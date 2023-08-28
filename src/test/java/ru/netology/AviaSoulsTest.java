package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

class AviaSoulsTest {
    AviaSouls manager = new AviaSouls();
    TicketTimeComparator ticketComparator = new TicketTimeComparator();
    Ticket ticket1 = new Ticket("Санкт-Петербург", "Москва", 5_000, 19, 20);
    Ticket ticket2 = new Ticket("Москва", "Сочи", 7_000, 16, 19);
    Ticket ticket3 = new Ticket("Екатеринбург", "Челябинск", 10_000, 11, 13);
    Ticket ticket4 = new Ticket("Сочи", "Краснодар", 7_000, 16, 17);
    Ticket ticket5 = new Ticket("Сочи", "Краснодар", 12_000, 12, 16);
    Ticket ticket6 = new Ticket("Сочи", "Краснодар", 9_500, 18, 20);

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }
    @Test
    public void shouldCompareWithMin() {
        int expected = -1;
        int actual = ticket4.compareTo(ticket3);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldCompareTwoIdentical() {
        int expected = 0;
        int actual = ticket2.compareTo(ticket4);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldCompareWithMax() {
        int expected = 1;
        int actual = ticket3.compareTo(ticket1);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldComparatorWithMin() {
        int expected = -1;
        int actual = ticketComparator.compare(ticket6, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTheComparatorTwoIdentical() {
        int expected = 0;
        int actual = ticketComparator.compare(ticket4, ticket1);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldComparatorWithMax() {
        int expected = 1;
        int actual = ticketComparator.compare(ticket2, ticket3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllTickets() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = AviaSouls.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareToOrderingAll() {
        Ticket[] expected = {ticket4, ticket5, ticket6};
        Ticket[] actual = manager.search("Сочи", "Краснодар");
        Assertions.assertArrayEquals(expected, actual);
    }
    /*
    @Test
    public void shouldSearch() {
        Ticket[] expected = {ticket4, ticket5, ticket6};
        Ticket[] actual = manager.search("Сочи", "Краснодар");
        Assertions.assertArrayEquals(expected, actual);
    }
 */
    @Test
    public void shouldSearchWithOneTicket() {
        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.search("Санкт-Петербург", "Москва");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchWithNoTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Санкт-Петербург", "Сочи");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchAndSort() {
        Ticket[] expected = {ticket4, ticket6, ticket5};
        Ticket[] actual = manager.searchAndSortBy("Сочи", "Краснодар", ticketComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchAndSortWithOneTicket() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.searchAndSortBy("Екатеринбург", "Челябинск", ticketComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchAndSortWithNoTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Челябинск", ticketComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}

