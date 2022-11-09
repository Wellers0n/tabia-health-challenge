package health.tabia.challenge;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

        Date start = new Date(from);
        Date end = new Date(to);

        Date storeDate = new Date(metricsIterator.current().getTimestamp());

        for (int i = 0; i < store.size(); i++) {

            if (storeDate.after(start) && storeDate.before(end)) {
                if (name == "") {

                    System.out.println(metricsIterator.current().getName());
                    metricsIterator.moveNext();

                } else if (metricsIterator.current().getName() == name) {

                    System.out.println(metricsIterator.current().getName());
                    metricsIterator.moveNext();
                } else {
                    metricsIterator.remove();
                    metricsIterator.moveNext();
                }
            }
        }

        try {
            metricsIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return metricsIterator;
    }
}
