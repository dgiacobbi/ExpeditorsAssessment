/*
    Name: David Giacobbi
    Date: November 19, 2024
    Project: Expeditors Technical Assessment

    The purpose of this program is to create the Household object to parse through lines of the input data and create
    new households and add new occupants as needed.
*/

/* 
        Requirements:
        Write a program using Java or an object oriented language of your preference.  (Java is the primary dev language at Expeditors).  
        Given the provided input data, print the expected output to the console or write to a text file.
        Please include the code that you wrote to solve the exercise.

        Input data:
        "Dave","Smith","123 main st.","seattle","wa","43"
        "Alice","Smith","123 Main St.","Seattle","WA","45"
        "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
        "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
        "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
        "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
        "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
        "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
        "Ian","Smith","123 main st ","Seattle","Wa","18"
        "Jane","Smith","123 Main St.","Seattle","WA","13"

        Expected output: 
        Each household and number of occupants, followed by:
        Each First Name, Last Name, Address and Age sorted by Last Name then First Name where the occupant(s) is older than 18
*/

import java.util.Set;
import java.util.HashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


public class Household {

    private int occupantCount;
    public String streetAddress;
    public String city;
    public String state;

    private Set<Occupant> houseOccupants;
    
    public Household (ArrayList<String> dataValues){
        // Initialize object variables
        this.occupantCount = 0;
        this.houseOccupants = new HashSet<Occupant>();

        // Set each value to the corresponding private variable, remove any unnecessary punctuation and whitespace
        this.streetAddress = dataValues.get(2).replaceAll("\\p{Punct}", "").replaceAll("\\s+$", "").toUpperCase();
        this.city = dataValues.get(3).replaceAll("\\p{Punct}", "").toUpperCase();
        this.state = dataValues.get(4).replaceAll("\\p{Punct}", "").toUpperCase();
    }


    public void addNewOccupant(Occupant newOccupant){
        // Add occupant to the household's set
        this.houseOccupants.add(newOccupant);
        
        // Update household's current occupant count
        this.occupantCount++;
    }


    /*
    Display all information about specified household. Organize occupants by specified order and make a
    list of the ones that are able to be shown (over 18 years old).
    */
    public void displayHousehold(){
        // Display Household details
        System.out.println(this.streetAddress);
        System.out.println(this.city + ", " + this.state);
        System.out.println("Occupant Count: " + this.occupantCount);

        // Create a list of occupants over 18
        List<Occupant> shownOccupants = new ArrayList<Occupant>();
        for (Occupant occupant : houseOccupants){
            if (occupant.age > 18)
                shownOccupants.add(occupant);
        }

        // Sort the list of occupants first by their last name and then their first
        Collections.sort(shownOccupants, Comparator.comparing(Occupant::getLastName).thenComparing(Occupant::getFirstName));
        // Display sorted results
        for (Occupant currOcc : shownOccupants){
            currOcc.displayOccupant();
        }

        System.out.println();
    }


    /*
    Checks if household is the same as this household
    * @params Household newHouse comparison house
    */
    public boolean compareHousehold(Household newHouse){
        if (newHouse.streetAddress.equals(this.streetAddress) && newHouse.city.equals(this.city) && newHouse.state.equals(this.state))
            return true;
        else
            return false;
    }
}
