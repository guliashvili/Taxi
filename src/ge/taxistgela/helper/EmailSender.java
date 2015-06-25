package ge.taxistgela.helper;

import ge.taxistgela.bean.GeneralCheckableInformation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSender {
    private final static String username="taxergela@gmail.com";
    private final static String password="gelagela";
    private final static String from="support@taxistgela.ge";
    private final static String stmphost="smtp.gmail.com";
    private final static String host="localhost";

    private final static String subject="Taxist Gela Service Verification";
    private final static String verificationURL="http://localhost:8080/verify?action=uEmail&token=";
    private final static String message_t[] = {"Thank You! "," For registration, Please Follow to the URL: ",verificationURL," And enter code given:"};
    /**
     * Sends verification email specified in user given
     * @param user user to verify email
     */
    public static void verifyEmail(GeneralCheckableInformation user) throws RuntimeException {
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
                        //if (i == 1) b.append(user.);
                        if (i == 3) b.append(URLEncoder.encode(user.getEmailToken(),"UTF-8"));
                        b.append(message_t[i]);
                    }
                    message.setText(b.toString());
                    Transport.send(message);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }

        );

        thread.start();
    }
}
