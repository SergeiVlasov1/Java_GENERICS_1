package ru.netology.domain;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AviaticketsSalesTest {
    private AviaticketsSales first = new AviaticketsSales(1, 31000, "KUF", "BEG", 200);
    private AviaticketsSales second = new AviaticketsSales(2, 48100, "KUF", "TIA", 200);
    private AviaticketsSales third = new AviaticketsSales(3, 29865, "KUF", "SKP", 200);

    @Test
    public void shouldSortTicketsByPrice() {
        AviaticketsSales[] expected = new AviaticketsSales[]{third, first, second};
        AviaticketsSales[] actual = new AviaticketsSales[]{first, second, third};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}

