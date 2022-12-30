package test.bjtmastermind.colparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import me.bjtmastermind.colparser.COLFile;

public class Test {
    public static void main(String[] args) throws Exception {
        parsingTest();
        assemblingTest("path/to/colours.col", "/path/to/new_colors.col");
    }

    private static void parsingTest() throws IOException {
        COLFile colFile = new COLFile();
        colFile.parse("path/to/colours.col");

        System.out.println(colFile.printColors());
        System.out.println(colFile.printWaterColors());
    }

    private static void assemblingTest(String intputPath, String outputPath) throws IOException {
        COLFile colFile = new COLFile();
        colFile.parse(intputPath);

        colFile.assemble(outputPath);

        if (Files.exists(new File(outputPath).toPath())) {
            System.out.println("Successfully created file at: '"+outputPath+"'.");
        } else {
            System.err.println("Failed to create file.");
        }
    }
}
