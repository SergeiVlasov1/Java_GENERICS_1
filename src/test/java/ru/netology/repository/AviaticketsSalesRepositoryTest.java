package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AviaticketsSales;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsSalesRepositoryTest {
    private AviaticketsSalesRepository repository = new AviaticketsSalesRepository();
    private AviaticketsSales first = new AviaticketsSales(1, 31000, "KUF", "BEG", 200);
    private AviaticketsSales second = new AviaticketsSales(2, 48100, "KUF", "TIA", 200);
    private AviaticketsSales third = new AviaticketsSales(3, 29865, "KUF", "SKP", 200);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    public void shouldFindAllTickets() {
        AviaticketsSales[] expected = new AviaticketsSales[]{first, second, third};
        AviaticketsSales[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfExist() {
        int idToFind = 3;
        AviaticketsSales expected = third;
        AviaticketsSales actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfNotExist() {
        int idToFind = 5;
        AviaticketsSales expected = null;
        AviaticketsSales actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfExist() {
        int removeId = 2;
        repository.removeById(removeId);
        AviaticketsSales[] expected = new AviaticketsSales[]{first, third};
        AviaticketsSales[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByIdIfNotExist() {
        int removeId = 5;
        assertThrows(NotFoundException.class, () -> repository.removeById(removeId));
    }
}

