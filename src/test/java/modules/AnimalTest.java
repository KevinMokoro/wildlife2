package modules;



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
}