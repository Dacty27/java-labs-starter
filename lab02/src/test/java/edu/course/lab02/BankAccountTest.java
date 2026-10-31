package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void успешноеСписаниеУменьшаетБаланс() {
        BankAccount account = new BankAccount(100);
        account.withdraw(40);
        assertEquals(60, account.getBalance());
    }

    @Test
    void списаниеБольшеБалансаВыбрасываетИсключение() {
        BankAccount account = new BankAccount(50);
        assertThrows(IllegalStateException.class, () -> account.withdraw(51));
    }

    @Test
    void отрицательныйНачальныйБалансВыбрасываетИсключение() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void неположительноеПополнениеВыбрасываетИсключение() {
        BankAccount account = new BankAccount(10);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5));
    }

    @Test
    void успешноеПополнениеУвеличиваетБаланс() {
        BankAccount account = new BankAccount(10);
        account.deposit(25);
        assertEquals(35, account.getBalance());
    }
}