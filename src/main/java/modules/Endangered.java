package modules;

import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animals {
    public Endangered(String name,int sightingId) {
        this.name = name;
        this.sightingId = sightingId;

    }
    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }
    public static Endangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangered endangered= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
            return endangered;
        }
    }


}