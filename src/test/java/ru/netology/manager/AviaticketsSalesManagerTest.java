package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsSales;
import ru.netology.repository.AviaticketsSalesRepository;
import ru.netology.domain.AviaticketsByTimeAscComparator;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsSalesManagerTest {
    private AviaticketsSalesRepository repository = new AviaticketsSalesRepository();
    private AviaticketsSalesManager manager = new AviaticketsSalesManager(repository);
    private AviaticketsSales first = new AviaticketsSales(1, 31000, "KUF", "BEG", 200);
    private AviaticketsSales second = new AviaticketsSales(2, 48100, "KUF", "TIA", 200);
    private AviaticketsSales third = new AviaticketsSales(3, 29865, "KUF", "SKP", 200);
    private AviaticketsSales fourth = new AviaticketsSales(3, 32000, "KUF", "SKP", 210);

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
        AviaticketsSales[] actual = manager.findAll("KUF", "BEG");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsIfContained() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        AviaticketsSales[] expected = new AviaticketsSales[]{third, fourth};
        AviaticketsSales[] actual = manager.findAll("KUF", "SKP");
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

    @Test
    public void shouldSortTicketsByTravelTime() {
        manager.add(third);
        manager.add(fourth);
        AviaticketsByTimeAscComparator comparator = new AviaticketsByTimeAscComparator();
        AviaticketsSales[] expected = new AviaticketsSales[]{third, fourth};
        AviaticketsSales[] actual = manager.findTicketsByTime("KUF", "SKP", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeEmptyIfNoTickets() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        AviaticketsByTimeAscComparator comparator = new AviaticketsByTimeAscComparator();
        AviaticketsSales[] expected = new AviaticketsSales[]{};
        AviaticketsSales[] actual = manager.findTicketsByTime("DME", "KZN", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneIfContained() {
        manager.add(first);
        manager.add(fourth);
        AviaticketsByTimeAscComparator comparator = new AviaticketsByTimeAscComparator();
        AviaticketsSales[] expected = new AviaticketsSales[]{fourth};
        AviaticketsSales[] actual = manager.findTicketsByTime("KUF", "SKP", comparator);
        assertArrayEquals(expected, actual);
    }

}

