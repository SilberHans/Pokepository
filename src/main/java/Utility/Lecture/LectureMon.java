package Utility.Lecture;

import Pokemons.Pokemon;
import Persons.Person;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public final class LectureMon {
    ArrayList<Person> persons = new ArrayList<>();
        
    String Line = null;
    String pName = null;
    String pID = null;
    String pRegion = null;
    LocalDate pBirthDate = null;
    int pAge = 0;
    int pPokeDollars= 0;

    String njAdmissionDate = null;
    int njExpYears= 0;
    boolean njAvailability = true;

    int tMedals = 0;
        
}