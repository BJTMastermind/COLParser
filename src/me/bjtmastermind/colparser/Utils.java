package me.bjtmastermind.colparser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

class Utils {

    static byte[] dataOutputStreamToArray(DataOutputStream src) {
        byte[] output = new byte[src.size()];
        try {
            src.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    static String getString(ByteBuffer buffer, int size) {
        byte[] bytes = new byte[size];
        buffer.get(bytes, 0, size);
        return new String(bytes);
    }
}
