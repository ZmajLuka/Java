package com.luka.chat.data;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 03/09/13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Data<T>  {

    private final  byte[] bytes;
    private final byte opcode;

    public Data(final byte opcode, final byte[] bytes) {
          this.bytes  = bytes;
          this.opcode = opcode;
    }

    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(this.bytes.length+1);
        buffer.put(this.opcode);
        buffer.put(this.bytes);
        return buffer.array();
    }


}
