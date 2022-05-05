import java.io.Serializable;

/**
 * Product class
 */
public class Product extends OperationToProduct implements Serializable {

    /**
     * Default constructor
     */
    String bcode;
    String title;
    int quantity;
    double price;

    public Product() {
    }

    /**
     * Constructor method to initialize a product
     *
     * @param bcode    Product's bar code
     * @param title    Product's title
     * @param quantity Product's quantity
     * @param price    Product's price
     */
    public Product(String bcode, String title, int quantity, double price) {
        this.bcode=bcode;
        this.title=title;
        this.quantity=quantity;
        this.price=price;
    }

    /**
     * Convert this product to String for printing
     */
    @Override
    public String toString() {
        double x= Math.round(this.price*100);
        return this.bcode + " | " + this.title + " | " + this.quantity + " | " +x/100;
    }

}
