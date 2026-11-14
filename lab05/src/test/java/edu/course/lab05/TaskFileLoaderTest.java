package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {

    private static final String HEADER =
            "id;title;category;estimatedHours;status";

    @TempDir
    Path tempDir;

    private Path write(String... lines) throws IOException {
        Path file = tempDir.resolve("tasks.txt");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    private final TaskFileLoader loader = new TaskFileLoader();

    @Test
    void loadsValidFile() throws IOException {
        Path file = write(
                HEADER,
                "T-101;Prepare release;backend;8;READY",
                "T-102;Write docs;docs;3;NEW");

        LoadResult result = loader.load(file);

        assertTrue(result.errors().isEmpty());
        assertEquals(2, result.tasks().size());
        Task first = result.tasks().get(0);
        assertEquals("T-101", first.id());
        assertEquals("Prepare release", first.title());
        assertEquals("backend", first.category());
        assertEquals(8, first.estimatedHours());
        assertEquals(TaskStatus.READY, first.status());
    }

    @Test
    void loadsUnicodeText() throws IOException {
        Path file = write(HEADER,
                "T-201;Подготовить релиз;бэкенд;5;IN_PROGRESS");

        LoadResult result = loader.load(file);

        assertTrue(result.errors().isEmpty());
        assertEquals(1, result.tasks().size());
        assertEquals("Подготовить релиз", result.tasks().get(0).title());
        assertEquals("бэкенд", result.tasks().get(0).category());
    }

    @Test
    void emptyFieldProducesError() throws IOException {
        Path file = write(HEADER, "T-101;;backend;8;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.tasks().isEmpty());
        assertEquals(1, result.errors().size());
        ValidationError err = result.errors().get(0);
        assertEquals(2, err.lineNumber());
        assertEquals("title", err.field());
    }

    @Test
    void badNumberProducesError() throws IOException {
        Path file = write(HEADER, "T-101;Prepare release;backend;eight;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.tasks().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals("estimatedHours", result.errors().get(0).field());
    }

    @Test
    void unknownStatusProducesError() throws IOException {
        Path file = write(HEADER, "T-101;Prepare release;backend;8;UNKNOWN");

        LoadResult result = loader.load(file);

        assertTrue(result.tasks().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals("status", result.errors().get(0).field());
    }

    @Test
    void wrongFieldCountProducesError() throws IOException {
        Path file = write(HEADER, "T-101;Prepare release;backend;8");

        LoadResult result = loader.load(file);

        assertTrue(result.tasks().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals("row", result.errors().get(0).field());
    }

    @Test
    void goodAndBadRowsTogether() throws IOException {
        Path file = write(
                HEADER,
                "T-101;Prepare release;backend;8;READY",
                "T-102;bad hours;backend;NaN;READY",
                "T-103;Write docs;docs;3;DONE");

        LoadResult result = loader.load(file);

        assertEquals(2, result.tasks().size());
        assertEquals(1, result.errors().size());
        assertEquals(3, result.errors().get(0).lineNumber());
    }

    @Test
    void missingFileThrowsDataLoadingException() {
        Path missing = tempDir.resolve("nope.txt");

        DataLoadingException ex = assertThrows(DataLoadingException.class,
                () -> loader.load(missing));

        assertTrue(ex.getMessage().contains(missing.toString()));
        assertNotNull(ex.getCause());
        assertInstanceOf(java.nio.file.NoSuchFileException.class, ex.getCause());
    }

    @Test
    void headerCheckedSeparately() throws IOException {
        Path file = write("id;title;status", "T-101;Prepare release;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.tasks().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals("header", result.errors().get(0).field());
    }
}