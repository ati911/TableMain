package Table;

import Table.Table.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static Table.textDecorations.Decor.WHITE;

public class FilePrintMain {
    private static final String pathString = "src/main/resources";

    public static void main(String[] args) {

        Table table = new Table("#", "Fájlnév", "Sorok");
        table.setHeaderTextColor(WHITE);
        File[] files = new File(pathString).listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            String actualFileName = files[i].getName();
            try {
                Path pathOfFile = Path.of(pathString + "/" + actualFileName);
                long linesCounter = Files.lines(pathOfFile).count();
                table.addRow("" + (i + 1), actualFileName, "" + (linesCounter - 1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        table.setColumnIsLeftAligned(false, true, false);
        table.printTable("Elérhető fájlok");

    }
}
