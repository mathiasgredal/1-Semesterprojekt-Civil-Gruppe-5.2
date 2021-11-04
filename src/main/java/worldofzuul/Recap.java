package worldofzuul;

import java.util.HashMap;
import java.util.Map;

public class Recap {
    enum DataPoint {
        Emissions,
        SoldEnergy,
        SoldEnergyPrice
    }

    // This stores all the recap data
    private Map<DataPoint, Map<Integer, Double>> data;

    public Recap() {
        data = new HashMap<>() {{
            put(DataPoint.Emissions, new HashMap<>());
            put(DataPoint.SoldEnergy, new HashMap<>());
            put(DataPoint.SoldEnergyPrice, new HashMap<>());
        }};
    }

    public void addDataPoint(DataPoint type, int year, double value) {
        data.get(type).put(year, value);
    }

    public void getDataPoint(DataPoint type, int year) {
        data.get(type).get(year);
    }

    public double sumDataPoint(DataPoint type) {
        return data.get(type).values().stream().mapToDouble(d -> d).sum();
    }
}
