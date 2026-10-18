       
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//Lớp Validator có chức năng chính là xử lý việc nhập liệu từ bàn phím một cách an toàn,
//hợp lệ và được kiểm tra điều kiện rõ ràng.
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    private Validator(){
        
    }
    
    //Hàm lấy số nguyên hợp lệ trong khoảng [min, max]
    public static int getInt(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,int min,int max){
        do {            
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if(number>=min && number<=max){
                    return number;
                }else{
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
    
    //Hàm lấy số thực hợp lệ trong khoảng [min, max]
    public static double getDouble(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,double min,double max){
        do {            
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if(number>=min && number<=max){
                    return number;
                }else{
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
    
   
    //Hàm lấy chuỗi hợp lệ theo regex
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();

            if (str != null && !str.trim().isEmpty() && str.trim().matches(REGEX)) {
                return str.trim();
            }

            System.out.println(messageError);
        } while (true);
    }

    
    //Hàm lấy ngày tháng hợp lệ nằm trong khoảng min-max và đúng định dạng
    public static Date getDate(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorDate,final String REGEX,
            Date min,Date max){
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {            
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                if(date.compareTo(min)>=0&&date.compareTo(max)<=0){
                    return date;
                }
                System.out.println(messsageErrorOutOfRange);
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }
    
}
