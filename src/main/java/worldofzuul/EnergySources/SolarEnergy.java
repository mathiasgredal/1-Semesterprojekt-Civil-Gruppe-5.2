package worldofzuul.EnergySources;

public class SolarEnergy extends EnergySource {
    private static final String solarEnergyName = "Solar Energy";
    private static final int solarEnergyPrice = 30900;
    private static final int solarEnergyEmission = 0;
    private static final int solarEnergyOutput = 1;

    public SolarEnergy() {
        super(solarEnergyName, "Photovoltaic solar panel array producing energy from sunlight", solarEnergyPrice, solarEnergyEmission, solarEnergyOutput);
    }

}