package com.luka.chat.server.handlers;

import com.luka.chat.data.Data;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 05/09/13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHandler{

    public abstract void handle(final byte[] bytes);

}