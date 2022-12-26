package me.bjtmastermind.colparser;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class COLFile {
    private int colorCount;
    private int waterColorCount;
    private LinkedHashMap<String, Integer> colors;
    private LinkedHashMap<String, int[]> waterColors;

    public COLFile() {
        this.colors = new LinkedHashMap<>();
        this.waterColors = new LinkedHashMap<>();
    }

    public COLFile(int colorCount, int waterColorCount, LinkedHashMap<String, Integer> colors, LinkedHashMap<String, int[]> waterColors) {
        this.colorCount = colorCount;
        this.waterColorCount = waterColorCount;
        this.colors = colors;
        this.waterColors = waterColors;
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

    public int getColorCount() {
        return this.colorCount;
    }

    public boolean hasWaterColors() {
        return this.getWaterColorCount() > 0;
    }

    public int getWaterColorCount() {
        return this.waterColorCount;
    }

    public LinkedHashMap<String, Integer> getColors() {
        return this.colors;
    }

    public LinkedHashMap<String, int[]> getWaterColors() {
        return this.waterColors;
    }

    public static String toHex(int color) {
        String result = Integer.toHexString(color).toUpperCase();
        while (result.length() < 8) {
            result = "0" + result;
        }
        return "#" + result;
    }

    public static LinkedList<Integer> toARGB(int color) {
        LinkedList<Integer> argb = new LinkedList<>();
        argb.add((color >> 16) & 0xFF);
        argb.add((color >> 8) & 0xFF);
        argb.add(color & 0xFF);
        argb.add(0, (color >> 24) & 0xFF);
        return argb;
    }

    COLFile setColorCount(int colorCount) {
        this.colorCount = colorCount;
        return this;
    }
}
