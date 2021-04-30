package modules;

import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animals {
    private String health;
    private String age;

    public static final String HEALTH_ILL = "ill";
    public static final String HEALTHY_HEALTHY = "healthy";
    public static final String HEALTHY_OK = "ok";

    public static final String AGE_BABY = "newborn";
    public static final String AGE_YOUNG = "young";
    public static final String AGE_ADULT = "adult";

    public Endangered(String name,int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
      //  this.health = health;
       // this.age = age;




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

   // public String getHealth() {
    //    return health;
  //  }

  //  public String getAge() {
  //      return age;
  //  }
}