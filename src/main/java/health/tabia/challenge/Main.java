package health.tabia.challenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws ParseException {

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long tsTimeOne = simpleDateFormat.parse("28/01/1998").getTime();
        long tsTimeTwo = simpleDateFormat.parse("25/05/2021").getTime();
        long tsTimeThree = simpleDateFormat.parse("17/07/2022").getTime();

        Metric metricOne = new Metric("Wellerson", tsTimeOne);
        Metric metricTwo = new Metric("Hubert", tsTimeTwo);
        Metric metricThree = new Metric("Darth Vader", tsTimeThree);

        Store store = new Store();

        store.insert(metricOne);
        store.insert(metricTwo);
        store.insert(metricThree);

        store.query("", tsTimeTwo, tsTimeThree);

    }
}