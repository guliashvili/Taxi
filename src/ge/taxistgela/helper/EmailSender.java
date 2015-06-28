package ge.taxistgela.helper;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.bean.User;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Ratmach on 30/5/15.
 */
public class EmailSender {
    private final static String username = "taxergela@gmail.com";
    private final static String password = "gelagela";
    private final static String from = "support@taxistgela.ge";
    private final static String stmphost = "smtp.gmail.com";
    private final static String host = "localhost";

    private final static String subject = "Taxist Gela Service Verification";
    private final static String verificationURL = "http://localhost:8080/verify?";
    private final static String[] verificationActions = {
            "action=uEmail&token=",
            "action=dEmail&token=",
            "action=cEmail&token=",
            "action=dCompany&token="
    };
    private final static String message_t[] = {"Thank You! ", " For registration, Please Follow to the URL: ", verificationURL, " And enter code given:"};

    /**
     * Sends verification email specified in user given
     *
     * @param user user to verify email
     */
    public static void verifyEmail(SuperDaoUser user) throws RuntimeException {
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
                    if (i == 3)
                        b.append(verificationActions[getActionIndex(user)]).append(URLEncoder.encode(user.getEmailToken(), "UTF-8"));
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

    private static int getActionIndex(SuperDaoUser user) {
        if (user instanceof User) {
            return 0;
        } else if (user instanceof Driver) {
            return 1;
        } else if (user instanceof Company) {
            return 2;
        }

        return -1;
    }

    public static void verifyCompany(Driver driver, Company company) {
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
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(company.getEmail()));
                message.setSubject(subject);

                String token = HashGenerator.encryptAES(driver.getDriverID() + "#" + company.getCompanyID());

                String url = verificationURL + verificationActions[3] + URLEncoder.encode(token, "UTF-8");

                StringBuilder b = new StringBuilder();

                b.append("Driver wants to work for you.\n");
                b.append("Driver Info.\n");
                b.append("Name: ").append(driver.getFirstName()).append(" ").append(driver.getLastName());
                b.append(" Personal ID: ").append(driver.getPersonalID()).append("\n");
                b.append("Please Follow to the URL for confirmation: ").append(url);

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
