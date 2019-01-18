package jquon_a2;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Investment {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
        
    public Investment(String symbol, String name, int quantity, double price) {
        
        if (symbol == null) {
            this.symbol = "";
        }
        else {
            this.symbol = symbol;
        }
        if (name == null) {
            this.name = "";
        }
        else {
            this.name = name;
        }
        if (quantity < 0) {
            this.quantity = 0;
        }
        else {
            this.quantity = quantity;
        }
        if (price < 0) {
            this.price = 0;
        }
        else {
            this.price = price;
        }

    }
    
    public Investment() {
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0;
        this.bookValue = 0;
    }
    
    /*Accessors and muators*/
    public String getSymbol(){
        return symbol;
    }
       
    public String getName(){
        return name;
    }
        
    public int getQuantity(){
        return quantity;
    }
       
    public double getPrice(){
        return price;
    }
        
    public double getBookValue(){
        return bookValue;
    }
      
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
        
    public void setName(String name){
        this.name = name;
    }
        
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
       
    public void setPrice(double price){
        this.price = price;
    }
        
    public void setBookValue(double bookValue){
        this.bookValue = bookValue;
    }
        
    public boolean equals(stock compare){
        return this.getSymbol().equals(compare.getSymbol());
    }
    
    @Override
    public String toString() {
        String formattedPrice = String.format("%.02f", price);
        String formattedVal = String.format("%.02f", bookValue);
        return "Symbol:" + symbol + "\n"+ "Name:" + name + "\n" + "Quantity:" + quantity + "\n" + "Price:" + formattedPrice + "\n" + "Book value:" + formattedVal + "\n";
    }
    
}

