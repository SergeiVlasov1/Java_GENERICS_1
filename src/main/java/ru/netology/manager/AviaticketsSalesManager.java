package ru.netology.manager;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.AviaticketsSales;
import ru.netology.repository.AviaticketsSalesRepository;

import java.util.Arrays;
import java.util.Comparator;

@Data
@NoArgsConstructor
public class AviaticketsSalesManager {
    private AviaticketsSalesRepository repository;

    public AviaticketsSalesManager(AviaticketsSalesRepository repository) {
        this.repository = repository;
    }

    public void add(AviaticketsSales item) {
        repository.save(item);
    }

    public AviaticketsSales[] findAll(String departureAirport, String arrivalAirport) {
        AviaticketsSales[] result = new AviaticketsSales[0];
        for (AviaticketsSales item : repository.findAll()) {
            if (item.getDepartureAirport().contains(departureAirport) && item.getArrivalAirport().contains(arrivalAirport)) {
                AviaticketsSales[] tmp = new AviaticketsSales[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public AviaticketsSales[] findTicketsByTime(String departureAirport, String arrivalAirport, Comparator<AviaticketsSales> comparator) {
        AviaticketsSales[] result = new AviaticketsSales[0];
        for (AviaticketsSales item : repository.findAll()) {
            if (item.getDepartureAirport().contains(departureAirport) && item.getArrivalAirport().contains(arrivalAirport)) {
                AviaticketsSales[] tmp = new AviaticketsSales[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

}
