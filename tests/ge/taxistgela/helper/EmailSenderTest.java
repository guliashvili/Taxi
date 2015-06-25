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