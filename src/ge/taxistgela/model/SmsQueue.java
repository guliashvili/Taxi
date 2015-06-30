package ge.taxistgela.model;

import ge.taxistgela.bean.Company;
import ge.taxistgela.bean.Driver;
import ge.taxistgela.bean.SuperDaoUser;
import ge.taxistgela.bean.User;
import ge.taxistgela.helper.HashGenerator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Alex on 6/30/2015.
 */
public class SmsQueue {
    private static final String[] M_TYPE = {
            "http://localhost:8080/verify?action=uPhone&token=",
            "http://localhost:8080/verify?action=dPhone&token=",
            "http://localhost:8080/verify?action=cPhone&token="
    };

    private BlockingQueue<String> queue;

    public SmsQueue() {
        queue = new LinkedBlockingQueue<>();
    }

    private static int getUserType(SuperDaoUser superUser) {
        if (superUser instanceof User) {
            return 0;
        } else if (superUser instanceof Driver) {
            return 1;
        } else if (superUser instanceof Company) {
            return 2;
        }

        return -1;
    }

    public String getSms() {
        String ret;

        try {
            ret = queue.take();
        } catch (InterruptedException e) {
            return null;
        }

        return ret;
    }

    public boolean addSms(SuperDaoUser superUser) {
        String phoneNumber = superUser.getPhoneNumber();

        String token = HashGenerator.encryptAES(phoneNumber);

        if (token == null) {
            return true;
        }

        String message = null;

        try {
            message = M_TYPE[getUserType(superUser)] + URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return true;
        }

        try {
            addSms(phoneNumber, message);
        } catch (InterruptedException e) {
            return true;
        }

        return false;
    }

    private void addSms(String phoneNumber, String message) throws InterruptedException {
        queue.put(phoneNumber + " " + message);
    }
}
