import java.security.Key;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class Crypt_skcrackme_1 {
    public byte[] encrypt(byte[] arr, String secret) {
        byte[] encryptedByte = (byte[]) null;
        try {
            Key secretkKey = new SecretKeySpec(secret.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, secretkKey);
            encryptedByte = cipher.doFinal(arr);
        } catch (Exception e) {
        }
        return encryptedByte;
    }


    public void main(String[] args){
        System.out.println("Chương trình tạo mã bí mật skcrackme_1!");

        System.out.print("Nhập tên của bạn: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if(name.length() > 15){
            System.out.println("Đầu vào không được nhiều hơn 15 kí tự!");
            return;
        }
        System.out.println("Tên của bạn: " + name);
        
        byte[] encryptedlayer1 = encrypt(name.getBytes(), "13377331");
        byte[] encryptedlayer2 = encrypt(encryptedlayer1, "13248657");
        System.out.println("Encrypt lớp 1: " + encryptedlayer1.toString());
        System.out.println("Encrypt lớp 2: " + encryptedlayer2.toString());

        StringBuilder result = new StringBuilder();
        int var = 1337;
        int key = (var * 32) / (16 * 16);
        for (int i = 0; i < encryptedlayer2.length; i++) {
            int original = ((encryptedlayer2[i] & 0xFF) + key) & 0xFF;

            if (i > 0) {
                result.append(" ");
            }
            result.append(original);
        }

        String recovered = result.toString();
        
        System.out.println("Chuỗi kết quả mã hóa: " + recovered);
    }
}