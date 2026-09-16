import java.io.InputStream;
import java.util.Base64;
import java.util.Scanner;

public class JavaCrackme1 {
    
    byte[] buffer;
    String[] getClass = new String[256];
    int[] getResourceAsStream = new int[256];

    public String geString(int i) {
        int i2 = i & 255;
        if (getResourceAsStream[i2] != i) {
            getResourceAsStream[i2] = i;
            if (i < 0) {
                i &= 65535;
            }
            getClass[i2] = new String(buffer, i, buffer[i - 1] & 255).intern();
        }
        return getClass[i2];
    }

    public void main(String[] args) {

        try {
            InputStream resourceAsStream = new JavaCrackme1().getClass().getResourceAsStream("I.gif");
            if (resourceAsStream != null) {
                int size = (resourceAsStream.read() << 16) | (resourceAsStream.read() << 8) | resourceAsStream.read();
                buffer = new byte[size];
                int i = 0;
                byte key = (byte) size;
                byte[] bArr = buffer;
                int k = 0;
                while (size != 0 && (k = resourceAsStream.read(bArr, i, size)) != -1) {
                    size -= i;
                    int j = k + i;
                    while (i < j) {
                        int m = i;
                        bArr[m] = (byte) (bArr[m] ^ key);
                        i++;
                    }
                }
                resourceAsStream.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(buffer.length);
        
        System.out.println("I.I(90): " + geString(90));
        System.out.println("I.I(96): " + geString(96));
        System.out.println("I.I(6): " + geString(6));
        System.out.println("I.I(1): " + geString(1));
        System.out.println("I.I(101): " + geString(101));
        System.out.println("I.I(12): " + geString(12));
        System.out.println("I.I(47): " + geString(47));
        System.out.println("I.I(63): " + geString(63));
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập name mà bạn muốn dùng để mã hóa: ");
        String name = scanner.nextLine();

        String pass = Base64.getEncoder().encodeToString(name.getBytes());

        System.out.println("Name: " + name);
        System.out.println("Password: " + pass);
    }
}
