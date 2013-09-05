package com.luka.chatter;

import com.luka.chatter.data.Data;
import com.luka.chatter.property.user.User;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class ServerUser extends User {

    public transient Connection connection;

    public User safe() {
        final User user = new User();
        user.getUid().setValue(getUid().getValue());
        user.getName().setValue(getName().getValue());
        return user;
    }

    public boolean send(final Data data ) {
        return connection != null && connection.send(data);
    }

}
