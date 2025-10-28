package Persons;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person{
    private String pName;
    private String pID;
    private String pRegion;
    private LocalDate pBirthDate;
    private int pAge;
    private int pPokeDollars;

    public Person(){
        this.pName = "";
        this.pID = "";
        this.pRegion = "";
        this.pBirthDate = null;
        this.pAge = 0;
        this.pPokeDollars = 0;
    }
    public Person(String pName, String pID, String pRegion, LocalDate pBirthDate, int pPokeDollars){
        this.pName = pName;
        this.pID = pID;
        this.pRegion = pRegion;
        this.pBirthDate = pBirthDate;
        this.pAge = Period.between(pBirthDate, LocalDate.now()).getYears();
        this.pPokeDollars = pPokeDollars;
    }

    public void setpName(String pName){
        this.pName = pName;
    }
    public void setpID(String pID){
        this.pID = pID;
    }
    public void setpRegion(String pRegion){
        this.pRegion = pRegion;
    }
    public void setpBirthDate(LocalDate pBirthDate){
        this.pBirthDate = pBirthDate;
    }
    public void setpAge(LocalDate pBirthDate){
        this.pAge = Period.between(pBirthDate, LocalDate.now()).getYears();
    }
    public void setpPokeDollars(int pPokeDollars){
        this.pPokeDollars = pPokeDollars;
    }

    public String getpName(){
        return this.pName;
    }
    public String getpID(){
        return this.pID;
    }
    public String getpRegion(){
        return this.pRegion;
    }
    public LocalDate getpBirthDate(){
        return this.pBirthDate;
    }
    public int getpAge(){
        return this.pAge;
    }
    public int getpPokeDollars(){
        return this.pPokeDollars;
    }

    @Override
    public String toString() {
        return "Name:\t\t" + this.getpName() + "ID:\t\t" + this.getpID() + "Region:\t\t" + this.getpRegion() + "Birth Date:\t" + "Age:\t" + this.getpAge() + "PokeDollars:\t" + this.getpPokeDollars();
    }
    
    public abstract void genericDialogue();
}