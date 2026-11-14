package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TaskFileLoader {

    private static final String HEADER = "id;title;category;estimatedHours;status";
    private static final int EXPECTED_FIELDS = 5;

    public LoadResult load(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("Cannot read " + path, e);
        }

        List<Task> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        if (lines.isEmpty()) {
            errors.add(new ValidationError(0, "header", "file is empty"));
            return new LoadResult(tasks, errors);
        }

        if (!HEADER.equals(lines.get(0).trim())) {
            errors.add(new ValidationError(1, "header",
                    "expected header '" + HEADER + "'"));
            return new LoadResult(tasks, errors);
        }

        for (int i = 1; i < lines.size(); i++) {
            int lineNumber = i + 1;
            String line = lines.get(i);
            if (line.isBlank()) {
                continue;
            }
            parseLine(line, lineNumber, tasks, errors);
        }
        return new LoadResult(tasks, errors);
    }

    private void parseLine(String line, int lineNumber,
                           List<Task> tasks, List<ValidationError> errors) {

        List<String> parts;
        try {
            parts = RowSplitter.split(line);
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError(lineNumber, "row",
                    "unbalanced quotes: " + e.getMessage()));
            return;
        }

        if (parts.size() != EXPECTED_FIELDS) {
            errors.add(new ValidationError(lineNumber, "row",
                    "expected " + EXPECTED_FIELDS + " fields, got " + parts.size()));
            return;
        }

        String id = parts.get(0).trim();
        String title = parts.get(1).trim();
        String category = parts.get(2).trim();
        String hoursRaw = parts.get(3).trim();
        String statusRaw = parts.get(4).trim();

        if (id.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "id", "id is empty"));
            return;
        }
        if (title.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "title", "title is empty"));
            return;
        }
        if (category.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "category", "category is empty"));
            return;
        }

        int hours;
        try {
            hours = Integer.parseInt(hoursRaw);
        } catch (NumberFormatException e) {
            errors.add(new ValidationError(lineNumber, "estimatedHours",
                    "not a number: '" + hoursRaw + "'"));
            return;
        }
        if (hours <= 0) {
            errors.add(new ValidationError(lineNumber, "estimatedHours",
                    "must be positive: " + hours));
            return;
        }

        TaskStatus status;
        try {
            status = TaskStatus.parse(statusRaw);
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError(lineNumber, "status",
                    "unknown status: '" + statusRaw + "'"));
            return;
        }

        tasks.add(new Task(id, title, category, hours, status));
    }
}