package me.bjtmastermind.colparser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class COLFile {
    private int colorCount;
    private LinkedHashMap<String, Integer> colors;
    private int waterColorCount;
    private LinkedHashMap<String, int[]> waterColors;

    public COLFile() {
        this.colors = new LinkedHashMap<>();
        this.waterColors = new LinkedHashMap<>();
    }

    public COLFile(LinkedHashMap<String, Integer> colors) {
        this(colors, new LinkedHashMap<String, int[]>());
    }

    public COLFile(LinkedHashMap<String, Integer> colors, LinkedHashMap<String, int[]> waterColors) {
        this.colors = colors;
        this.waterColors = waterColors;
        this.colorCount = colors.size();
        this.waterColorCount = waterColors.size();
    }

    public COLFile addColor(String name, int color) {
        this.colors.put(name, color);
        this.colorCount = colors.size();
        return this;
    }

    public COLFile addWaterColor(String name, int[] colors) {
        this.waterColors.put(name, colors);
        this.waterColorCount = waterColors.size();
        return this;
    }

    public void assemble(String outputFilepath) {
        DataOutputStream rawOutput = new DataOutputStream(new ByteArrayOutputStream());

        try {
            rawOutput.writeInt(this.hasWaterColors() ? 1 : 0);
            rawOutput.writeInt(this.getColorCount());

            for (String color : this.getColors().keySet()) {
                rawOutput.writeShort((short) color.length());
                rawOutput.writeUTF(color);
                rawOutput.writeInt(this.getColors().get(color));
            }

            if (this.hasWaterColors()) {
                rawOutput.writeInt(this.getWaterColorCount());
                for (String waterColor : this.getWaterColors().keySet()) {
                    rawOutput.writeShort((short) waterColor.length());
                    rawOutput.writeUTF(waterColor);

                    for (int i = 0; i < 3; i++) {
                        rawOutput.writeInt(this.getWaterColors().get(waterColor)[i]);
                    }
                }
            }

            File output = new File(outputFilepath);
            Files.write(output.toPath(), Utils.dataOutputStreamToArray(rawOutput));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getColorCount() {
        return this.colorCount;
    }

    public LinkedHashMap<String, Integer> getColors() {
        return this.colors;
    }

    public int getWaterColorCount() {
        return this.waterColorCount;
    }

    public LinkedHashMap<String, int[]> getWaterColors() {
        return this.waterColors;
    }

    public boolean hasWaterColors() {
        return this.getWaterColorCount() > 0;
    }

    public COLFile parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(new File(filepath).toURI()));

        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);

        int hasWaterColors = buffer.getInt();
        int colorCount = buffer.getInt();
        this.colorCount = colorCount;

        for (int i = 0; i < colorCount; i++) {
            short nameSize = buffer.getShort();
            String name = Utils.getString(buffer, nameSize);
            int color = buffer.getInt();

            this.addColor(name, color);
        }

        if (hasWaterColors > 0) {
            int waterColorCount = buffer.getInt();
            this.waterColorCount = waterColorCount;
            for (int i = 0; i < waterColorCount; i++) {
                short nameSize = buffer.getShort();
                String name = Utils.getString(buffer, nameSize);

                int[] colors = new int[3];
                for (int j = 0; j < 3; j++) {
                    colors[j] = buffer.getInt();
                }
                this.addWaterColor(name, colors);
            }
        }
        return this;
    }

    public String printColors() {
        String output = "";
        int currentIndex = 0;
        for (String color : this.getColors().keySet()) {
            output += color + " | " + toARGBHex(this.getColors().get(color));
            if (currentIndex < this.getColors().keySet().size() - 1) {
                output += "\n";
            }
            currentIndex++;
        }
        return output;
    }

    public String printWaterColors() {
        String output = "";
        int currentIndex = 0;
        for (String color : this.getWaterColors().keySet()) {
            output += color + " | ";
            for (int i = 0; i < 3; i++) {
                output += toARGBHex(this.getWaterColors().get(color)[i]) + " ";
            }

            if (currentIndex < this.getWaterColors().keySet().size() - 1) {
                output += "\n";
            }
            currentIndex++;
        }
        return output;
    }

    public COLFile setColors(LinkedHashMap<String, Integer> colors) {
        this.colors = colors;
        this.colorCount = colors.size();
        return this;
    }

    public COLFile setWaterColors(LinkedHashMap<String, int[]> waterColors) {
        this.waterColors = waterColors;
        this.waterColorCount = this.waterColors.size();
        return this;
    }

    // TODO: Remove static and figure out how to convert color output of `colors` and `waterColors` to ARGB without wrapping in this current method.
    public static LinkedList<Integer> toARGB(int color) {
        LinkedList<Integer> argb = new LinkedList<>();
        argb.add((color >> 16) & 0xFF);
        argb.add((color >> 8) & 0xFF);
        argb.add(color & 0xFF);
        argb.add(0, (color >> 24) & 0xFF);
        return argb;
    }

    // TODO: Remove static and figure out how to convert color output of `colors` and `waterColors` to Hex without wrapping in this current method.
    public static String toARGBHex(int color) {
        String result = String.format("%08X", color);
        return "#" + result;
    }
}
