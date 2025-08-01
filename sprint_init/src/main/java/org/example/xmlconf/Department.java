package org.example.xmlconf;

public class Department extends Company{

    int depId;
    String depName;

    public Department() {
        System.out.println("In Department constructor");
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depName='" + depName + '\'' +
                '}';
    }
}
