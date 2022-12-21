package test.bjtmastermind.colparser;

import java.io.File;

import me.bjtmastermind.colparser.COLFile;
import me.bjtmastermind.colparser.Parser;

public class Test {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(new File("/home/bjtmastermind/Desktop/City Texture Pack/x32/colours.col"));
        COLFile colFile = parser.parse();

        for (String color : colFile.getColors().keySet()) {
            System.out.println(color+" | "+colFile.getColors().get(color));
        }
    }
}
