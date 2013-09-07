package com.luka.chat.data.impl;

import com.luka.chat.data.Data;
import com.luka.chat.data.Opcodes;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 03/09/13
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class MessageData extends Data<String> {

    public MessageData(String value) {
        super(Opcodes.MESSAGE, value.getBytes());
    }
}
