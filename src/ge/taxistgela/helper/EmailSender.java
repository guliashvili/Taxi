package ge.taxistgela.helper;

import ge.taxistgela.bean.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSender {
    private final static String from="support@taxistgela.ge";
    private final static String stmphost="mail.smtp.host";
    private final static String host="localhost";

    private final static String subject="Taxist Gela Service Verification";
    private final static String verificationURL="127.0.0.1/verify";
    private final static String message_t[] = {"Thank You!","For registration, Please Follow to the URL: ",verificationURL," And enter code given:"};

    /**
     * Sends verification email specified in user given
     * @param user user to verify email
     * @param key key to be sent
     */
    public static synchronized void verifyEmail(User user,String key){
        Properties properties = System.getProperties();
        properties.setProperty(stmphost,host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
            message.setSubject(subject);
            StringBuilder b = new StringBuilder();
            for(int i=0;i<message_t.length;++i){
                if(i==1) b.append(user.getFirstName());
                b.append(message_t[i]);
            }
            b.append(key);
            message.setText(b.toString());
            Transport.send(message);
        }catch (MessagingException e){
            System.out.println(e.toString());
        }
    }
}
