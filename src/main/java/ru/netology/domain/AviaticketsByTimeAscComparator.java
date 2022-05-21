package ru.netology.domain;

import java.util.Comparator;

public class AviaticketsByTimeAscComparator implements Comparator<AviaticketsSales> {

    @Override
    public int compare(AviaticketsSales o1, AviaticketsSales o2) {
        return o1.getTravelTime() - o2.getTravelTime();
    }
}
