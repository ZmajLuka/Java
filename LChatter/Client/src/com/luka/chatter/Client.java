package com.luka.chatter;

import com.luka.chatter.components.ChatArea;
import com.luka.chatter.components.InputArea;
import com.luka.chatter.components.UserArea;
import com.luka.chatter.data.Constants;
import com.luka.chatter.data.Data;
import com.luka.chatter.data.Opcodes;
import com.luka.chatter.data.event.DataEvent;
import com.luka.chatter.data.event.DataListner;
import com.luka.chatter.handler.*;
import com.luka.chatter.property.user.User;
import com.luka.chatter.property.user.Users;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class Client extends JFrame implements Opcodes,Constants {

    private static Connection connection;
    private static final Map<Byte, AbstractHandler> handler = new HashMap<Byte, AbstractHandler>();
    private static Users<User> users;
    private static User user;
    private static ChatArea chatArea;
    private static InputArea inputArea;
    public static final Dimension USERWINDOW = new Dimension(800,700);

    static {
        handler.put(ASSIGN, new AssignHandler());
        handler.put(LEAVE, new LeaveHandler());
        handler.put(JOIN, new JoinHandler());
        handler.put(MESSAGE, new MessageHandler());
        handler.put(MOVE, new MoveHandler());
        handler.put(COLOR, new ColorHandler());
    }

    private Client() {
        setTitle(TITLE);
        setLayout(new BorderLayout());

        users = new Users<User>();
        user = new User();
        users.add(user);

        UserArea userArea = new UserArea();
        chatArea = new ChatArea();
        inputArea = new InputArea();

        final JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, chatArea, inputArea);
        add(userArea, BorderLayout.CENTER);
        add(split, BorderLayout.SOUTH);


        userArea.setPreferredSize(USERWINDOW);

        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static User getUser() {
        return user;
    }

    public static Users getUsers() {
        return users;
    }

    public static Connection getConnection(){
        return connection;
    }

    public static InputArea getInputArea() {
        return inputArea;
    }

    public static ChatArea getChatArea() {
        return chatArea;
    }

    public void start() throws IOException {
        connection = new Connection(new Socket(HOST,PORT));
        connection.addDataListener(new DataListner() {
            @Override
            public void onDataReceived(DataEvent dataEvent) {
                final Data data = dataEvent.getData();
                handler.get(data.getOpcode()).handle(data);
            }
        });

    }

    public static void main(String args[]) throws IOException{
        new Client().start();
    }

}
