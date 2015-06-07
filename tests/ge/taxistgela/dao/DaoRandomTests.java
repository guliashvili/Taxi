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
    ArrayList<Review> reviews = new ArrayList<>();



}