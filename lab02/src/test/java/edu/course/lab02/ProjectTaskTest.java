package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTaskTest {

    @Test
    void созданиеКорректнойЗадачи() {
        ProjectTask task = new ProjectTask("T-1", "Написать отчёт", 8);
        assertEquals("T-1", task.getId());
        assertEquals("Написать отчёт", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(8, task.getEstimatedHours());
        assertFalse(task.isDone());
    }

    @Test
    void пустойИдентификаторВыбрасываетИсключение() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("", "Задача", 5));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("   ", "Задача", 5));
    }

    @Test
    void nullИдентификаторВыбрасываетИсключение() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Задача", 5));
    }

    @Test
    void пустоеНазваниеВыбрасываетИсключение() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "   ", 5));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", null, 5));
    }

    @Test
    void неположительнаяОценкаВыбрасываетИсключение() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "Задача", 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T-1", "Задача", -3));
    }

    @Test
    void сменаСтатусаРаботает() {
        ProjectTask task = new ProjectTask("T-1", "Задача", 5);
        task.changeStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        task.changeStatus(TaskStatus.DONE);
        assertTrue(task.isDone());
    }

    @Test
    void nullСтатусВыбрасываетИсключение() {
        ProjectTask task = new ProjectTask("T-1", "Задача", 5);
        assertThrows(IllegalArgumentException.class, () -> task.changeStatus(null));
    }

    @Test
    void увеличениеОценкиРаботает() {
        ProjectTask task = new ProjectTask("T-1", "Задача", 5);
        task.increaseEstimate(3);
        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void неположительноеУвеличениеОценкиВыбрасываетИсключение() {
        ProjectTask task = new ProjectTask("T-1", "Задача", 5);
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(0));
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(-2));
    }
}