package modules;

import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting("ZoneA","Kevin");
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_ZoneA() {
        Sighting testSighting = new Sighting("ZoneA","Kevin");
        assertEquals("ZoneA", testSighting.getLocation());

    }

    @Test
    public void getRanger_sightingInstantiatesWithRanger_Kevin() {
        Sighting testSighting = new Sighting("ZoneA","Kevin");
        assertEquals("Kevin", testSighting.getRanger());
    }
}