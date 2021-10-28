package worldofzuul;

public class House extends Room{
    private int energyNeed;

    public House(String description, int energyNeed){
        super(description);
        this.energyNeed = energyNeed;
    }

    public int getEnergyNeed() {
        return energyNeed;
    }

    public void setEnergyNeed(int energyNeed) {
        this.energyNeed = energyNeed;
    }
}
