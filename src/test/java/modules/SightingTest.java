package modules;

import org.junit.Test;

import static org.junit.Assert.*;


public class SightingTest {
    public Sighting setUpNewSighting(){
        return new Sighting("ZoneA","Kevin" );
    }
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

    @Test
    public void equals_returnsTrueIfLocationAndRangerAreSame_true() {
        Sighting testSighting = setUpNewSighting();
        Sighting otherSighting = setUpNewSighting();
        assertTrue(testSighting.equals(otherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }
}