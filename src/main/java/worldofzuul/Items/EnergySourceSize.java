package worldofzuul.Items;

public enum EnergySourceSize {
    SMALL("small"), MEDIUM("medium"), LARGE("large");
    private final String name;

    EnergySourceSize(String name) {
        this.name = name;
    }

    public boolean equalsName(String other) {
        return name.equals(other);
    }

    public String toString() {
        return this.name;
    }

    public String upperCaseName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}