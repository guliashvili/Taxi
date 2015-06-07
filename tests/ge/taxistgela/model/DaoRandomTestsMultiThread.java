package ge.taxistgela.model;

import ge.taxistgela.dao.DaoRandomTests;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ratmach on 7/6/15.
 */
public class DaoRandomTestsMultiThread {
    @Test
    public void TestMultiThread(){
        DaoRandomTests tests = new DaoRandomTests();
        Thread t = new Thread(){
            @Override
            public void run(){
                tests.randomCompanyTests();
            }
        };
        t.start();
        Thread t1 = new Thread(){
            @Override
            public void run(){
                tests.randomDriverTests();
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run(){
                tests.randomUserTests();
            }
        };
        t2.start();
        Thread t3 = new Thread(){
            @Override
            public void run(){
                tests.randomReviewTests();
            }
        };
        t3.start();
        Thread t4 = new Thread(){
            @Override
            public void run(){
                tests.randomOrderTests();
            }
        };
        t4.start();
        try {
            t.join();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}