package Persons;

import Utility.PersonValidations;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Person{
    private String pName;
    private final String pRegion;
    private final String pID;
    private LocalDate pBirthDate;
    private int pAge;
    private int pPokeDollars;

    public Person(){
        this.pName = "";
        this.pRegion = "";
        this.pID = "";
        this.pBirthDate = null;
        this.pAge = 0;
        this.pPokeDollars = 0;
    }
    public Person(String pName, String pRegion, String pID, LocalDate pBirthDate, int pPokeDollars){
        this.pName = pName;
        this.pRegion = pRegion;
        this.pID = pID;
        this.pBirthDate = pBirthDate;
        this.pAge = Period.between(pBirthDate, LocalDate.now()).getYears();
        this.pPokeDollars = pPokeDollars;
    }

    public void setpName(String pName){
        this.pName = PersonValidations.valpName(pName);
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
    public String getpRegion(){
        return this.pRegion;
    }
    public String getpID(){
        return this.pID;
    }
    public LocalDate getpBirthDate(){
        return this.pBirthDate;
    }
    public String getpBirthDateStr(){
        if (pBirthDate == null) return "Fecha no disponible";
        return this.pBirthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public int getpAge(){
        return this.pAge;
    }
    public int getpPokeDollars(){
        return this.pPokeDollars;
    }

    @Override
    public String toString() {
        return "\nName:\t\t\t" + this.getpName() + "\nRegion:\t\t\t" + this.getpRegion() + "\nID:\t\t\t" + this.getpID() + "\nBirth Date:\t\t" + this.getpBirthDateStr() + "\nAge:\t\t\t" + this.getpAge() + "\nPokeDollars:\t\t" + this.getpPokeDollars();
    }
    
    public String fileString(){
        return this.getpName() + "," + this.getpRegion() + "," + this.getpID() + "," + this.getpBirthDateStr() + "," + this.getpAge() + "," + this.getpPokeDollars();
    }
    
    public abstract void genericDialogue();
}