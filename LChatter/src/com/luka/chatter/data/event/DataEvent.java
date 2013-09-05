package com.luka.chatter.data.event;

import com.luka.chatter.Connection;
import com.luka.chatter.data.Data;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class DataEvent extends ConnectionEvent {

    private final Data data;

    public DataEvent(Connection connection, final Data data) {
        super(connection);
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public boolean reply(final Data data){
        return getConnection().send(data);
    }


}
