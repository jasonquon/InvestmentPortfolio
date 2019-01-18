package jquon_a2;
import java.io.*;
import java.util.*;

/**
 * portfolio class utilizes the stock and mutual fund classes to perform the simulation
 * @author Jason
 */
public class portfolio {
    private ArrayList<Investment> list;
    private HashMap<String, ArrayList<Integer>> map;
    
    /*
    *Portfolio constructor that initializes a list of stcks and a list of mutual funds
    */
    public portfolio(){
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    }
    
    Scanner s = new Scanner(System.in);

    /*
    *run() method contains the command loop for the main program
    */
    public void run(String fileName) {
        
        boolean exit = false;
        int option;
        String file = fileName;
        
        loadFile(file, list);
        updateMap(map, list);
        
        while (exit == false) {
            
            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. Update");
            System.out.println("4. See total portfolio gain");
            System.out.println("5. Search");
            System.out.println("6. Quit");
            System.out.println("Enter an option.");
            
            String choice = s.nextLine();
                
            if (choice.equals("1") || choice.toLowerCase().equals("b") || choice.toLowerCase().equals("buy")) {
                option = 0;
                System.out.println("Enter 1 to buy stocks or 2 to buy mutual funds.");
                while (option < 1 || option > 2) {
                    try{
                        option = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Invalid option. Enter 1 or 2.");
                        continue;
                    }
                    if (option != 1 && option != 2){
                        System.out.println("Enter 1 or 2.");
                    }
                }
                  
                buyInvestment(option, list);
                    
                /*Update hash map*/
                updateMap(map, list);
                    
            }
                    
            else if (choice.equals("2") || choice.toLowerCase().equals("sell")) {
                option = 0;
                System.out.println("Enter 1 to sell stocks or 2 to sell mutual funds.");
                while (option < 1 || option > 2) {
                    try{
                        option = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Invalid option");
                        continue;
                    }
                    if (option != 1 && option != 2){
                        System.out.println("Enter 1 or 2.");
                    }
                }
                    
                String tempString = "";
                System.out.println("Enter symbol.");
                tempString = s.nextLine();
                while (tempString.length() == 0){
                    System.out.println("Invalid symbol. Enter symbol.");
                    tempString = s.nextLine();
                }
                  
                int tempInt = 0;
                System.out.println("Enter the quantity.");
                while(tempInt < 1){
                    try{
                        tempInt = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Not a valid quantity. Enter the quantity.");
                        continue;
                    }
                    if (tempInt < 1){
                        System.out.println("Enter a quantity greater than 0");
                    }
                }
                    
                double tempPrice = -1;
                System.out.println("Enter Price: ");
                while(tempPrice < 0){
                    try{
                        tempPrice = Double.parseDouble(s.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Not a valid price");
                    }
                    if (tempPrice < 0){
                        System.out.println("Please enter a price greater than or equal to 0: ");
                    }
                }

                sellInvestment(option, list, tempString, tempInt, tempPrice);
                   
                /*Update hash map*/
                map.clear();
                updateMap(map, list);
            }        
                                
            else if (choice.equals("3") || choice.toLowerCase().equals("u") || choice.toLowerCase().equals("update")) {
                
                if (!list.isEmpty()){
                    updatePrices(list);
                }
                    
            }                    
            
            else if (choice.equals("4") || choice.toLowerCase().equals("g") || choice.toLowerCase().equals("gain")) {
             
                if (!list.isEmpty()) {
                    double gain = getGain(list);
                    System.out.println("Total gain: " + gain);
                }
                    
                else {
                    System.out.println("No investments have been entered.");
                }
                
            }        
                                
            else if (choice.equals("5") || choice.toLowerCase().equals("search")) {
             
                search(list); 
                    
            }
                    
            else if (choice.equals("6") || choice.toLowerCase().equals("q") || choice.toLowerCase().equals("quit")) {
                    System.out.println("Exiting");
                    exit = true;
                    
                    saveFile(file, list);
                    
                    for(int i = 0; i < list.size(); i++){
                        System.out.println(list.get(i));
                    }
                    
            }
                    
            else {
                System.out.println("Invalid input");
                    
            }
        }
    }
    
    /**
     * buyInvestment() allows user to buy stock by creating a new list element or adding to a pre-existing one
     * @param option - user input to determine if stock or mutual fund is being bought
     * @param list - list of investments
     */
    public void buyInvestment(int option, ArrayList<Investment> list) {
               
        String symbol = "";
        System.out.println("Enter symbol.");
        symbol = s.nextLine();
        while (symbol.length() == 0){
            System.out.println("Invalid symbol. Enter symbol.");
            symbol = s.nextLine();
        }
        
        String name = "";
        System.out.println("Enter name.");
        name = s.nextLine();
        while (name.length() == 0){
            System.out.println("Invalid name. Enter name.");
            name = s.nextLine();
        } 
        
        int quantity = 0;
        System.out.println("Enter the quantity.");
        while(quantity < 1){
            try{
                quantity = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Not a valid quantity. Enter the quantity.");
                continue;
            }
            if (quantity < 1){
                System.out.println("Enter a quantity greater than 0.");
            }
        }  
    
        double price = -1;
        System.out.println("Enter Price: ");
        while(price < 0){
            try{
                price = Double.parseDouble(s.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Not a valid price");
            }
            if (price < 0){
                System.out.println("Please enter a price greater than or equal to 0: ");
            }
        }
       
        int match = -1;
        boolean typeMatch = true;
        
        if (option == 1) {
            stock tempStock = new stock(symbol, name, quantity, price);

           for(int i = 0; i < list.size(); i++){
                if(symbol.equals(list.get(i).getSymbol()) && (list.get(i).getClass().equals(tempStock.getClass()))) {
                    match = i;
                }
                if(symbol.equals(list.get(i).getSymbol()) && !(list.get(i).getClass().equals(tempStock.getClass()))) {
                    typeMatch = false;
                }
            }

            if (typeMatch == true) {
                if(match == -1){
                    list.add(tempStock);
                } else {
                    list.get(match).setQuantity(list.get(match).getQuantity() + tempStock.getQuantity());
                    list.get(match).setPrice(tempStock.getPrice());
                    list.get(match).setBookValue(list.get(match).getBookValue() + (tempStock.getQuantity() * tempStock.getPrice()) + 9.99);
                }
            }
            else {
                System.out.println("Investment already exists as mutual fund.");
            }
        }
        
        else {
            mutualFund tempMF = new mutualFund(symbol, name, quantity, price);

           for(int i = 0; i < list.size(); i++){
                if(symbol.equals(list.get(i).getSymbol()) && (list.get(i).getClass().equals(tempMF.getClass()))) {
                    match = i;
                }
                if(symbol.equals(list.get(i).getSymbol()) && !(list.get(i).getClass().equals(tempMF.getClass()))) {
                    typeMatch = false;
                }
            }

            if (typeMatch == true) {
                if(match == -1){
                    list.add(tempMF);
                } else {
                    list.get(match).setQuantity(list.get(match).getQuantity() + tempMF.getQuantity());
                    list.get(match).setPrice(tempMF.getPrice());
                    list.get(match).setBookValue((list.get(match).getQuantity() * list.get(match).getPrice()) + 9.99);
                }
            }
            else {
                System.out.println("Investment already exists as a stock.");
            }
        }
    }
    
    /**
     * sellStock() allows the user to sell stocks if a quantity has previously been bought
     * @param option - determines whether the user is selling a stock or mutual fund
     * @param list - list of investments
     * @param tempString - user inputted symbol
     * @param tempInt - user inputted quantity
     * @param tempPrice - user inputted price
     */
    public void sellInvestment(int option, ArrayList<Investment> list, String tempString, int tempInt, double tempPrice) {
        boolean match = false;
        stock tempStock = new stock();
        mutualFund tempMF = new mutualFund();
        for (int i = 0; i < list.size(); i++) {
            /*Checking if investment exists in list*/
            if (tempString.equals(list.get(i).getSymbol())) {
                if ((option == 1 && list.get(i).getClass().equals(tempStock.getClass())) || (option == 2 && list.get(i).getClass().equals(tempMF.getClass()))) {
                    match = true;
                }
                if (match == true) {
                    /*Checking if the user is selling a valid quantity*/
                    if (tempInt <= list.get(i).getQuantity()) {

                        if (list.get(i).getQuantity() - tempInt == 0) {
                            /*Removing investment from list if entire quantity is sold*/
                            list.remove(i);
                        } else {
                            /*Updating instance variables*/
                            list.get(i).setQuantity(list.get(i).getQuantity() - tempInt);
                            double tempNum = list.get(i).getQuantity();
                            double tempDen = list.get(i).getQuantity() + tempInt;
                            list.get(i).setBookValue(list.get(i).getBookValue() * (double)(tempNum / tempDen));
                            list.get(i).setPrice(tempPrice);
                        }
                        System.out.format("Payment received: $%.2f\n", (tempInt * tempPrice - 9.99));

                    } else {
                        System.out.println("Inadequate quantity.");
                    }
                }
                else {
                    System.out.println("Investment not found.");
                }
            }
        }
    }
    
    /**
     * updatePrices() allows the user to update the price of every investment in a list
     * @param list - list of investments
     */
    public void updatePrices(ArrayList<Investment> list){
        System.out.println("Updating investment prices...");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Investment Symbol: " + list.get(i).getSymbol());
            System.out.println("Enter new price");
            double newPrice = -1;
            while(newPrice < 0){
                try{
                    newPrice = Double.parseDouble(s.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Not a valid price");
                }
                if (newPrice < 0){
                    System.out.println("Please enter a price greater than or equal to 0: ");
                }
            }
            list.get(i).setPrice(newPrice);
        }
    }
    
    /**
     * getGain - computes the gain from stocks and mutual funds
     * @param list - list of stocks
     * @return a double representing the gain from stocks
     */
    public double getGain(ArrayList<Investment> list) {
        double gain = 0;
        stock tempStock = new stock();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getClass().equals(tempStock.getClass())) {
                gain += ((list.get(i).getQuantity() * list.get(i).getPrice()) - 9.99) - list.get(i).getBookValue();
            }
            else {
                gain += ((list.get(i).getQuantity() * list.get(i).getPrice()) - 45) - list.get(i).getBookValue();
            }
        }
        return gain;
    }
    
    /**
     * search() outputs any stocks or mutual funds that match a user inputted price range or key words
     * @param list - list of investments
     */
    public void search(ArrayList<Investment> list){
        String searchKey = "";
        System.out.println("Enter a search term.");
        searchKey = s.nextLine();
        
        /*Return all investments if empty string is entered*/
        if (searchKey.length() == 0){
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }
                    
            return;
        }
        
        /*Check if user input is symbol*/
        for (int i = 0; i < list.size(); i++){
            if (searchKey.equals(list.get(i).getSymbol())){
                System.out.println(list.get(i));
                return;
            }
        }
        
        /*Check if user input is a price range*/
        if (searchKey.contains("-")){
            boolean exit = false;
            StringTokenizer tok = new StringTokenizer(searchKey, "-", true);
            int count = tok.countTokens();
            
            /*3 tokens means a high and low value is given*/
            if (count == 3){
                double low = 0;
                double high = 0;
                String tempString;
                tempString = tok.nextToken();
                try {
                    low = Double.parseDouble(tempString);
                } catch (NumberFormatException e) {
                    exit = true;
                }
                tempString = tok.nextToken();
                tempString = tok.nextToken();
                try {
                    high = Double.parseDouble(tempString);
                } catch (NumberFormatException e) {
                    exit = true;
                }
                
                /*Check prices if tokens are successfully parsed to double*/
                if (!exit){
                    for (int i = 0; i < list.size(); i++){
                        if (list.get(i).getPrice() >= low && list.get(i).getPrice() <= high){
                            System.out.println(list.get(i));
                        }
                    }
                } 
            }
            
            /*Search prices given only low or high bound*/
            else if (count == 2){
                double low = 0;
                double high = 0;
                String tempString;
                tempString = tok.nextToken();
                
                /*Search given high bound*/
                if (tempString.equals("-")){
                    tempString = tok.nextToken();
                    try {
                        high = Double.parseDouble(tempString);
                        for (int i = 0; i < list.size(); i++){
                            if (list.get(i).getPrice() >= low && list.get(i).getPrice() <= high){
                                System.out.println(list.get(i));
                            }
                        }
                    } catch (NumberFormatException e) {
                        
                    }
                    
                /*Search given low bound*/
                } else {
                    try {
                        low = Double.parseDouble(tempString);
                        for (int i = 0; i < list.size(); i++){
                            if (list.get(i).getPrice() >= low){
                                System.out.println(list.get(i));
                            }
                        }
                    } catch (NumberFormatException e) {
                        
                    }
                }
            }
        }
        
        else {
            /*Searches exact prices*/
            double searchPrice = 0;
            try {
                searchPrice = Double.parseDouble(searchKey);
            } catch (NumberFormatException e) {
                
            }
            
            for (int i = 0; i < list.size(); i++){
                if (searchPrice == list.get(i).getPrice()){
                    System.out.println(list.get(i));
                }
            }
            
            /*Searches for search key in name instance variable*/
            ArrayList<Integer> indexList;
            ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
            ArrayList<Integer> indices = new ArrayList<>();
            for (String key: searchKey.split("[\\.,\\s!;?:\"]+")) {
                indexList = map.get(key.toLowerCase());
                if (indexList != null) {
                    finalList.add(indexList);
                }
            }
            
            /*Find intersection of all index arrays*/
            for (int i = 0; i < finalList.get(0).size(); i++) {
                indices.add(finalList.get(0).get(i));
            }
            
            for (int j = 1; j < finalList.size(); j++) {
                indices.retainAll(finalList.get(j));
            }
            
            for (int k = 0; k < indices.size(); k++) {
                System.out.println(list.get(indices.get(k)));
            }
            
        }
        
    }
    
    /**
     * loadFile() loads data from a text file into the portfolio list
     * @param fileName - file to load from
     * @param list - list to add investments to
     */
    public void loadFile(String fileName, ArrayList<Investment> list) {

        BufferedReader reader;
        
        try{
            reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();                
            if (line != null) {
                do {
                    String symbol = null;
                    String name = null;
                    int quantity = -1;
                    double price = -1;
                    double bookValue= -1;
                    
                    /*Create stock object from file data*/
                    if (line.equals("Stock")) {
                        int count = 0;
                        while (count < 5) {
                            line = "";
                            line = reader.readLine();
                            String[] toks = line.split(":");
                            if (count == 0) {
                                symbol = toks[1];
                            }
                            else if (count == 1) {
                                name = toks[1];
                            }
                            else if (count == 2) {
                                quantity = Integer.parseInt(toks[1]);
                            }
                            else if (count == 3) {
                                price = Double.parseDouble(toks[1]);
                            }
                            else {
                                bookValue = Double.parseDouble(toks[1]);
                            }
                            count++;
                        }
                        stock toAdd = new stock(symbol, name, quantity, price);
                        toAdd.setBookValue(bookValue);
                        list.add(toAdd);            
                    }
                    
                    /*Create mutual fund object from file data*/
                    else {
                        int count = 0;
                        while (count < 5) {
                            line = "";
                            line = reader.readLine();
                            String[] toks = line.split(":");
                            if (count == 0) {
                                symbol = toks[1];
                            }
                            else if (count == 1) {
                                name = toks[1];
                            }
                            else if (count == 2) {
                                quantity = Integer.parseInt(toks[1]);
                            }
                            else if (count == 3) {
                                price = Double.parseDouble(toks[1]);
                            }
                            else {
                                bookValue = Double.parseDouble(toks[1]);
                            }
                            count++;
                        }
                        mutualFund toAdd = new mutualFund(symbol, name, quantity, price);
                        toAdd.setBookValue(bookValue);
                        list.add(toAdd);
                    }
                    line = reader.readLine();
                } while (line != null);
            }
            /*Output message if file is empty*/
            else {
                System.out.println("No data to load.");
            }
            reader.close();
            
        /*Output message if file is not found*/
        } catch(IOException e){
            System.out.println("Failed to read " + fileName);
        }
    }
    
    /**
     * saveFile() saves the current portfolio to a text file
     * @param fileName - file to write to
     * @param list - list of investments
     */
    public void saveFile(String fileName, ArrayList<Investment> list) {
        
        BufferedWriter writer;
        
        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            stock tempStock = new stock();
            mutualFund tempMF = new mutualFund();
            
            /*Write all data to text file*/
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().equals(tempStock.getClass())) {
                    writer.write("Stock\n");
                    tempStock = (stock) list.get(i);
                    writer.write(tempStock.toString());
                }
                else {
                    writer.write("Mutual Fund\n");
                    tempMF = (mutualFund) list.get(i);
                    writer.write(tempMF.toString());
                }
            }
            writer.close();
            
        /*Output message if file cannot be written to*/
        } catch(IOException e){
            System.out.println("Failed to write to " + fileName);
        }
    }
    
    /**
     * updateMap() creates a hash map of name keywords mapped to the index of the investment in the list
     * @param map - hash map to update
     * @param list - list of investments
     */
    public void updateMap(HashMap<String, ArrayList<Integer>> map, ArrayList<Investment> list) {
 
        ArrayList<Integer> indexList;
        
        /*Add every investment to hash map*/
        for (int i = 0; i < list.size(); i++) {
            for (String key: list.get(i).getName().toLowerCase().split("[\\.,\\s!;?:\"]+")) {
                
                /*Add index to list if key already exists*/
                if (map.containsKey(key)) {
                    indexList = map.get(key);
                    if (!indexList.contains(i)) {
                        indexList.add(i);
                    }
                }
                
                /*Add key/value to list if it does not already exist*/
                else {
                    indexList = new ArrayList<>();
                    indexList.add(i);
                    map.put(key, indexList);
                }
            }
        }
    }
}
