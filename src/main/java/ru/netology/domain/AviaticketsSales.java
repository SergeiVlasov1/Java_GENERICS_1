package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AviaticketsSales implements Comparable<AviaticketsSales> {
    private int id;
    private int price;
    private String departureAirport;
    private String arrivalAirport;
    private int travelTime;

    @Override
    public int compareTo(AviaticketsSales o) {
        return price - o.price;
    }

}
