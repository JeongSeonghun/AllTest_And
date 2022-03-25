package com.jsh.kr.alltestlib.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * https://palpit.tistory.com/641
 */
class ByteUtil {

    public static byte[] intTobyte(int integer, ByteOrder order) {

        ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
        buff.order(order);

        buff.putInt(integer);

        return buff.array();
    }

    // java -> Big, c -> little
    public static int bytesToInt(byte[] bytes, ByteOrder order) {
        ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
        buff.order(order);
        byte top = ByteBuffer.wrap(bytes).order(order).get(0);

        while (buff.position() < buff.capacity() - bytes.length) {
            if ((top & (byte) 0x80) == (byte) 0x80) {
                buff.put((byte) 0xff);
            } else {
                buff.put((byte) 0x00);
            }
        }

        buff.put(bytes);

        buff.flip();

        return buff.getInt();
    }

    public static int unsignedBytesToInt(byte[] bytes, ByteOrder order) {
        ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
        buff.order(order);

        while (buff.position() < buff.capacity() - bytes.length) {
            buff.put((byte) 0x00);
        }

        buff.put(bytes);

        buff.flip();

        return buff.getInt();
    }

    public static int byteToInt(byte b) {
        return b;
    }

    public static int unsignedByteToInt(byte b) {
        return b & 0xff;
    }

    public static String byteToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.US_ASCII);
    }

    public static String changeToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(changeToHexString(b));
        }
        return sb.toString();
    }
    public static String changeToHexString(byte b) {
        return String.format("%02X", b&0xff);
    }

    public static UUID makeUUID16bit(byte[] bytes, ByteOrder order) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(order);
        return UUID.fromString(String.format(
                "%08x-0000-1000-8000-00805f9b34fb", buffer.getShort()));
    }

    public static UUID makeUUID128bit(byte[] bytes, ByteOrder order) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(order);
        long lsb = buffer.getLong();
        long msb = buffer.getLong();
        if (order.toString().equals(ByteOrder.BIG_ENDIAN.toString())) {
            return new UUID(lsb, msb);
        } else {
            // TODO: 09/07/2019 실제 데이터 확인 필요
            return new UUID(msb, lsb);
        }
    }
}
