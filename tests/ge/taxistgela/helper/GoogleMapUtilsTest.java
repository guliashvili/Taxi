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

package ge.taxistgela.helper;

import com.google.maps.model.DistanceMatrixElement;
import ge.taxistgela.bean.Location;
import org.junit.Test;

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
        origin.setLatitude(new Double("41.69973"));
        origin.setLongitude(new Double("44.80398"));

        Location destination = new Location();
        destination.setLatitude(new Double("41.69973"));
        destination.setLongitude(new Double("44.80398"));

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
        origin.setLatitude(new Double("41.7008"));
        origin.setLongitude(new Double("44.80191000000001"));

        Location destination = new Location();
        destination.setLatitude(new Double("41.69973"));
        destination.setLongitude(new Double("44.80398"));

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