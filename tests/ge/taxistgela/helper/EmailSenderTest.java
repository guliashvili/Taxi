package ge.taxistgela.helper;

import ge.taxistgela.bean.User;
import static org.mockito.Mockito.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSenderTest {

    @Test
    public void testVerifyEmail() throws Exception {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("rmach13@freeuni.edu.ge").thenReturn("ratmach@yahoo.com");
        when(user.getFirstName()).thenReturn("Rati").thenReturn("Ratmach");
        EmailSender.verifyEmail(user,HashGenerator.getSaltHash("asdf"));
        EmailSender.verifyEmail(user,HashGenerator.getSaltHash("asdfk"))
    }
}