import java.security.MessageDigest;
import java.util.Scanner;
import java.util.Base64;

class KeyGenerator{
    public  byte[] hash(byte[] text) {
        byte[] bytehash;
        try {
            bytehash = MessageDigest.getInstance("MD5").digest(text);
            return bytehash;
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public void main(){
        System.out.println("Chương trinhf tạo khóa giải mã crack_me_1_by_luk4sapps");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên đầu vào của bạn: ");
        String name = scanner.nextLine();
        System.out.println("Đầu vào name:" + name);
        
        String key = new StringBuilder(new String(Base64.getEncoder().encode(hash(name.getBytes()))))
        .reverse().toString();

        System.out.println("Key:" + key);        
    }
}