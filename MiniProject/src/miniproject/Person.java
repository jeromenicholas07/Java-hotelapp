package miniproject;


public class Person implements java.io.Serializable{
    String name,gender;
    int age;

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    
    
}
