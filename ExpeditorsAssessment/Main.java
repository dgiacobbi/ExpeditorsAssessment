/*
    Name: David Giacobbi
    Date: November 19, 2024

    The purpose of this program is to test the input data provided and ensure the output is correct.
*/

public class Main {

    /*
    Runs the test data on the database object and ensures proper output.
    * @params String array of command line arguments
    * @return n/a
    */
    public static void main(String[] args) {
        // Create a new database with the input text file loaded in
        HousingDatabase testDatabase = new HousingDatabase("input.txt");
        testDatabase.displayDatabase();
    }  
}
