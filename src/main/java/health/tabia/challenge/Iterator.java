package health.tabia.challenge;

import java.time.LocalDate;
import java.util.ArrayList;

public class Iterator implements MetricIterator {

    private ArrayList<Metric> metrics;
    private int cursor = 0;

    Iterator(ArrayList<Metric> metrics) {
        this.metrics = metrics;
    }

    public boolean moveNext() {
        cursor++;
        if (cursor >= this.metrics.size()) {
            return false;
        }
        return true;
    }

    public Metric current() {
        return this.metrics.get(cursor);
    }

    public void remove() {
        metrics.remove(cursor);
    }

    public int getCursor() {
        return cursor;
    }

    public void close() throws Exception {
        metrics.clear();
    }

}