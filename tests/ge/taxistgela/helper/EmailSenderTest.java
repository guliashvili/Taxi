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

import ge.taxistgela.bean.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSenderTest {

    @Test
    public void testVerifyEmail() throws Exception {
       // User user = mock(User.class);
        //when(user.getEmail()).thenReturn("rmach13@freeuni.edu.ge").thenReturn("ratmach@yahoo.com");
        //when(user.getFirstName()).thenReturn("Rati").thenReturn("Ratmach");
        try {
            User user = new User();
            user.setFirstName("Rmertochemo");
            user.setEmail("vinmesmiti@gmail.com");
            user.setToken(HashGenerator.getSaltHash("asdf"));
            EmailSender.verifyEmail(user);
            user.setFirstName("Azo");
            user.setEmail("alex.r.azizian@gmail.com");
            user.setToken(HashGenerator.getSaltHash("asdfk"));
            EmailSender.verifyEmail(user);
        }catch (Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }
    }
}