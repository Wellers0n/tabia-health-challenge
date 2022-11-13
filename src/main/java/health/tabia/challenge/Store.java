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

    private int binarySearch(long timestampTarget) {
        if (store.isEmpty()) {
            return 0;
        }

        int left = 0;
        int right = store.size() - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            long timestamp = store.get(mid).getTimestamp();
            if (timestamp == timestampTarget) {
                ans = mid;
                right = mid - 1;
            } else if (timestamp > timestampTarget) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
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
        int end = binarySearch(to) + 1;

        ArrayList<Metric> list = new ArrayList<Metric>(metricsIterator.getStore().subList(start, end));

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
