package com.luka.chatter;

import com.luka.chatter.data.Data;
import com.luka.chatter.data.event.ConnectionEvent;
import com.luka.chatter.data.event.ConnectionListner;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.event.DataListner;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class Connection extends Thread  implements Runnable{

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private final Socket socket;

    private final List<DataListner> dListeners;
    private final List<ConnectionListner> cListeners;



    public Connection(final Socket socket) throws IOException {

        this.socket = socket;


        dListeners = new LinkedList<DataListner>();
        cListeners = new LinkedList<ConnectionListner>();

        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.oos.flush();

        this.ois = new ObjectInputStream(socket.getInputStream());


        setPriority(MAX_PRIORITY);
        start();

    }


    public void addDataListener(final DataListner l){
        dListeners.add(l);
    }

    public void removeDataListener(final DataListner l){
        dListeners.remove(l);
    }

    public void addConnectionListener(final ConnectionListner l){
        cListeners.add(l);
    }

    public void removeConnectionListener(final ConnectionListner l){
        cListeners.remove(l);
    }

    private void fireConnectionClosed(){
        final ConnectionEvent e = new ConnectionEvent(this);
        for(final ConnectionListner l : cListeners)
            l.onConnectionClosed(e);
    }

    private void fireDataReceived(final Data d){
        final DataEvent e = new DataEvent(this, d);
        for(final DataListner l : dListeners)
            l.onDataReceived(e);
    }

    public void run(){
        while(true){
            try{
                final Data data = (Data) ois.readObject();
                fireDataReceived(data);
            }catch(Exception ex){
                ex.printStackTrace();
                close();
                break;
            }
        }
        fireConnectionClosed();
    }


    private void close() {

        try {
            this.oos.close();
            this.ois.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public boolean send(Data data) {
        try {
            this.oos.writeObject(data);
            this.oos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
    }


    private byte getByte(final Object...objects) throws IllegalAccessException, NoSuchFieldException {
        byte b = 0;
        for(Object object : objects) {
            if(object instanceof Long) {
                b+= 8;
            } else if(object instanceof String) {
                b+= ((String) object).length() * 2;
            }
        }
        System.out.println(b);
        return b;
    }


}
