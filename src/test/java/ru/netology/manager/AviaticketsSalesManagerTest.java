package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsSales;
import ru.netology.repository.AviaticketsSalesRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsSalesManagerTest {
    private AviaticketsSalesRepository repository = new AviaticketsSalesRepository();
    private AviaticketsSalesManager manager = new AviaticketsSalesManager(repository);
    private AviaticketsSales first = new AviaticketsSales(1, 31000, "KUF", "BEG", 200);
    private AviaticketsSales second = new AviaticketsSales(2, 48100, "KUF", "TIA", 200);
    private AviaticketsSales third = new AviaticketsSales(3, 29865, "KUF", "SKP", 200);

    @BeforeEach
    public void setUp() {
        manager = new AviaticketsSalesManager(repository);
    }

    @Test
    public void shouldReturnEmptyIfNoTickets() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsSales[] expected = new AviaticketsSales[]{};
        AviaticketsSales[] actual = manager.findAll("KZN", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketIfContained() {
        manager.add(first);
        AviaticketsSales[] expected = new AviaticketsSales[]{first};
        AviaticketsSales[] actual = manager.findAll("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsIfContained() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsSales[] expected = new AviaticketsSales[]{second, first};
        AviaticketsSales[] actual = manager.findAll("DME", "AYT");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsOfDepartureWithoutArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsSales[] expected = new AviaticketsSales[]{};
        AviaticketsSales[] actual = manager.findAll("DME", "KZN");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsWithoutDepartureWithArrivalAirport() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsSales[] expected = new AviaticketsSales[]{};
        AviaticketsSales[] actual = manager.findAll("SVO", "AYT");
        assertArrayEquals(expected, actual);
    }
}

