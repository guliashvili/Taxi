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
        Thread t1 = new Thread(){
            @Override
            public void run(){
                tests.randomDriverTests();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run(){
                tests.randomUserTests();
            }
        };
        Thread t3 = new Thread(){
            @Override
            public void run(){
                tests.randomReviewTests();
            }
        };
        Thread t4 = new Thread(){
            @Override
            public void run(){
                tests.randomOrderTests();
            }
        };
    }
}