package modules;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Animal extends Animals {

    public Animal(String name,int sightingId) {
        this.name = name;
        this.sightingId = sightingId;

    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }






}