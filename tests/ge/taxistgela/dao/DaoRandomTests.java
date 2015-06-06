package ge.taxistgela.dao;

import ge.taxistgela.bean.*;
import ge.taxistgela.model.*;
import junit.framework.TestCase;
import org.hamcrest.number.BigDecimalCloseTo;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ratmach on 6/6/15.
 */
public class DaoRandomTests extends TestCase {
    String[] names={"rati","azo","gela","gulo","mandzu","aleqsandre","tornike","giorgi","givi","nodari","sandro","ana","nino","nunu","ekko","yasuo","vi","volibear","tinker","revazi","vlad","roshan","seer"};
    String[] surenames={"matchavariani","aziziani","guliashvili","mandzulashvili","magaltadze","magradze","kunchulia","gobejishvili","tchanturia","dogonadze","shalikashvili"};
    String[] emails={"@freeuni.edu.ge","@gmail.com","@yahoo.com","@mail.ru"};

    String[] companyNames={"Rostomi & CO","taqsi minda shchma","sapatriarqos jipi gamodzaxebit","2 diplomianebi","rac ar gergeba","sad iyo da sad ara"};

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    ArrayList<Company> companies = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();

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
            assertTrue(usr.equals(dao.loginUser(usr.getEmail(), usr.getPassword())));
            users.add(usr);
        }
    }
    @Test
    public void randomCompanyTests(){
        CompanyDao dao = new CompanyDao();
        Random rnd = new Random();
        for(int i=0;i<250;++i){
            String companyCode = generateRandomString(9,true,false);
            String email = "";
            while(email.equals("") || dao.checkEmail(email)){
                email = generateRandomString(14,false,false)+emails[rnd.nextInt(emails.length)];
            }
            String password = generateRandomString(10,true,false);
            String companyName = companyNames[rnd.nextInt(companyNames.length)];
            String phoneNumber = generateRandomString(9,true,true);
            String facebookID = generateRandomString(30,true,false);
            String googleID = generateRandomString(30,true,false);
            Company company = new Company(-1,companyCode,email,password,companyName,phoneNumber,facebookID,googleID,rnd.nextBoolean());
            dao.registerCompany(company);
            assertTrue(company.equals(dao.loginCompany(company.getEmail(),company.getPassword())));
            companies.add(company);
        }
    }
    @Test
    public void randomDriverTests(){
        DriverDao dao = new DriverDao();
        Random rnd = new Random();
        for(int i=0;i<250;++i){
            String personalID = generateRandomString(11,true,true);
            String email = "";
            while(email.equals("") || dao.checkEmail(email)){
                email = generateRandomString(14,false,false)+emails[rnd.nextInt(emails.length)];
            }
            String password = generateRandomString(10,true,false);
            Integer companyID = companies.get(rnd.nextInt(companies.size())).getCompanyID();
            String name = names[rnd.nextInt(names.length)];
            String surename = surenames[rnd.nextInt(surenames.length)];
            String phoneNumber = generateRandomString(9,true,true);
            Gender gend=Gender.MALE;
            if(rnd.nextBoolean()) gend=Gender.FEMALE;
            String facebookID = generateRandomString(30,true,false);
            String googleID = generateRandomString(30,true,false);
            Car car = new Car();
            String carID="";
            while(carID.equals("") || dao.checkCarID(carID)){
                carID = generateRandomPlate();
            }
            car.setConditioning(rnd.nextBoolean());
            car.setCarYear(1990+rnd.nextInt(25));
            car.setCarDescription(generateRandomString(200,false,false));
            car.setNumPassengers(rnd.nextInt(8));
            dao.insertCar(car);
            DriverPreference driverPreference = new DriverPreference();
            driverPreference.setCoefficientPer(rnd.nextDouble()*8);
            driverPreference.setDriverPreferenceID(-1);
            driverPreference.setMinimumUserRating(rnd.nextDouble()*5);
            dao.insertDriverPreference(driverPreference);
            Location loc = new Location(new BigDecimal(rnd.nextDouble()*180),new BigDecimal(rnd.nextDouble()*180));
            Driver driver = new Driver(-1,personalID,email,password,companyID,name,surename,gend,phoneNumber,car,facebookID,googleID,loc,rnd.nextDouble()*5,driverPreference,rnd.nextBoolean(),rnd.nextBoolean());
            dao.registerDriver(driver);
            assertTrue(driver.equals(dao.loginDriver(driver.getEmail(),driver.getPassword())));
            drivers.add(driver);
        }
    }
    /**
     * generates random string of given length
     * @param len size
     * @param forceLength the output string should be exactly given size
     * @return
     */
    private String generateRandomString(int len,boolean forceLength,boolean numbersOnly){ // needs some utf and symbols
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
    public void randomOrderTests(){
        OrderDao dao = new OrderDao();
        Random rnd = new Random();
        int userID = users.get(rnd.nextInt(users.size())).getUserID();
        int driverID = drivers.get(rnd.nextInt(drivers.size())).getDriverID();
        int numPassengers = rnd.nextInt(8);
        Location loc = new Location(new BigDecimal(rnd.nextDouble()*180),new BigDecimal(rnd.nextDouble()*180));
        Location loc1 = new Location(new BigDecimal(rnd.nextDouble()*180),new BigDecimal(rnd.nextDouble()*180));
        Date startDate = new Date();
        Date endDate = new Date();
        Date callTime = new Date();
        startDate.setTime(rnd.nextLong());
        while(endDate.after(startDate) && endDate.after(callTime) && callTime.after(startDate)){
            startDate.setTime(rnd.nextLong());
            callTime.setTime(rnd.nextLong());
        }
        BigDecimal paymentAmount = new BigDecimal(rnd.nextDouble()*220);
        Order ord = new Order(-1,userID,driverID,numPassengers,loc,loc1,startDate,endDate,paymentAmount,callTime);
        dao.addOrder(ord);
        assertTrue(dao.getOrderByID(ord.getOrderID()).equals(ord));
        orders.add(ord);
    }
    @Test
    public void randomReviewTests(){
        ReviewDao dao =new ReviewDao();
        Random rnd = new Random();
        Review rev = new Review(-1,orders.get(rnd.nextInt(orders.size())).getOrderID(),rnd.nextBoolean(),rnd.nextDouble()*5,generateRandomString(120,false,false));
    }

}