package com.luka.chatter.property.user;

import java.util.LinkedList;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 08/08/13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class Users<T extends User> extends LinkedList<T> {

    public T getUser(final long uid){
        for(final T t : this)
            if(t.equals(uid))
                return t;
        return null;
    }

    public T getUser(final String name){
        for(final T t : this)
            if(t.getName().getValue().equalsIgnoreCase(name))
                return t;
        return null;
    }
}
