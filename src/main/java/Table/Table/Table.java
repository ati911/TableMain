package Table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static Table.textDecorations.Decor.*;

public class Table {
    private static final String CROSS = "+";
    private static final String MINUS = "-";
    private static final String PIPE = "|";
    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final String NA = "N/A";

    private final List<Column> columns = new ArrayList<>();
    private int columnIndex;
    private final int numberOfColumns;
    private String headerTextColor = "";
    private String titleDecor = "";
    private String[] alignChar;
    private final List<StringBuilder> rowDecors = new ArrayList<>();

    public Table(String... headers) {
        numberOfColumns = headers.length;
        for (int i = 0; i < numberOfColumns; i++) {
            columns.add(new Column());
            addCell(headers[i]);
        }
        alignChar = new String[numberOfColumns];
        Arrays.fill(alignChar, EMPTY);
    }

    public void addRow(String... rows) {
        rowDecors.add(null);
        addRowInner(rows);
    }

    public void addRow(StringBuilder rowDecor, String... rows) {
        rowDecors.add(rowDecor);
        addRowInner(rows);
    }

    private void addRowInner(String... rows) {
        if (checkSameColumnNumber(rows.length)) {
            for (String cellData : rows) {
                addCell(Objects.requireNonNullElse(cellData, NA));
            }
        }
    }

    private boolean checkSameColumnNumber(int rowsLength) {
        if (rowsLength != numberOfColumns) {
            throw new NotMatchingRowAndHeadersException("A beadott értékek száma nem felel meg a fejléceknek!");
        }
        return true;
    }

    public void printTable(String title) {
        setTitle(title);
        printTable();
    }

    public void printTable(String titleDecor, String title) {
        setTitle(titleDecor, title);
        printTable();
    }

    public void printTable() {
        int numberOfRows = columns.get(0).getCells().size();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                String leftBorder;
                if (j == 0) {
                    leftBorder = PIPE + SPACE;
                } else {
                    leftBorder = EMPTY;
                }
                int longestCell = columns.get(j).getLongestCell();
                String cell = columns.get(j).getCells().get(i);
                if (i == 0) {
                    System.out.printf(leftBorder + headerTextColor +
                            ITALIC + "%" + alignChar[j] +
                            longestCell + "s" + RESET + SPACE + PIPE + SPACE, cell);
                } else {
                    String actualRowDecor = Objects.requireNonNullElse(
                            rowDecors.get(i - 1), new StringBuilder()).toString();
                    System.out.printf(leftBorder + actualRowDecor + "%" +
                            alignChar[j] + longestCell + "s" + RESET + SPACE + PIPE + SPACE, cell);
                }
            }
            System.out.println();
        }
        drawHorizontalLine(true);
    }

    public void setColumnIsLeftAligned(boolean... columnLeftAlign) {
        int inputLength = columnLeftAlign.length;
        if (checkSameColumnNumber(inputLength)) {
            String[] alignChar = new String[inputLength];
            for (int i = 0; i < inputLength; i++) {
                if (columnLeftAlign[i]) {
                    alignChar[i] = MINUS;
                } else {
                    alignChar[i] = EMPTY;
                }
            }
            this.alignChar = alignChar;
        }
    }

    public void setHeaderTextColor(String headerTextColor) {
        this.headerTextColor = headerTextColor;
    }

    private void setTitle(String title) {
        int width = drawHorizontalLine(false);
        int padSize = width - title.length();
        int padStart = title.length() + padSize / 2;
        title = String.format("%" + padStart + "s", title);
        title = String.format(PIPE + titleDecor + "%-" + (width - 1) + "s" + RESET + PIPE, title);
        System.out.print(title);
        System.out.println();
        drawHorizontalLine(true);
    }

    private void setTitle(String titleDecor, String title) {
        this.titleDecor = titleDecor;
        setTitle(title);
    }

    private int drawHorizontalLine(boolean crossIsReq) {
        int totalLength = 0;
        System.out.print(crossOrMinus(crossIsReq));
        for (int i = 0; i < numberOfColumns; i++) {
            int longestCell = columns.get(i).getLongestCell();
            for (int j = 0; j < longestCell + 2; j++) {
                System.out.print(MINUS);
                totalLength++;
            }
                System.out.print(crossOrMinus(crossIsReq));
        }
        System.out.println();
        return totalLength + numberOfColumns;
    }

    private String crossOrMinus(boolean crossIsReq) {
        return crossIsReq ? CROSS : MINUS;
    }

    private void addCell(String content) {
        if (columnIndex == numberOfColumns) {
            columnIndex = 0;
        }
        columns.get(columnIndex).addCell(content);
        columnIndex++;
    }
}
