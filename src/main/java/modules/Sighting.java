package modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.sql2o.*;

public class Sighting {
    private String location;
    private String ranger;
    private int id;

    public Sighting(String location,String ranger) {
       this.location = location;
       this.ranger = ranger;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return Objects.equals(location, sighting.location) &&
                Objects.equals(ranger, sighting.ranger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, ranger);
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (location, ranger) VALUES (:location, :ranger)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", this.location)
                    .addParameter("ranger", this.ranger)
                    .executeUpdate()
                    .getKey();

        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);

        }
    }
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;

        }
    }
  //  public List<Object> getAnimals() {
   //     List<Object> allAnimals = new ArrayList<Object>();
    //    try(Connection con = DB.sql2o.open()) {
    //        String sql = "SELECT * FROM animals where sightingId=:id";
     //       return con.createQuery(sql)
      //              .addParameter("id", this.id)
       //             .executeAndFetch(Animal.class);
     //   }
  //  }


}