package me.bjtmastermind.colparser;

import java.util.LinkedHashMap;

public class COLFile {
    private long colorCount;
    private LinkedHashMap<String, String> colors;

    public COLFile() {
        this.colors = new LinkedHashMap<>();
    }

    public COLFile(long colorCount, LinkedHashMap<String, String> colors) {
        this.colorCount = colorCount;
        this.colors = colors;
    }

    public COLFile setColors(LinkedHashMap<String, String> colors) {
        this.colorCount = colors.size();
        this.colors = colors;
        return this;
    }

    public COLFile addColor(String name, String color) {
        this.colors.put(name, color);
        this.colorCount = colors.size();
        return this;
    }

    public long getColorCount() {
        return this.colorCount;
    }

    public LinkedHashMap<String, String> getColors() {
        return this.colors;
    }

    public int[] hexToRGB(String hex) {
        String hexStripped = hex.replace("#", "");
        int r = Integer.valueOf(hexStripped.substring(0, 2), 16);
        int g = Integer.valueOf(hexStripped.substring(2, 4), 16);
        int b = Integer.valueOf(hexStripped.substring(4, 6), 16);
        return new int[] {r,g,b};
    }

    COLFile setColorCount(long colorCount) {
        this.colorCount = colorCount;
        return this;
    }
}
