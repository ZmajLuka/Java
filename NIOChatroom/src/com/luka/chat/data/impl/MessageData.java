package com.luka.chat.data.impl;

import com.luka.chat.data.Data;

import java.nio.ByteBuffer;
import java.util.ArrayList;

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
        super(MESSAGE, value);
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(getValue().getBytes().length+1);
        buffer.put(getOpcode());
        buffer.put(getValue().getBytes());
        return buffer;
    }
}
