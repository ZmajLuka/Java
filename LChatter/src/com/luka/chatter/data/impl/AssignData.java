package com.luka.chatter.data.impl;

import com.luka.chatter.property.user.User;

import java.io.Serializable;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */
public class AssignData extends SingleValueData<User> implements Serializable {

    public AssignData( User value) {
        super(ASSIGN, value);
    }
}
