package HomeWork5;

public class main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Semenov", "Dmitriy", "Vladimirovich", "Ingeneer", "sem@ya.ru", "796001234567", 43000, 33);
        persArray[1] = new Person("Ivanov", "Pavel", "Ivanovich", "Master", "pashka@ya.ru", "799901234567", 41000, 49);
        persArray[2] = new Person("Pavlov", "Alex", "Romanovich", "Ingeneer", "qwe@bk.ru", "79371234567", 47000, 42);
        persArray[3] = new Person("Rogov", "Vasya", "Sergeevich", "Ingeneer", "s_1@bk.ru", "796001234567", 35000, 35);
        persArray[4] = new Person("Petrov", "Oleg", "Vladimirovich", "Ingeneer", "sem@vk.com", "796001234567", 33000, 27);


        getAllPerson(persArray);
        System.out.println("\n************\n");
        getOldPerson(persArray);
    }
    private static void getOldPerson(Person[] persArray){
        System.out.println("Old Persons:\n");
        for (int i = 0; i < persArray.length; i++)
            if (persArray[i].getAge() > 40)
                System.out.println(persArray[i].getInfo());
    }
    private static void getAllPerson(Person[] persArray){
        System.out.println("All Empoyees:\n");
        for (int i = 0; i < persArray.length; i++)
                System.out.println((i + 1) + " " + persArray[i].getInfo());
    }



}
