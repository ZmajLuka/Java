package com.luka.chatter.data.event;

import com.luka.chatter.Connection;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionEvent {

     private final Connection connection;

     public ConnectionEvent(final Connection connection)  {
         this.connection = connection;
     }

    public Connection getConnection() {
        return this.connection;
    }



}
