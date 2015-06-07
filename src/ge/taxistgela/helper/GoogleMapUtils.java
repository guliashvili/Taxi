package ge.taxistgela.helper;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import ge.taxistgela.bean.Location;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 6/7/2015.
 */
public class GoogleMapUtils {

    private static final GeoApiContext context;

    static {
        context = new GeoApiContext()
                .setApiKey("AIzaSyA2wMaosCj2-KyQQvYhcQbaO6E4I4iUpbc")
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    /**
     * Return an object that contains distance and time to travel
     * on the road between two locations.
     *
     * @param origin
     * @param destination
     * @return Object containing distance and time.
     */
    public static DistanceMatrixElement getRoad(Location origin, Location destination) {
        DistanceMatrix matrix;

        try {
            matrix = DistanceMatrixApi.newRequest(context)
                    .origins(parseToLatLng(origin))
                    .destinations(parseToLatLng(destination))
                    .mode(TravelMode.DRIVING)
                    .await();
        } catch (Exception e) {
            return null;
        }

        DistanceMatrixElement road = matrix.rows[0].elements[0];

        if (road.status == DistanceMatrixElementStatus.OK)
            return road;

        return null;
    }

    /**
     * Parses Location object to LatLng object.
     *
     * @param origin
     * @return Parsed object.
     */
    private static LatLng parseToLatLng(Location origin) {
        return new LatLng(origin.getLatitude().doubleValue(), origin.getLongitude().doubleValue());
    }
}
