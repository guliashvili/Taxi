package ge.taxistgela.helper;

import ge.taxistgela.bean.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSender {
    private final static String username="rmach13@freeuni.edu.ge";
    private final static String password="getyvit parols aba ra sure :DDDD";
    private final static String from="support@taxistgela.ge";
    private final static String stmphost="smtp.gmail.com";
    private final static String host="localhost";

    private final static String subject="Taxist Gela Service Verification";
    private final static String verificationURL="127.0.0.1/verify?action=uEmail&token=";
    private final static String message_t[] = {"Thank You! "," For registration, Please Follow to the URL: ",verificationURL," And enter code given:"};

    /**
     * Sends verification email specified in user given
     * @param user user to verify email
     * @param key key to be sent
     */
    public static void verifyEmail(User user, String key) throws RuntimeException {
        Thread thread = new Thread(() -> {

                try {
                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", stmphost);
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getDefaultInstance(properties,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(from);
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                    message.setSubject(subject);
                    StringBuilder b = new StringBuilder();
                    for (int i = 0; i < message_t.length; ++i) {
                        if (i == 1) b.append(user.getFirstName());
                        if (i == 2) b.append(user.getEmailToken());
                        b.append(message_t[i]);
                    }
                    b.append(key);
                    message.setText(b.toString());
                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException();
                }
            }

        );

        thread.start();
    }
}
