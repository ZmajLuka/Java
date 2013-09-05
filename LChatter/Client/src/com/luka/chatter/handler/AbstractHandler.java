package com.luka.chatter.handler;

import com.luka.chatter.data.Data;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHandler<T extends Data> {

    public abstract void handle(final T data);

}
