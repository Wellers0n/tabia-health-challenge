package health.tabia.challenge;

import java.util.ArrayList;

public class Iterator implements MetricIterator {

    private ArrayList<Metric> metrics;
    private int cursor = 0;

    Iterator(ArrayList<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean moveNext() {
        if (cursor >= this.metrics.size()) {
            return false;
        }
        
        cursor++;

        return true;
    }

    @Override
    public Metric current() {
        return this.metrics.get(cursor);
    }

    @Override
    public void remove() {
        metrics.remove(cursor);
    }

    public int getCursor() {
        return cursor;
    }

    @Override
    public void close() throws Exception {
        metrics.clear();
    }

}