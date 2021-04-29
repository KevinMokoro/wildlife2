package modules;

import java.sql.Timestamp;

public abstract class Animals{
    public String name;
    public int id;
    public int sightingId;
    public Timestamp createdAt;
    public String type;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public int getSightingId(){
        return sightingId;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animals)) {
            return false;
        } else {
            Animals newAnimal = (Animals) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getSightingId() == newAnimal.getSightingId();
        }
    }
}