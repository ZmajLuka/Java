package com.luka.chatter.handlers;

import com.luka.chatter.data.Data;
import com.luka.chatter.data.event.DataEvent;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHandler<T extends Data>{

    public abstract void handle(final DataEvent e, final T data);

}
