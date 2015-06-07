package ge.taxistgela.helper;

import com.google.maps.model.DistanceMatrixElement;
import ge.taxistgela.bean.Location;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alex on 6/7/2015.
 */
public class GoogleMapUtilsTest {

    @Test
    public void testGetRoadEqualOriginDestionation() throws Exception {
        // 41.69973, 44.80398
        Location origin = new Location();
        origin.setLatitude(new BigDecimal("41.69973"));
        origin.setLongitude(new BigDecimal("44.80398"));

        Location destination = new Location();
        destination.setLatitude(new BigDecimal("41.69973"));
        destination.setLongitude(new BigDecimal("44.80398"));

        DistanceMatrixElement road = GoogleMapUtils.getRoad(origin, destination);

        assertNotNull(road);
        assertEquals(0L, road.distance.inMeters);
        assertEquals(0L, road.duration.inSeconds);
    }

    @Test
    public void testGetRoadDifferentOriginDestionation() throws Exception {
        // 41.7008, 44.80191000000001
        // 41.69973, 44.80398
        Location origin = new Location();
        origin.setLatitude(new BigDecimal("41.7008"));
        origin.setLongitude(new BigDecimal("44.80191000000001"));

        Location destination = new Location();
        destination.setLatitude(new BigDecimal("41.69973"));
        destination.setLongitude(new BigDecimal("44.80398"));

        DistanceMatrixElement road = GoogleMapUtils.getRoad(origin, destination);

        assertNotNull(road);
        assertEquals(209L, road.distance.inMeters);
        assertEquals(19L, road.duration.inSeconds);
    }

    @Test
    public void testGetRoadNull() throws Exception {
        // TODO add tests for return value null.
    }
}