package jquon_a2;

/**
 * Assignment 2
 * @author Jason
 */
public class Jquon_A2 {

    /**
     * main program creates a portfolio object and runs the command loop
     * @param args - name of the text file to read from and write to
     */
    public static void main(String[] args) {
        portfolio port = new portfolio();
        port.run(args[0]);
    }
    
}
