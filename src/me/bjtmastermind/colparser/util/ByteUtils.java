package me.bjtmastermind.colparser.util;

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class ByteUtils {

    public static String bytesToString(byte[] bytes) {
        return new String(ByteBuffer.wrap(bytes).array());
    }

    public static void addShortToBuffer(LinkedList<Byte> addTo, short value) {
        byte[] valueAsBytes = ByteBuffer.allocate(2).putShort(value).array();
        for (int i = 0; i < valueAsBytes.length; i++) {
            addTo.add(valueAsBytes[i]);
        }
    }

    public static void addIntToBuffer(LinkedList<Byte> addTo, int value) {
        byte[] valueAsBytes = ByteBuffer.allocate(4).putInt(value).array();
        for (int i = 0; i < valueAsBytes.length; i++) {
            addTo.add(valueAsBytes[i]);
        }
    }

    public static void addStringToBuffer(LinkedList<Byte> addTo, String value) {
        byte[] valueAsBytes = ByteBuffer.allocate(value.length()).put(value.getBytes(), 0, value.length()).array();
        for (int i = 0; i < valueAsBytes.length; i++) {
            addTo.add(valueAsBytes[i]);
        }
    }

    public static byte[] listToArray(LinkedList<Byte> src) {
        byte[] output = new byte[src.size()];
        for (int i = 0; i < src.size(); i++) {
            output[i] = src.get(i);
        }
        return output;
    }
}
