package Persons;

import Iinterfaces.MoneyHandler; // Importar la interfaz
import Utility.PersonValidations;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// Añadir 'implements MoneyHandler'
public abstract class Person implements MoneyHandler {
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
    
    // Implementación de setpPokeDollars (ya existía, pero ahora es parte de la interfaz)
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
        return this.pBirthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public int getpAge(){
        return this.pAge;
    }
    
    // Implementación de getpPokeDollars (ya existía, pero ahora es parte de la interfaz)
    public int getpPokeDollars(){
        return this.pPokeDollars;
    }

    @Override
    public String toString() {
        return "\nName:\t\t\t" + this.getpName() + "\nRegion:\t\t\t" + this.getpRegion() + "\nID:\t\t\t" + this.getpID() + "\nBirth Date:\t\t" + this.getpBirthDateStr() + "\nAge:\t\t\t" + this.getpAge() + "\nPokeDollars:\t\t" + this.getpPokeDollars();
    }
    
    public abstract void genericDialogue();

    // --- MÉTODOS DE MONEYHANDLER IMPLEMENTADOS ---
    
    /**
     * Añade dinero al total del entrenador.
     * @param amount La cantidad a ganar.
     */
    @Override
    public void earnMoney(int amount) {
        if (amount > 0) {
            this.pPokeDollars += amount;
        }
    }

    /**
     * Resta dinero del total del entrenador. No permite que baje de 0.
     * @param amount La cantidad a perder.
     */
    @Override
    public void loseMoney(int amount) {
        if (amount > 0) {
            this.pPokeDollars -= amount;
            if (this.pPokeDollars < 0) {
                this.pPokeDollars = 0;
            }
        }
    }
    
    /**
     * (Implementado por compatibilidad de interfaz)
     * Reemplazado por setpPokeDollars(int amount)
     */
    @Override
    public void setMoney(int amount) {
        this.setpPokeDollars(amount);
    }

    /**
     * (Implementado por compatibilidad de interfaz)
     * Reemplazado por getpPokeDollars()
     */
    @Override
    public void getMoney(int amount) {
        // Este método en la interfaz parece estar mal definido (debería ser getMoney() y retornar int)
        // Lo implementamos vacío y usamos getpPokeDollars() en su lugar.
    }
}