package jquon_a2;

/**
 * Mutual fund object class
 * @author Jason
 */
public class mutualFund extends Investment {
    
    /**
     * Constructor for mutual fund object
     * @param symbol - mutual fund symbol
     * @param name - mutual fund name
     * @param quantity - mutual fund quantity
     * @param price - mutual fund price
     */
    public mutualFund(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        this.setBookValue(this.getPrice() * this.getQuantity());
    }
    
    /**
     * No argument constructor for mutual fund object
     */
    public mutualFund() {
        super();
        this.setBookValue(0);
    }
}
