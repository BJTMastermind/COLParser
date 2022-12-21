package me.bjtmastermind.colparser.util;

import java.nio.ByteBuffer;

public class ByteUtils {

    public static String bytesToString(byte[] bytes) {
        return new String(ByteBuffer.wrap(bytes).array());
    }

    public static String bytesToHexString(byte[] hex) {
        String hexColor = "";
        for (int i = 0; i < hex.length; i++) {
            hexColor += String.format("%02X", hex[i]);
        }
        return "#" + hexColor;
    }
}
