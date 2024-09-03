package entities;

import java.util.List;
import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private int age;


    public static List<Carbon> carbons =  new ArrayList<>();


    public List<Carbon> getCarbons() {
        return carbons;
    }





    public User(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
     //  this.carbons = new ArrayList<>();
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


}
