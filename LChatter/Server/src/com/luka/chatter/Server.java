package com.luka.chatter;

import com.luka.chatter.data.Constants;
import com.luka.chatter.data.Data;
import com.luka.chatter.data.Opcodes;
import com.luka.chatter.data.event.ConnectionEvent;
import com.luka.chatter.data.event.ConnectionListner;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.event.DataListner;
import com.luka.chatter.data.impl.AssignData;
import com.luka.chatter.data.impl.JoinData;
import com.luka.chatter.data.impl.LeaveData;
import com.luka.chatter.data.impl.MessageData;
import com.luka.chatter.handlers.AbstractHandler;
import com.luka.chatter.handlers.ColorHandler;
import com.luka.chatter.handlers.MessageHandler;
import com.luka.chatter.handlers.MoveHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Thread implements Runnable,Opcodes,Constants {

    public static final ServerUsers users = new ServerUsers();
    private ServerSocket serverSocket;
    private static final Map<Byte, AbstractHandler> handlers = new HashMap<Byte, AbstractHandler>();
    private long uid = 0;


    static {
        handlers.put(MESSAGE, new MessageHandler());
        handlers.put(MOVE, new MoveHandler());
        handlers.put(COLOR, new ColorHandler());
    }

    private Server() {
        setPriority(MAX_PRIORITY);
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            while(true) {
                try{
                    final Socket socket = serverSocket.accept();
                    final Connection connection = new Connection(socket);
                    connection.send( new MessageData(-1, String.format("Welcome to %s", TITLE)));
                    final ServerUser user = new ServerUser();
                    user.connection = connection;
                    user.getName().setValue("Guest" + uid++);
                    user.getUid().setValue(uid);

                    connection.addConnectionListener(new ConnectionListner() {
                        @Override
                        public void onConnectionClosed(ConnectionEvent connectionEvent) {
                            final ServerUser user  = users.getUser(connectionEvent.getConnection());
                            if(user == null) {
                                return;
                            }
                            users.remove(user);
                            users.send(new LeaveData(user));
                            users.send(new MessageData(-1, String.format("%s has left the chat" , user.getName().getValue())));
                        }
                    });

                    connection.addDataListener(new DataListner() {
                        @Override
                        public void onDataReceived(DataEvent dataEvent) {
                            final Data data = dataEvent.getData();
                            handlers.get(data.getOpcode()).handle(dataEvent, data);
                        }
                    });

                    connection.send(new AssignData(user.safe()));
                    users.send(new JoinData(user.safe()));
                    for(final ServerUser su : users) {
                        connection.send(new JoinData(su.safe()));
                    }
                    users.send(new MessageData(-1 ,String.format("%s has just joined the chat", user.getName().getValue())));
                    users.add(user);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void main(String args[]){
        new Server().start();
    }

}
