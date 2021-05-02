package modules;
import org.junit.*;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class EndangeredTest {

    public Endangered setUpNewEndangered() {
        return new Endangered("rhino","ill","young");
    }

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Endangered_instantiatesCorrectly_true() {
        Endangered testEndangered = setUpNewEndangered();
        assertEquals(true,testEndangered instanceof Endangered);
    }

    @Test
    public void getName_instantiatesWithName_String() {
        Endangered testEndangered = setUpNewEndangered();
        assertEquals("rhino", testEndangered.getName());
    }

  //  @Test
  //  public void getSightingId_instantiatesWithSightingId_int() {
  //      Endangered testEndangered = setUpNewEndangered();
  //      assertEquals(1,testEndangered.getSightingId());
  ///  }

    @Test
    public void getHealth_instantiatesWithHealth_String() {
        Endangered testEndangered = setUpNewEndangered();
        assertEquals("ill", testEndangered.getHealth());
    }

    @Test
    public void getAge_instantiatesWithAge_String() {
        Endangered testEndangered = setUpNewEndangered();
        assertEquals("young", testEndangered.getAge());
    }



    @Test
    public void equals_returnsTrueIfObjectsSame_true() {
        Endangered testEndangered = setUpNewEndangered();
        Endangered otherEndangered = setUpNewEndangered();
        assertTrue(testEndangered.equals(otherEndangered));
    }

    @Test
    public void save_savesObjectsToDatabase_true() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        assertTrue(Endangered.all().get(0).equals(testEndangered));
    }

    @Test
    public void save_assignsIdToEndangered() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        Endangered savedEndangered = Endangered.all().get(0);
        assertEquals(savedEndangered.getId(), testEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        Endangered otherEndangered = new Endangered("","","");
        otherEndangered.save();
        assertEquals(true,Endangered.all().get(0).equals(testEndangered));
        assertEquals(true,Endangered.all().get(1).equals(otherEndangered));
    }

    @Test
    public void find_returnsEndangeredWithSameId_otherEndangered() {
        Endangered firstEndangered =setUpNewEndangered();
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("","","");
        secondEndangered.save();
        assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
    }
  //  @Test
 //   public void save_savesSightingIdIntoDB_true() {
 //       Sighting testSighting = new Sighting("","");
  //      testSighting.save();
   //     Endangered testEndangered = new Endangered("lion", testSighting.getId(),"","");
   //     testEndangered.save();
       // Endangered savedEndangered = Endangered.find(testEndangered.getId());
 //       assertEquals(savedEndangered.getSightingId(), testSighting.getId());
  //  }



   // @Test
   // public void save_recordsTimeOfCreationInDatabase() {
  //      Endangered testEndangered = setUpNewEndangered();
   //     testEndangered.save();
    //    Timestamp savedEndangeredCreatedAt = Endangered.find(testEndangered.getId()).getCreatedAt();
    //    Timestamp rightNow = new Timestamp(new Date().getTime());
    //    assertEquals(rightNow.getDay(), savedEndangeredCreatedAt.getDay());
  //.
   // }
    @Test
    public void delete_deletesEndangeredAnimal_true() {
        Endangered testEndangered = setUpNewEndangered();
        testEndangered.save();
        testEndangered.delete();
        assertEquals(null, Endangered.find(testEndangered.getId()));
    }



}