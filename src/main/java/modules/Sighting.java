package modules;

import java.util.Objects;

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
}