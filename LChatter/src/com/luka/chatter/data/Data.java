package com.luka.chatter.data;

import java.io.Serializable;
import java.util.Date;
/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class Data implements Serializable, Opcodes, Constants {

    private final byte opcode;
    private final Date date;

    public Data(final byte opcode) {
        this.opcode = opcode;
        this.date = new Date();
    }

    public byte getOpcode() {
        return this.opcode;
    }

    public Date getTime() {
        return this.date;
    }

    public String getTimestamp() {
        return TIME_FORMAT.format(date);
    }


}
