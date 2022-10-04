package model;

public class Employee {
    private String name;
    private int age;
    private boolean isManager;
    private int wage;

    public Employee(String name, int age, boolean isManager, int wage) {
        this.name = name;
        this.age = age;
        this.isManager = isManager;
        this.wage = wage;
    }

    public void createEmployee(String name, int age, boolean isManager, int wage) {
        Employee e = new Employee(name, age, isManager, wage);
        e.name = name;
        e.age = age;
        e.isManager = isManager;
        e.wage = wage;
    }

    public void removeEmployee(String name) {
        //stub
    }

    // getters

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isManager() {
        return isManager;
    }

    public int getWage() {
        return wage;
    }
}
