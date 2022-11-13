package health.tabia.challenge;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of the Metric abstraction
 */
public class Store implements MetricStore {
    private final ArrayList<Metric> store = new ArrayList<Metric>();

    private int binarySearch(long timestamp) {
        if (store.isEmpty()) {
            return 0;
        }
        int left = 0;
        int right = store.size() - 1;
        int mid;
        while (right > left) {
            mid = (right + left) / 2;
            long timestampTarget = store.get(mid).getTimestamp();
            if (timestamp == timestampTarget) {
                return mid;
            } else if (timestamp > timestampTarget) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (store.get(left).getTimestamp() > timestamp) {
            return left;
        }
        return left + 1;
    }

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

        // get index start
        int start = binarySearch(from);
        // get index end
        int end = binarySearch(to);

        ArrayList<Metric> list = new ArrayList<Metric>(store.subList(start, end));

        for (int i = 0; i < list.size(); i++) {
            if (name == "") {
                System.out.println(list.get(i).getName());
            } else if (list.get(i).getName() == name) {
                System.out.println(list.get(i).getName());
            }

        }

        return metricsIterator;
    }
}
