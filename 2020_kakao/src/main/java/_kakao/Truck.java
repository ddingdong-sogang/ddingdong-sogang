package _kakao;

public class Truck {
    private int id;
    private int location_id;
    private int loaded_bikes_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getLoaded_bikes_id() {
        return loaded_bikes_id;
    }

    public void setLoaded_bikes_id(int loaded_bikes_id) {
        this.loaded_bikes_id = loaded_bikes_id;
    }

    @Override
    public String toString(){
        return "id : " + id + " location id : " + location_id + " getLoadedBike : " + loaded_bikes_id;
    }
}
