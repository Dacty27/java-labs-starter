package edu.course.lab05;

import java.util.ArrayList;
import java.util.List;

final class RowSplitter {

    private RowSplitter() {}

    static List<String> split(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ';' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());

        if (inQuotes) {
            throw new IllegalArgumentException("unbalanced quotes");
        }
        return fields;
    }
}