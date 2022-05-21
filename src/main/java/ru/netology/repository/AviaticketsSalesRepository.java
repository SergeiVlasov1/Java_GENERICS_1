package ru.netology.repository;


import ru.netology.domain.AviaticketsSales;

public class AviaticketsSalesRepository {
    private AviaticketsSales[] items = new AviaticketsSales[0];

    public void save(AviaticketsSales item) {
        int length = items.length + 1;
        AviaticketsSales[] tmp = new AviaticketsSales[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public AviaticketsSales[] findAll() {
        return items;
    }

    public AviaticketsSales findById(int id) {
        for (AviaticketsSales item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = items.length - 1;
        AviaticketsSales[] tmp = new AviaticketsSales[length];
        int index = 0;
        for (AviaticketsSales item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}
