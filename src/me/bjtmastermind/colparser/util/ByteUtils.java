package me.bjtmastermind.colparser.util;

import java.nio.ByteBuffer;

public class ByteUtils {

    public static String bytesToString(byte[] bytes) {
        return new String(ByteBuffer.wrap(bytes).array());
    }


}
