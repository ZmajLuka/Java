package com.luka.chat.server;

import com.luka.chat.data.Data;
import com.luka.chat.data.Opcodes;
import com.luka.chat.data.impl.MessageData;
import com.luka.chat.server.data.DataEvent;
import com.luka.chat.server.data.DataListner;
import com.luka.chat.server.handlers.AbstractHandler;
import com.luka.chat.server.handlers.MessageHandler;
import com.luka.chat.user.User;
import com.luka.chat.user.Users;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Luka
 * Date: 23/08/13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Thread implements Runnable, Opcodes{

    private static List<User> users;
    private long uid = 0;
    private ArrayList<DataListner> dataListners = new ArrayList<DataListner>();
    public final static HashMap<Byte,AbstractHandler> handlers = new HashMap<Byte, AbstractHandler>();

    static {
        handlers.put(MESSAGE, new MessageHandler());
    }

    private Server(){
        users = new LinkedList<User>();

        setPriority(MAX_PRIORITY);
    }

    private static void send(final User user, final Data data){
        try{
            user.getSocketChannel().write(ByteBuffer.wrap(data.getBuffer().array()));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void send(final Data data){
        for(final User user : users)
            send(user, data);
    }

    public void run(){
        try{
            final Selector selector = Selector.open();
            final ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
            server.socket().bind(new InetSocketAddress(4595));
            while(true){
                try{
                    selector.select();
                    final Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while(keys.hasNext()){
                        final SelectionKey key = keys.next();
                        keys.remove();
                        if(key.isAcceptable()){
                            try{
                                final SocketChannel sc = server.accept();
                                sc.configureBlocking(false);
                                sc.register(selector, SelectionKey.OP_READ);
                                final User user = new User(sc);
                                user.getName().setValue("Guest" + uid++);

                                send(user, new MessageData("Welcome to chat"));
                                send(new MessageData(String.format("%s just joined", user.getName().getValue())));
                                users.add(user);
                            }catch(IOException ex){
                                ex.printStackTrace();
                            }
                        }else if(key.isReadable()){
                            final SocketChannel sc = (SocketChannel) key.channel();
                            final User user = Users.getUser(users,sc);
                            try{
                                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                                if(sc.read(buffer) == -1)  {
                                    throw new EOFException();
                                }
                                String s = String.format("%s :" + new String(buffer.array()) , user.getName().getValue());
                                byte opcode = buffer.get(0);
                                dataListners.add(new DataListner() {
                                    @Override
                                    public void onDataRecieved(DataEvent event) {
                                        event.handle();
                                    }
                                });
                                fireDataRecieved(opcode, s.getBytes());

                            } catch (IOException ex) {
                                ex.printStackTrace();
                                key.cancel();
                                try{
                                    sc.close();
                                }catch(IOException e){}
                                users.remove(user);
                                send(new MessageData(String.format("%s just left", user.getName().getValue())));
                            }

                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void fireDataRecieved(final byte opcode, final byte[] bytes) {
        for(DataListner dataListner : dataListners) {
            dataListner.onDataRecieved(new DataEvent(opcode, bytes));
        }
        clear();
    }

    private void clear() {
        dataListners = new ArrayList<DataListner>();
    }



    public static void main(String args[]){
        new Server().start();
    }
}
