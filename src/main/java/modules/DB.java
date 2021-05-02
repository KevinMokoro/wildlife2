package modules;

import org.sql2o.*;

//!public class DB {
 //   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "kevin", "  ");

//}


public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postresql://ec2-18-211-97-89.compute-1.amazonaws.com:5432/dcm4kbn00ppfhi", "hqaocomjvkyfhj", "a5b81c8e022b220ea529e5a01372e4dd00d6aa9a4742ba688a47b0ac0dc0de62");
}