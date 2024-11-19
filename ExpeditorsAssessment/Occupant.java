/*
    Name: David Giacobbi
    Date: November 19, 2024
    Project: Expeditors Technical Assessment

    The purpose of this program is to create Occupant object and parse through lines of the input data to create
    occupants to which can be added to their respective households.
*/


import java.util.ArrayList;


public class Occupant {

    // Object variables the provide details about each occupant
    public String firstName;
    public String lastName;
    public int age;

    /*
    Constructor for Occupant. Uses the data entry from input file to set object
    values (such as name and age).
    * @params String dataEntry is a line of input data
    */
    public Occupant (ArrayList<String> dataValues){

        // Set each value to the corresponding private variable, remove any punctuation
        this.firstName = dataValues.get(0).replaceAll("\\p{Punct}", "");
        this.lastName = dataValues.get(1).replaceAll("\\p{Punct}", "");

        // For age, remove any punctuation and turn into integer value
        dataValues.set(5, dataValues.get(5).replaceAll("\\p{Punct}", ""));
        this.age = Integer.parseInt(dataValues.get(5));
    } 

    
    /*
    Returns the last name of the occupant
    * @returns occupant last name
    */
    public String getLastName(){
        return this.lastName;
    }


    /*
    Returns the first name of the occupant
    * @returns occupant first name
    */
    public String getFirstName(){
        return this.firstName;
    }


    /*
    Display all information about specified occupant
    */
    public void displayOccupant(){
        System.out.println(this.firstName+" "+this.lastName+" (Age: "+this.age+")");
    }
}
