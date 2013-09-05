package com.luka.chat.user;

import java.nio.channels.SocketChannel;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 03/09/13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private final SocketChannel socketChannel;
    private final Property<String> name;


    public User(final SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.name = new Property<String>();
    }

    public Property<String> getName() {
        return this.name;
    }

    public SocketChannel getSocketChannel() {
        return this.socketChannel;
    }

}
