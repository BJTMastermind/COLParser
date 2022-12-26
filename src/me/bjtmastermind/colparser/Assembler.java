package me.bjtmastermind.colparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

import me.bjtmastermind.colparser.util.ByteUtils;

public class Assembler {
    COLFile colFile;

    public Assembler(COLFile colFile) {
        this.colFile = colFile;
    }

    public void assemble(String outputFilepath) {
        LinkedList<Byte> rawOutput = new LinkedList<>();

        ByteUtils.addIntToBuffer(rawOutput, this.colFile.hasWaterColors() ? 1 : 0);
        ByteUtils.addIntToBuffer(rawOutput, this.colFile.getColorCount());

        for (String color : this.colFile.getColors().keySet()) {
            ByteUtils.addShortToBuffer(rawOutput, (short) color.length());
            ByteUtils.addStringToBuffer(rawOutput, color);
            ByteUtils.addIntToBuffer(rawOutput, this.colFile.getColors().get(color));
        }

        if (this.colFile.hasWaterColors()) {
            ByteUtils.addIntToBuffer(rawOutput, this.colFile.getWaterColorCount());
            for (String waterColor : this.colFile.getWaterColors().keySet()) {
                ByteUtils.addShortToBuffer(rawOutput, (short) waterColor.length());
                ByteUtils.addStringToBuffer(rawOutput, waterColor);

                for (int i = 0; i < 3; i++) {
                    ByteUtils.addIntToBuffer(rawOutput, this.colFile.getWaterColors().get(waterColor)[i]);
                }
            }
        }

        File output = new File(outputFilepath);
        try {
            Files.write(output.toPath(), ByteUtils.listToArray(rawOutput));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}