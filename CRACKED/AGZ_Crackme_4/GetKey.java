import java.io.InputStream;
import java.util.Base64;
import java.util.Scanner;

class GetKey {
    byte[] buffer;
    String[] str = new String[256];
    int[] read = new int[256];

    public String getString(int i) {
        int i2 = i & 255;
        if (read[i2] != i) {
            read[i2] = i;
            if (i < 0) {
                i &= 65535;
            }
            str[i2] = new String(buffer, i, buffer[i - 1] & 255);
        }
        return str[i2];
    }
    private static String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    private static String decodeBase64(String str) {
        return new String(Base64.getDecoder().decode(str));
    }


    public void main(){
        int i;
        try {
            InputStream inputStream = new GetKey().getClass().getResourceAsStream("I/I.gif");
            if (inputStream != null) {
                int size = (inputStream.read() << 16) | (inputStream.read() << 8) | inputStream.read();
                buffer = new byte[size];
                int j = 0;
                byte b = (byte) size;
                while (size != 0 && (i = inputStream.read(buffer, j, size)) != -1) {
                    size -= i;
                    int i4 = i + j;
                    while (j < i4) {
                        int i5 = j;
                        buffer[i5] = (byte) (buffer[i5] ^ b);
                        j++;
                    }
                }
                inputStream.close();
            }
        } catch (Exception e) {
        }

        System.out.println("I.I(191): " + getString(191));
        System.out.println("I.I(207): " + getString(207));
        System.out.println("I.I(238): " + getString(238));
        System.out.println("I.I(1): " + getString(1));
        System.out.println("I.I(14): " + decodeBase64(getString(14)));
        System.out.println("I.I(31): " + decodeBase64(getString(31)));
        System.out.println("I.I(40): " + decodeBase64(getString(40)));
        System.out.println("I.I(165): " + decodeBase64(getString(165)));
        System.out.println("I.I(182): " + decodeBase64(getString(182)));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập dữ liệu bạn muốn dùng sinh key: ");
        String input1 = scanner.nextLine();
        String str4 = getString(1);
        String input3 = decodeBase64(str4);
        String input2 = encodeBase64(input1);

        System.out.println("Input1: " + input1);
        System.out.println("Input2: " + input2);
        System.out.println("Input3: " + input3);
    }
}