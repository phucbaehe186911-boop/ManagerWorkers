
package entity;

import utils.Validator;
import bo.ManagerWorker;

// Lưu trữ dữ liệu của một công nhân, đồng thời cung cấp phương thức input()
public class Worker {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String workLocation;

    public Worker() {
    }

    public Worker(String id, String name, int age, double salary, String workLocation) {
        setId(id);
        setName(name);
        setAge(age);
        setSalary(salary);
        setWorkLocation(workLocation);
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID không được để trống hoặc null.");
        }
        this.id = id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên công nhân không được để trống hoặc null.");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 50) {
            throw new IllegalArgumentException("Tuổi công nhân phải từ 18 đến 50.");
        }
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Lương không được âm.");
        }
        this.salary = salary;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        if (workLocation == null || workLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Nơi làm việc không được để trống hoặc null.");
        }
        this.workLocation = workLocation.trim();
    }

   
    // input Worker
    public static Worker input() throws Exception {
        Worker worker = new Worker();

        worker.setId(Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase());
        worker.setName(Validator.getString(
        "Enter Name: ",
        "Tên công nhân không được để trống hoặc không hợp lệ!",
        "[A-Za-z\\s]+"
));
        worker.setAge(Validator.getInt("Enter age: ", "age >= 18 and <= 50 !", "Invalid!", 18, 50));
        worker.setSalary(Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE));
        worker.setWorkLocation(Validator.getString("Enter work location: ", "Invalid!", "[A-Za-z\\s]+"));

        return worker;
    }

    @Override
    public String toString() {
        return "Worker{" + id + ", " + name + ", " + age + ", " + salary + ", " + workLocation + '}';
    }
}
