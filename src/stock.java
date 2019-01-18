package jquon_a2;

/**
 * Stock object class
 * @author Jason
 */
public class stock extends Investment {
    
    /**
     * Constructor for stock class
     * @param symbol - stock symbol
     * @param name - stock name
     * @param quantity - quantity of stocks
     * @param price - price of stock
     */
    public stock(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.setBookValue((this.getPrice() * this.getQuantity()) + 9.99);
    }
    
    /**
     * No argument constructor for stock class
     */
    public stock() {
        super();
        this.setBookValue(0);
    }
    
    
}
