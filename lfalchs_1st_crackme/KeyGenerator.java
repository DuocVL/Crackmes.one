import java.util.Random;
import java.util.Scanner;


public class KeyGenerator {
    
    public void main(){
        char[] a = {'0', '1', '2', '3', '4', '5', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String b = "LFalch";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập dữ liệu bạn muốn dùng để sinh key mã hóa:");
        String plain = scanner.nextLine();

        Random random = new Random(plain.hashCode() | b.hashCode());
        String key = "";
        for(int i = 0; i < 16;i++){
            key = String.valueOf(key) + a[random.nextInt(a.length)];
        }

        System.out.println("Dữ liệu đã nhập:" + plain);
        System.out.println("Key:" + key);
    }
}
