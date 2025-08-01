package CollectionPractice;

public class EmployeeModal implements Comparable<EmployeeModal> {

    int emp_id;
    String emp_name;
    String department;

    public EmployeeModal(int emp_id, String emp_name, String department) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.department = department;
    }

    @Override
    public int compareTo(EmployeeModal o) {
        return this.emp_id - o.emp_id;
    }

    @Override
    public String toString() {
        return "EmployeeModal{" +
                "emp_id=" + emp_id +
                //   ", emp_name='" + emp_name + '\'' +
                //   ", department='" + department + '\'' +
                '}';
    }

}
