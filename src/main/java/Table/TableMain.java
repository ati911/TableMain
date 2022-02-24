package Table;

import static Table.textDecorations.Decor.*;

public class TableMain {
    public static void main(String[] args) {
        Table table = new Table("Ssz.", "Valami név", "Még valami", "Akármi");

        StringBuilder rowDecor = new StringBuilder(GREEN);

        table.addRow("1.", "hosszúszöveg", "szöveg", "12");
        table.addRow(rowDecor, "2.", "szöveg", "szöveg", "4213000");
        table.addRow("3.", "szöveg", "hosszúszöveg", "15035");

        table.setColumnIsLeftAligned(true, false, true, false);
        table.printTable( WHITE+ITALIC+BOLD, "MINTA TÁBLÁZAT");

    }

}
