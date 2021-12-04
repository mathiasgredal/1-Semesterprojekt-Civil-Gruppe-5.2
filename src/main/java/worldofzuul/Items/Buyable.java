package worldofzuul.Items;

/**
 * An interface for shop items
 */
public interface Buyable {

    /**
     * Gets the name of the item
     */
    String getName();

    /**
     * The price of the item in dkk
     */
    double getPrice();

    /**
     * Short info string about the item.
     * Used as a store label for listing shop items
     */
    String getInfo();

    /**
     *
     * Gets the output in kWh for the item
     */
}
