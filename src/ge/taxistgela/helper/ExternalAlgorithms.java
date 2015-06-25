package ge.taxistgela.helper;

import java.math.BigDecimal;


/**
 * Created by GIO on 6/3/2015.
 */
public class ExternalAlgorithms {
    public final static BigDecimal EPS = new BigDecimal(0.000001);
    public static boolean DEBUGStrings = true;
    public static boolean DEBUGExceptions = true;
    public static boolean DEBUGSelects = true;

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
        else if (a instanceof BigDecimal && b instanceof BigDecimal) {
            BigDecimal c = ((BigDecimal) a).subtract((BigDecimal) b);
            if (c.signum() == -1) c = ((BigDecimal) b).subtract((BigDecimal) a);
            return c.compareTo(EPS) <= 0;
        }
        return  a.equals(b);
    }

    static public void debugPrintSelect(String s){
        if(DEBUGSelects)
            debugPrint(s);
    }
    static public void debugPrint(String s){
        if (DEBUGStrings)
          System.err.println(s);
    }
    static public void debugPrint(Exception e){
        if (DEBUGExceptions)
         e.printStackTrace();
    }
}
