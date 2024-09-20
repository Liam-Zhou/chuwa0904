package com.hw3.factorypattern;

public class PersonFactory {
    public Person getPerson(String personType) {
        if (personType == null)  return null;
        if (personType.equalsIgnoreCase("TEACHER")) {
            return new Teacher();
        }
        else if (personType.equalsIgnoreCase("ENGINEER")) {
            return new Engineer();
        }
        else if (personType.equalsIgnoreCase("DOCTOR")) {
            return new Doctor();
        }
        return null;
    }
}
