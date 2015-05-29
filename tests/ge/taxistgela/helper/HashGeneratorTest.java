package ge.taxistgela.helper;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Alex on 5/29/2015.
 */
public class HashGeneratorTest {

    @Test
    public void testGetHash() throws Exception {
        String s = "HelloWorld";

        assertEquals("68e109f0f40ca72a15e05cc22786f8e6", HashGenerator.getHash(s));
    }
}