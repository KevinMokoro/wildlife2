package modules;

import java.util.List;
import java.util.Objects;
import org.sql2o.*;

public class Sighting {
    private String location;
    private String ranger;

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
            con.createQuery(sql)
                    .addParameter("name", this.location)
                    .addParameter("email", this.ranger)
                    .executeUpdate();
        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);

        }
    }
}