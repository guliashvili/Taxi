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

    private final BlockingQueue<String> queue;

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
        String ret = "";

        if (!queue.isEmpty())
            try {
                ret = queue.take();
            } catch (InterruptedException e) {
                ret = "";
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
            queue.put(phoneNumber + " " + message);
        } catch (InterruptedException e) {
            return true;
        }

        return false;
    }
}
