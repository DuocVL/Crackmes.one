import java.security.MessageDigest;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.zip.CRC32;


public class GenKey {

    // Kiểm tra thời gian hiện tại của hệ thống có hợp lệ để chạy chương trình ko 
    void checkTime(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(1983, 1, 13);
        gregorianCalendar.add(1, 1);
        System.out.println("Time: " + gregorianCalendar.getTime());
        if (gregorianCalendar.after(gregorianCalendar2)) {
            System.out.println("Time Error");
        }else {
            System.out.println("Time Finished");
        }
    }

    //Tạo Seniral  
    void genSerial(String name){
        String str = "";
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int year = gregorianCalendar.get(Calendar.YEAR);
        int month = gregorianCalendar.get(Calendar.MONTH) + 1;
        int date = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Date: " + date);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            switch (date % 5) {
                case 0:
                    str = "Good Luck For Writer";
                    break;
                case 1:
                    str = "Foikd This is Very EasY?";
                    break;
                case 2:
                    str = "The Day is g\\ook/risse.x-> vhly";
                    break;
                case 3:
                    str = "No ONe Can sToP mE!ARe You";
                    break;
                case 4:
                    str = "Thank you for CraCK This :)";
                    break;
            }
            messageDigest.update(str.getBytes());
            switch (month % 3) {
                case 0:
                    str = "Email is What? \"vhly@163.com is Right!\",So YOu Can Only Make a KeyGen";
                    break;
                case 1:
                    str = "I love YoU , My Girls, HAHaaaaa!";
                    break;
                case 2:
                    str = "GivE a Right Reason, I Think this CrackMe 's Serial is Ofen change for A Name";
                    break;
            }
            messageDigest.update(str.getBytes());
            char[] charArray = name.toCharArray();
            char c = (char) (' ' + ((char) (date % 4)));
            for (int i = 0; i < charArray.length; i++) {
                charArray[i] = (char) (charArray[i] ^ c);
            }
            str = new String(charArray);
            messageDigest.update(str.getBytes());
            String strEncode = Base64.getEncoder().encodeToString(messageDigest.digest());
            String serial = new StringBuilder(strEncode).reverse().toString();

            System.out.println("Serial: " + serial);
            
            byte[] bytes = serial.getBytes();
            byte[] bytes2 = strEncode.getBytes();
            int i = 0;
            int length = bytes2.length - 1;
            while (i < bytes.length) {
                if ((bytes[i] ^ bytes2[length]) != 0) {
                    System.out.println("Không chính xác!");
                    return;
                } else {
                    i++;
                    length--;
                }
            }
            System.out.println("Serial chính xác!" );
        }catch (Exception e) {
            System.err.println(e);
        }
    }

    //Tạo Password 
    void genPassword(String name){
        char[] charArray = ("VHLY IS " + name + " The World 's Kid").toCharArray();
        char c = new GregorianCalendar().get(Calendar.DAY_OF_MONTH) % 6 == 3 ? (char) (65 ^ 71) : (char) (65 ^ 115);
        for (int i = 0; i < charArray.length; i++) {
            int i2 = i;
            charArray[i2] = (char) (charArray[i2] ^ c);
        }
        byte[] bytes = new String(charArray).getBytes();
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        long hash = crc32.getValue();
        long password = hash - 834;
        String strPassword = Long.toString(password, 36);
        System.out.println("Password: " + strPassword);
        if (hash - Long.parseLong(strPassword, 36) == 834) {
            System.out.println("Kiểm tra password thành công!");
            return;
        }
        System.out.println("Kiểm tra password lỗi!");
    }

    public void main(){
        checkTime();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập name bạn muốn dùng để sinh key độ dài >=3 , <= 16: ");
        String name = scanner.nextLine();
        System.out.println("Name: " + name);
        if(name.length() < 3 || name.length() > 16){
            System.out.println("Name không hợp lệ!");
            return;
        }
        genSerial(name);
        genPassword(name);
    }    
}
