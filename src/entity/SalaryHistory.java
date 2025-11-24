
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

//Lớp SalaryHistory dùngghi nhận thông tin lịch sử thay đổi lương của một công (`WoWorker)
public class SalaryHistory implements Comparable<SalaryHistory>{
    private String id;
    private double salaryUpdate;
    private SalaryStatus status;
    private Date date;

    public SalaryHistory() {
    }

    public SalaryHistory(String id, double salaryUpdate, SalaryStatus status, Date date) {
        setId(id);
        setSalaryUpdate(salaryUpdate);
        setStatus(status);
        setDate(date);
    }

    public String getId() {
        return id;
    }

     public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID công nhân không được để trống hoặc null.");
        }
        this.id = id;
    }

    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    public void setSalaryUpdate(double salaryUpdate) {
        if (salaryUpdate <= 0) {
            throw new IllegalArgumentException("Lương cập nhật phải > 0.");
        }
        this.salaryUpdate = salaryUpdate;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái thay đổi lương không được null.");
        }
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Ngày thay đổi lương không được null.");
        }
        // Không cho phép đặt ngày trong tương lai
        Date now = new Date();
        if (date.after(now)) {
            throw new IllegalArgumentException("Ngày thay đổi lương không được ở tương lai.");
        }
        this.date = date;
    }

    @Override
public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return String.format("%7s%15.2f%15s%20s\n",
            id, salaryUpdate, status, dateFormat.format(date));
}


    @Override
    public int compareTo(SalaryHistory o) {
        return this.id.compareTo(o.id);
    }
    
    
}
