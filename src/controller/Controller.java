package controller;

import bo.ManagerWorker;
import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import utils.Validator;

/**
 * Quản lý luồng xử lý chính của chương trình: thêm công nhân, tăng/giảm lương, hiển thị lịch sử.
 */
public class Controller {

    private final ManagerWorker workers;

    public Controller() {
        this.workers = new ManagerWorker();
    }

    // Thêm công nhân mới
    public void addWorker() throws Exception {
        Worker worker = Worker.input(); // Nhập thông tin worker
        if (workers.isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists!");
        }
        workers.add(worker);
        System.out.println("✅ Added worker successfully!");
    }

    // Tăng lương
    public void upSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        if (!workers.isExist(code)) {
            throw new Exception("Cannot find worker with code: " + code);
        }
        double amount = Validator.getDouble("Enter amount: ", "Salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
        workers.changeSalary(SalaryStatus.UP, code, amount);
        System.out.println("✅ Salary increased successfully!");
    }

    // Giảm lương
    public void downSalary() throws Exception {
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+").toUpperCase();
        if (!workers.isExist(code)) {
            throw new Exception("Cannot find worker with code: " + code);
        }
        double amount = Validator.getDouble("Enter amount: ", "Salary must be > 0", "Invalid!",
                Double.MIN_VALUE, Double.MAX_VALUE);
         workers.changeSalary(SalaryStatus.DOWN, code, amount);
        System.out.println("✅ Salary decreased successfully!");
    }

    // Hiển thị lịch sử lương
    public void showHistory() throws Exception {
        String result = workers.salaryHistoryToString();
        if (result == null) {
            throw new Exception("Salary history is empty!");
        } else {
            System.out.println(result);
        }
    }
}
