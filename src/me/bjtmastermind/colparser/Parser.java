package me.bjtmastermind.colparser;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.bjtmastermind.colparser.util.ByteUtils;

public class Parser {
    private byte[] bytes;
    private COLFile colFile;

    public Parser(File file) throws IOException {
        bytes = Files.readAllBytes(Paths.get(file.toURI()));
        colFile = new COLFile();
    }

    public COLFile parse() throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        colFile.setColorCount(buffer.getLong());
        while (buffer.position() < bytes.length) {
            if(buffer.get() != 0x00) {
                throw new IOException("Expected null termination byte '00' at position "+(buffer.position() - 1)+
                    " but found byte '"+String.format("%02X", buffer.get(buffer.position() - 1))+"'. Not a vaild COL file.");
            }

            int nameSize = buffer.get();

            byte[] nameAsBytes = new byte[nameSize];
            buffer.get(nameAsBytes, 0, nameSize);
            String name = ByteUtils.bytesToString(nameAsBytes);

            if(buffer.get() != 0x00) {
                throw new IOException("Expected null termination byte '00' at position "+(buffer.position() - 1)+
                    " but found byte '"+String.format("%02X", buffer.get(buffer.position() - 1))+"'. Not a vaild COL file.");
            }

            byte[] hexColorAsBytes = new byte[3];
            buffer.get(hexColorAsBytes, 0, 3);
            String hexColor = ByteUtils.bytesToHexString(hexColorAsBytes);

            colFile.addColor(name, hexColor);
        }
        return colFile;
    }
}
