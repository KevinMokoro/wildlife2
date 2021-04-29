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
}