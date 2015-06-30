package ge.taxistgela.helper;
/*
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;


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
            System.out.println(s);

        System.out.flush();
    }
    static public void debugPrint(Exception e){
        if (DEBUGExceptions)
         e.printStackTrace();
    }

    public static String verify(String id_token) throws GeneralSecurityException, IOException {
/*        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Arrays.asList(SNInfo.googleClientID))
                .build();


        GoogleIdToken idToken = verifier.verify(id_token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            ExternalAlgorithms.debugPrint("User ID: " + payload.getSubject());
            return payload.getSubject();
        } else {
            ExternalAlgorithms.debugPrint("Invalid ID token.");
        }*/
        return null;
    }

    public static String parseToJson(Object obj) {
        final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(obj);
    }
}
