package com.luka.chat.user;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 03/09/13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class Users {

    public static User getUser(final List<User> users, final SocketChannel socketChannel) {
        for(User user : users) {
            if(user.getSocketChannel().equals(socketChannel)) {
                return user;
            }
        }
        return null;
    }

}
