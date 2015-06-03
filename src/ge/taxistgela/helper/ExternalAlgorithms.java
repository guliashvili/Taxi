package ge.taxistgela.helper;

import java.util.Objects;

/**
 * Created by GIO on 6/3/2015.
 */
public class ExternalAlgorithms {
    static  public  boolean equalsNull(Object a, Object b){
        if(a == null && b == null) return  true;
        else if(a == null || b == null) return  false;
        return  a.equals(b);
    }
}
