package _kakao;

public class Location {
    int id;
    int located_bikes_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocated_bikes_count() {
        return located_bikes_count;
    }

    public void setLocated_bikes_count(int located_bikes_count) {
        this.located_bikes_count = located_bikes_count;
    }
    @Override
    public String toString(){
        return located_bikes_count + " ";
    }
}
