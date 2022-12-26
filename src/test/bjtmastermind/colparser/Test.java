package test.bjtmastermind.colparser;

import java.io.File;

import me.bjtmastermind.colparser.Assembler;
import me.bjtmastermind.colparser.COLFile;
import me.bjtmastermind.colparser.Parser;

public class Test {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(new File("path/to/colours.col"));
        COLFile colFile = parser.parse();

        for (String color : colFile.getColors().keySet()) {
            System.out.println(color+" | "+COLFile.toHex(colFile.getColors().get(color)));
        }
        for (String color : colFile.getWaterColors().keySet()) {
            System.out.print(color+" | ");
            for (int i = 0; i < 3; i++) {
                System.out.print(COLFile.toHex(colFile.getWaterColors().get(color)[i])+" ");
            }
            System.out.println("");
        }

        Assembler assembler = new Assembler(colFile);
        assembler.assemble("path/to/new_colours.col");
    }
}
