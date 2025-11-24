package bo;

import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Quản lý danh sách công nhân (Worker) + lịch sử thay đổi lương (SalaryHistory)
 */
public class ManagerWorker {

    private List<Worker> list;                     // Danh sách công nhân
    private List<SalaryHistory> salaryHistories;   // Danh sách lịch sử lương

    public ManagerWorker() {
        this.list = new ArrayList<>();
        this.salaryHistories = new ArrayList<>();
    }

    // ===================== Getter / Setter =====================
    public List<Worker> getList() {
        return list;
    }

    public void setList(List<Worker> list) {
        if (list == null) {
            throw new IllegalArgumentException("Danh sách công nhân không được null.");
        }
        this.list = list;
    }

    public List<SalaryHistory> getSalaryHistories() {
        return salaryHistories;
    }

    public void setSalaryHistories(List<SalaryHistory> salaryHistories) {
        if (salaryHistories == null) {
            throw new IllegalArgumentException("Danh sách lịch sử lương không được null.");
        }
        this.salaryHistories = salaryHistories;
    }

    // ===================== Quản lý Worker =====================
    private Worker getWorker(String id) {
        for (Worker workers : list) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return workers;
            }
        }
        return null;
    }

    public boolean isExist(String id) {
        for (Worker workers : list) {
            if (workers.getId() != null && workers.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Worker worker) throws Exception {
        if (isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists.");
        }
        return list.add(worker);
    }

    // 💰 Thay đổi lương công nhân (và tự động ghi lịch sử lương)//tim cong nhan theo ma code
    public void changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty! Cannot change salary");
        }

        Worker worker = getWorker(code);
        if (worker == null) {
            throw new Exception("Worker ID not found: " + code);
        }

        if (amount <= 0) {
            throw new Exception("Amount of money must be > 0");
        }

        double newSalary;
        if (status == SalaryStatus.UP) {
            newSalary = worker.getSalary() + amount;
        } else if (status == SalaryStatus.DOWN) {
            if (worker.getSalary() - amount <= 0) {
                throw new Exception("Cannot decrease salary below 0");
            }
            newSalary = worker.getSalary() - amount;
        } else {
            throw new Exception("Invalid SalaryStatus value");
        }

        // Cập nhật lương
        worker.setSalary(newSalary);

        // Ghi lại lịch sử thay đổi lương (theo cấu trúc SalaryHistory hiện tại)
        SalaryHistory history = new SalaryHistory(worker.getId(), newSalary, status, new Date());
        salaryHistories.add(history);

        
    }

    // ===================== Quản lý SalaryHistory =====================
    private void sortByID() {
        Collections.sort(salaryHistories);
    }

    public String salaryHistoryToString() {
        if (salaryHistories.isEmpty()) {
            return null;
        }
        sortByID();
        String str = String.format("%7s%15s%15s%20s\n", "Code", "Salary", "Status", "Date");
        for (SalaryHistory h : salaryHistories) {
            str += h.toString();
        }
        return str;
    }
}
