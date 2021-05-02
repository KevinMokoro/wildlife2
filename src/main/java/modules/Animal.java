package modules;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Animal extends Animals implements DatabaseManagement {
    public static final String DATABASE_TYPE = "animal";

    public Animal(String name,int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
      //  endangered = false;
        type = DATABASE_TYPE;

    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type ='animal' ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal= con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }






}