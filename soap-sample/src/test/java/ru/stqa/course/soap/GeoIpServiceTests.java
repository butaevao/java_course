package ru.stqa.course.soap;

import net.webservicex.GeoIP;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;

import static org.testng.Assert.assertEquals;

/**
 * Created by Оля on 13.11.2016.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
