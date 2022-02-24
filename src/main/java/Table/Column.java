package Table;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private List<String> cells;

    public Column() {
        cells = new ArrayList<>();
    }

    public void addCell(String content) {
        cells.add(content);
    }

    public List<String> getCells() {
        return cells;
    }

    public int getLongestCell() {
        return cells.stream().mapToInt(String::length).max().orElse(0);
    }
}
