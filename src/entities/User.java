package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    private int id;
    private String name;
    private int age;
    public  List<Carbon> carbons =  new ArrayList<>();


    public User(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public User(int id , String name, int age) {
        this.name = name;
        this.age = age;
        this.id = id;

    }




    public List<Carbon> getCarbons() {
        return carbons;
    }
    public boolean hasCarbon(int carbonId) {
        return carbons.stream().anyMatch(c -> c.getId() == carbonId);
    }



    public void addCarbon(Carbon carbon) {
        carbons.add(carbon);
    }


    public User() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", carbons =" + this.getCarbons() +
                '}';
    }
}
