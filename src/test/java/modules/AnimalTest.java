package modules;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.*;


public class AnimalTest {

    public Animal setUpNewAnimal() {
        return new Animal("lion",1);
    }

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public void getName_instantiatesWithName_String() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("lion", testAnimal.getName());
    }

    @Test
    public void getSightingId_instantiatesWithSightingId_int() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals(1,testAnimal.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animal testAnimal = setUpNewAnimal();
        Animal otherAnimal = setUpNewAnimal();
        assertTrue(testAnimal.equals(otherAnimal));
    }

    @Test
    public void save_returnsTrueIfObjectIsSame() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal otherAnimal = new Animal("",1);
        otherAnimal.save();
        assertEquals(true,Animal.all().get(0).equals(testAnimal));
        assertEquals(true,Animal.all().get(1).equals(otherAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_otherAnimal() {
        Animal firstAnimal =setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("", 3);
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting("","");
        testSighting.save();
        Animal testAnimal = new Animal("lion", testSighting.getId());
        testAnimal.save();
        Animal savedAnimal = Animal.find(testAnimal.getId());
        assertEquals(savedAnimal.getSightingId(), testSighting.getId());
    }

    @Test
    public void save_savesSightingIntoDB_true() {
        Sighting fistSighting = new Sighting("ZoneA","Kevin");
        fistSighting.save();
        Animal firstAnimal = new Animal("lion",fistSighting.getId());
        firstAnimal.save();
        Animal savedAnimal = Animal.find(firstAnimal.getId());
        assertEquals(savedAnimal.getSightingId(), fistSighting.getId());
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Timestamp savedAnimalCreatedAt = Animal.find(testAnimal.getId()).getCreatedAt();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedAnimalCreatedAt.getDay());
    }

    @Test
    public void delete_deletesAnimalFromDatabase_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null, Animal.find(testAnimal.getId()));
    }
}