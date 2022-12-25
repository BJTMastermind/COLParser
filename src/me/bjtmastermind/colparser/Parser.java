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

        int hasWaterColors = buffer.order(ByteOrder.BIG_ENDIAN).getInt();
        int colorCount = buffer.order(ByteOrder.BIG_ENDIAN).getInt();
        colFile.setColorCount(colorCount);

        for (int i = 0; i < colorCount; i++) {
            short nameSize = buffer.getShort();

            byte[] nameAsBytes = new byte[nameSize];
            buffer.get(nameAsBytes, 0, nameSize);
            String name = ByteUtils.bytesToString(nameAsBytes);

            int color = buffer.getInt();

            colFile.addColor(name, color);
        }

        if (hasWaterColors > 0) {
            int waterColorCount = buffer.order(ByteOrder.BIG_ENDIAN).getInt();
            for (int i = 0; i < waterColorCount; i++) {
                short nameSize = buffer.getShort();

                byte[] nameAsBytes = new byte[nameSize];
                buffer.get(nameAsBytes, 0, nameSize);
                String name = ByteUtils.bytesToString(nameAsBytes);

                int[] colors = new int[3];
                for (int j = 0; j < 3; j++) {
                    colors[j] = buffer.getInt();
                }
                colFile.addWaterColor(name, colors);
            }
        }
        return colFile;
    }
}
