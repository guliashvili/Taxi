package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.model.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ratmach on 6/6/15.
 */
public class DaoRandomTests extends TestCase {
    String[] names={"rati","azo","gela","gulo","mandzu","aleqsandre","tornike","giorgi","givi","nodari","sandro","ana","nino","nunu","ekko","yasuo","vi","volibear","tinker","revazi","vlad","roshan","seer"};
    String[] surenames={"matchavariani","aziziani","guliashvili","mandzulashvili","magaltadze","magradze","kunchulia","gobejishvili","tchanturia","dogonadze","shalikashvili"};
    String[] emails={"@freeuni.edu.ge","@gmail.com","@yahoo.com","@mail.ru"};

    String[] companyNames={};

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    ArrayList<Company> companies = new ArrayList<>();

    UserPreference[] preferences={};
    @Test
    public void randomUserTests(){
        UserDao dao = new UserDao();
        Random rnd = new Random();
        for(int i=0;i<250;++i){
            String email = "";
            while(email.equals("") || dao.checkEmail(email)){
                email = generateRandomString(14,false,false)+emails[rnd.nextInt(emails.length)];
            }
            String password = generateRandomString(10,true,false);
            String name = names[rnd.nextInt(names.length)];
            String surename = surenames[rnd.nextInt(surenames.length)];
            String phoneNumber = generateRandomString(9,true,true);
            Gender gend=Gender.MALE;
            if(rnd.nextBoolean()) gend=Gender.FEMALE;
            String facebookID = generateRandomString(30,true,false);
            String googleID = generateRandomString(30,true,false);
            double rating = rnd.nextDouble()*5;
            UserPreference usrp = new UserPreference();
            usrp.setUserPreferenceID(-1);
            usrp.setCarYear(rnd.nextInt(25)+1990);
            usrp.setConditioning(rnd.nextBoolean());
            usrp.setMinimumDriverRating(rnd.nextDouble()*5);
            usrp.setTimeLimit(rnd.nextInt(2500));
            usrp.setWantsAlone(rnd.nextBoolean());
            dao.insertUserPreference(usrp);
            User usr = new User(-1,email,password,name,surename,phoneNumber,gend,facebookID,googleID,rating,usrp,rnd.nextBoolean());
            dao.registerUser(usr);
        }
    }
    @Test
    public void randomDriverTests(){
        DriverManager man = new DriverManager(new DriverDao());
        for(int i=0;i<250;++i){

        }
    }
    /**
     * generates random string of given length
     * @param len size
     * @param forceLength the output string should be exactly given size
     * @return
     */
    private String generateRandomString(int len,boolean forceLength,boolean numbersOnly){
        Random rnd = new Random();
        String output="";
        for(int i=0;i<len;++i){
            if(rnd.nextBoolean() || numbersOnly)
                output += '0' + rnd.nextInt(9);
            else
                if(rnd.nextBoolean() || forceLength)
                    if(rnd.nextBoolean())
                        output += 'a' + rnd.nextInt(26);
                    else
                        output += '0' + rnd.nextInt(9);

        }
        return output;
    }
    /**
     * generates random plate number for car
     * @return
     */
    private String generateRandomPlate(){
        Random rnd = new Random();
        return ""+ ('0'+rnd.nextInt(9)) + ('0'+rnd.nextInt(9)) + ('a'+rnd.nextInt(26)) + ('a'+rnd.nextInt(26)) + ('a'+rnd.nextInt(26)) + ('0'+rnd.nextInt(9)) +('0'+rnd.nextInt(9));
    }
    @Test
    public void randomCompanyTests(){
        CompanyManager man = new CompanyManager(new CompanyDao());
        for(int i=0;i<250;++i){

        }
    }
    @Test
    public void randomOrderTests(){
        OrderManager man = new OrderManager(new OrderDao());
    }
    @Test
    public void randomReviewTests(){
        ReviewManager man = new ReviewManager(new ReviewDao());
    }

}