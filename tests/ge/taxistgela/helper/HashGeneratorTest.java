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

        s = "qwertyhgfdsazxcvbn";
        assertEquals("ef1cc40bd58c24d5fc16fd2d74cdb8a1", HashGenerator.getHash(s));

        s = "68e109f0f40ca72a15e05cc22786f8e6";
        assertEquals("22744961b84dc9c20cdd1c0e0f5e1a82", HashGenerator.getHash(s));
    }
}