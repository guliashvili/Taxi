package ge.taxistgela.dao.helper;

import ge.taxistgela.bean.User;
import static org.mockito.Mockito.*;

import ge.taxistgela.helper.EmailSender;
import ge.taxistgela.helper.HashGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

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
            EmailSender.verifyEmail(user, HashGenerator.getSaltHash("asdf"));
            user.setFirstName("Azo");
            user.setEmail("alex.r.azizian@gmail.com");
            EmailSender.verifyEmail(user, HashGenerator.getSaltHash("asdfk"));
        }catch (Exception e){
            System.out.println(e.toString());
            assertTrue(false);
        }
    }
}