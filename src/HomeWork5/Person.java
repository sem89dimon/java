package HomeWork5;

public class Person {

    private String surname;
    private String name;
    private String secondname;
    private String post;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String surname, String name, String secondname,
                  String post, String email, String phone, int salary, int age) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;


    }
    int getAge(){
        return age;
    }

    String getInfo(){
        return this.surname + " " +
                this.name + " " +
                this.secondname + " " +
                this.post + " email: " +
                this.email + " Phone number: " +
                this.phone + " salary is " +
                this.salary + " " +
                this.age + " years old";
    }
}
