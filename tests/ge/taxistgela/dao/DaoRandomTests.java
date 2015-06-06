package ge.taxistgela.dao;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.User;
import ge.taxistgela.bean.UserPreference;
import ge.taxistgela.model.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ratmach on 6/6/15.
 */
public class DaoRandomTests extends TestCase {
    String[] names={};
    String[] surenames={};
    String[] emails={};
    String[] passwords={};
    String[] phoneNumbers={};
    String[] facebook_IDS={};
    String[] google_IDS={};

    String[] companyNames={};

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    ArrayList<Company> companies = new ArrayList<>();

    UserPreference[] preferences={};
    @Test
    public void randomUserTests(){
        UserManager man = new UserManager(new UserDao());
        for(int i=0;i<250;++i){

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
    private String generateRandomString(int len,boolean forceLength){
        Random rnd = new Random();
        String output="";
        for(int i=0;i<len;++i){
            if(rnd.nextBoolean() || forceLength){
                output+='a'+rnd.nextInt(26);
            }
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