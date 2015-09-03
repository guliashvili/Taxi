/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.helper;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 5/29/2015.
 */
public class HashGeneratorTest {

    @Test
    public void testGetHash() throws Exception {
        String s = "HelloWorld";
        Assert.assertEquals("68e109f0f40ca72a15e05cc22786f8e6", HashGenerator.getHash(s));

        s = "qwertyhgfdsazxcvbn";
        assertEquals("ef1cc40bd58c24d5fc16fd2d74cdb8a1", HashGenerator.getHash(s));

        s = "68e109f0f40ca72a15e05cc22786f8e6";
        assertEquals("22744961b84dc9c20cdd1c0e0f5e1a82", HashGenerator.getHash(s));

        s = "traki";
        assertEquals("98ccab148f07b22e761434aa4c552dad",HashGenerator.getSaltHash(s));

        s = "rmach13@freeuni.edu.ge";
        String s1 = HashGenerator.encryptAES(s);
        s1 = HashGenerator.decryptAES(s1);
        assertEquals(s, s1);
    }
}