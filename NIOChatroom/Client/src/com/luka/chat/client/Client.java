package com.luka.chat.client;

import com.luka.chat.data.Data;
import com.luka.chat.data.impl.MessageData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Luka
 * Date: 23/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class Client extends JFrame implements ActionListener, Runnable{

    private SocketChannel channel;
    private final JTextField input;
    private final JTextArea messages;

    private Client(){
        super("NIO Chat");
        setLayout(new BorderLayout());

        messages = new JTextArea();
        messages.setEditable(false);

        input = new JTextField();
        input.addActionListener(this);

        add(new JScrollPane(messages), BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
    }


    private void display(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void append(final String msg){
        messages.append(msg + "\n");
        messages.repaint();
    }

    private void send(final Data data){
        try{
            channel.write(ByteBuffer.wrap(data.getBuffer().array()));
        }catch(IOException ex){
            append(String.format("Error sending msg: %s", ex.getMessage()));
        }
    }

    public void actionPerformed(final ActionEvent e){
        final Object source = e.getSource();
        if(source.equals(input)){
            final String text = input.getText().trim();
            if(text.isEmpty())
                return;
            send(new MessageData(text));
            input.setText("");
            input.repaint();
        }
    }

    public void run(){
        try{
            final Selector selector = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_CONNECT);
            channel.connect(new InetSocketAddress(4595));
            while(true){
                try{
                    selector.select();
                    final Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while(keys.hasNext()){
                        final SelectionKey key = keys.next();
                        keys.remove();
                        if(key.isConnectable()){
                            try{
                                if(channel.isConnectionPending())
                                    channel.finishConnect();
                                channel.register(selector, SelectionKey.OP_READ);
                            }catch(IOException ex){
                                ex.printStackTrace();
                            }
                        }else if(key.isReadable()){
                            try{
                                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                                if(channel.read(buffer) == -1)
                                    throw new EOFException();
                                final byte[] bytes = buffer.array();
                                final String msg = new String(bytes);
                                append(msg.trim());
                            }catch(IOException ex){
                                ex.printStackTrace();
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

    public void start(){
        display();
        final Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }

    public static void main(String args[]){
        new Client().start();
    }
}
