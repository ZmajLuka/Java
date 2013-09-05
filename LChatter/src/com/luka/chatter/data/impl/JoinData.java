package com.luka.chatter.data.impl;

import com.luka.chatter.property.user.User;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class JoinData extends SingleValueData<User> {

    public JoinData( User value) {
        super(JOIN, value);
    }

}
