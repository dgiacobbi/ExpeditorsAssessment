/*
    Name: David Giacobbi
    Date: November 19, 2024
    Project: Expeditors Technical Assessment

    The purpose of this program is to create the HousingDatabase object that will read in the text file and print out the results
    to the console.
*/

// Scanner and IO used to read over text file
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HousingDatabase {

    // Stores unique households from database
    private Set<Household> householdSet;

    /*
    Constructor for Database. Loads a text file with housing data into each object
    structure.
    * @params String textFile is the file path string to the housing data
    */
    public HousingDatabase(String textFile){
        // Initialize the householdSet private variable
        this.householdSet = new HashSet<Household>();

        // Try to read in the file path
        try {
            // Create a scanner to read through input data
            Scanner fileScan = new Scanner(new File(textFile));

            // Get the next data entry until the end of the text file
            while (fileScan.hasNextLine()) {

                // Parse current data entry into list of values
                String dataEntry = fileScan.nextLine(); 

                // Traverse line and save each value into list
                ArrayList<String> dataValues = new ArrayList<String>();
                int startIdx = 0;
                boolean quotesFound = false;
                // Identify quotations and save each value within double quotes into dataValues
                for (int i=0; i<dataEntry.length(); i++){
                    if (dataEntry.charAt(i) == '\"'){
                        quotesFound = !quotesFound;
                    } else if (dataEntry.charAt(i) == ',' && !quotesFound){
                        dataValues.add(dataEntry.substring(startIdx, i-1));
                        startIdx = i+1;
                    }
                }
                // Add the last entry
                dataValues.add(dataEntry.substring(startIdx));

                // Use the data entry to create a new household and occupant objects
                Household newHousehold = new Household(dataValues);
                Occupant newOccupant = new Occupant(dataValues);

                boolean houseFound = false;
                for (Household house : householdSet){
                    // If the household exists in set, add occupant to existing household 
                    if (house.compareHousehold(newHousehold)){
                        house.addNewOccupant(newOccupant);
                        houseFound = true;
                        break;
                    }    
                }
                // If house never found, add occupant to it and update set
                if (!houseFound){
                    newHousehold.addNewOccupant(newOccupant);
                    householdSet.add(newHousehold);
                }
            }
            
            // Close the scanner after use
            fileScan.close();
        
        // Invalid paths are caught and program is closed
        } catch (FileNotFoundException e){
            System.out.println("housing data file not found");
            System.exit(-1);
        }
    }


    /*
    Prints out each household in the current database set to the console
    */
    public void displayDatabase(){
        // Traverse each household in set and print out its details
        for (Household elem : this.householdSet){
            elem.displayHousehold();
            System.out.println();
        }
    }

}
