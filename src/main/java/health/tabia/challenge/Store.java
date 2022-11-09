package health.tabia.challenge;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of the Metric abstraction
 */
public class Store implements MetricStore {
    private final ArrayList<Metric> store = new ArrayList<Metric>();

    public void insert(Metric metric) {
        store.add(metric);
    }

    public void removeAll(String name) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName() == name) {
                store.remove(i);
            }
            return;
        }

    }

    public MetricIterator query(String name, long from, long to) {
        Iterator metricsIterator = new Iterator(store);

        LocalDate localDateFrom = new Timestamp(from).toLocalDateTime().toLocalDate();
        LocalDate localDateTo = new Timestamp(to).toLocalDateTime().toLocalDate();

        LocalDate localDateStart;
        LocalDate localDateEnd;

        if (localDateFrom.isBefore(localDateTo)) {
            localDateStart = localDateFrom;
            localDateEnd = localDateTo;
        } else {
            localDateStart = localDateTo;
            localDateEnd = localDateFrom;
        }

        List<LocalDate> dateList = localDateStart.datesUntil(localDateEnd).collect(Collectors.toList());

        LocalDate storeDate = new Timestamp(metricsIterator.current().getTimestamp()).toLocalDateTime().toLocalDate();

        for (int i = 0; i < store.size(); i++) {

            if (!dateList.contains(storeDate)) {
                metricsIterator.remove();
            }
            if (name == "") {
                System.out.println(metricsIterator.current().getName());
            } else if (metricsIterator.current().getName() == name) {
                System.out.println(metricsIterator.current().getName());
            }
        }

        return metricsIterator;
    }
}
