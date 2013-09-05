package com.luka.chatter.data.impl;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class MessageData extends  UidSingleValueData<String>  {


    public MessageData(String value) {
        super(MESSAGE, -1, value);
    }

    public MessageData(final long uid, String value) {
        super(MESSAGE, uid, value);
    }

}
