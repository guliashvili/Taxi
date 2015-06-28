package ge.taxistgela.ram.bean;

import ge.taxistgela.bean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GIO on 6/28/2015.
 */
public class UserInfo extends User {




    public Object block = new Object();
    public List<OrderInfo> candidats1 = Collections.synchronizedList(new ArrayList<OrderInfo>());
    public List<OrderInfo> candidats2 = Collections.synchronizedList(new ArrayList<OrderInfo>());
    public Long orderStartTime;




    public UserInfo() {
        super();
    }

    public UserInfo(User user) {
        super(user);
    }





}
