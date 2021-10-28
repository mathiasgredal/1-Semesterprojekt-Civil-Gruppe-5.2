package worldofzuul;

public abstract class EnergySource {
    private String energyName;
    private int energyPrice;
    private int energyEmission;
    private int energyOutput;

    public EnergySource(String energyName, int energyPrice, int energyEmission, int energyOutput){
        this.energyName = energyName;
        this.energyPrice = energyPrice;
        this.energyEmission = energyEmission;
        this.energyOutput = energyOutput;
    }

    public String getEnergyName() {
        return energyName;
    }

    public void setEnergyName(String energyName) {
        this.energyName = energyName;
    }

    public int getEnergyPrice() {
        return energyPrice;
    }

    public void setEnergyPrice(int energyPrice) {
        this.energyPrice = energyPrice;
    }

    public int getEnergyEmission() {
        return energyEmission;
    }

    public void setEnergyEmission(int energyEmission) {
        this.energyEmission = energyEmission;
    }

    public int getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(int energyOutput) {
        this.energyOutput = energyOutput;
    }
}
