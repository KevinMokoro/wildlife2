package modules;

import org.junit.Test;

import static org.junit.Assert.*;

//public Animal setUpNewAnimal() {
 //   return new Animal("lion");
 //      }

public class AnimalTest {
    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("lion");
        assertEquals(true,testAnimal instanceof Animal);
    }
}