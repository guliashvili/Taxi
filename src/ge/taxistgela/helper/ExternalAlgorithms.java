package ge.taxistgela.helper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


/**
 * Created by GIO on 6/3/2015.
 */
public class ExternalAlgorithms {

    /**
     * returns true if a and b are both null or equal(and non null(both))
     *
     * @param a
     * @param b
     * @return true if both are null or equal
     */
    static  public  boolean equalsNull(Object a, Object b){
        if(a == null && b == null) return  true;
        else if(a == null || b == null) return  false;
        return  a.equals(b);
        // TODO assertEquals isgan riti gansxvavdeba?
    }

    public  static  boolean DEBUG = true;
    public  static  boolean DEBUGSelects = true;

    static public void debugPrintSelect(String s){
        if(DEBUGSelects)
            debugPrint(s);
    }
    static public void debugPrint(String s){
        if(DEBUG)
          System.err.println(s);
    }
    static public void debugPrint(Exception e){
       if(DEBUG)
         e.printStackTrace();
    }
}
