import java.io.InputStream;
import java.util.Base64;
import java.util.Scanner;

class KeyGen{

    byte[] getResourceAsStream;
    String[] intern = new String[256];
    int[] read = new int[256];

    public void readFile(){
        int i;
        try {
            InputStream resourceAsStream = new KeyGen().getClass().getResourceAsStream("I.gif");
            if (resourceAsStream != null) {
                int i2 = (resourceAsStream.read() << 16) | (resourceAsStream.read() << 8) | resourceAsStream.read();
                getResourceAsStream = new byte[i2];
                int i3 = 0;
                byte b = (byte) i2;
                byte[] bArr = getResourceAsStream;
                while (i2 != 0 && (i = resourceAsStream.read(bArr, i3, i2)) != -1) {
                    i2 -= i;
                    int i4 = i + i3;
                    while (i3 < i4) {
                        int i5 = i3;
                        bArr[i5] = (byte) (bArr[i5] ^ b);
                        i3++;
                    }
                }
                resourceAsStream.close();
            }
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

    public String getString(int i){
        int i2 = i & 255;
        if (read[i2] != i) {
            read[i2] = i;
            if (i < 0) {
                i &= 65535;
            }
            intern[i2] = new String(getResourceAsStream, i, getResourceAsStream[i - 1] & 255).intern();
        }
        return intern[i2];
    }

    String decodeInput(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    double stringToDouble(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append((int) c);
        }
        return Double.parseDouble(sb.toString());
    }

    double getInfoMachine(double d) {
        if (d == 0.0d) {
            System.exit(0);
            return 0.0d;
        }
        double dAvailableProcessors = Runtime.getRuntime().availableProcessors();
        double d2 = Runtime.getRuntime().totalMemory();
        System.out.println(
            "Processors = " +
            Runtime.getRuntime().availableProcessors()
        );

        System.out.println(
            "Total Memory = " +
            Runtime.getRuntime().totalMemory()
        );
        return (((d - (d2 / 1.0E8d)) + (dAvailableProcessors * 28.0d)) + (d2 / 107543.0d)) - (d2 / ((double) Double.toString(d).length()));
    }

    String doubleValue(double d) {
        return Double.toString(d);
    }

    public void main(){
        // readFile();
        // System.out.println("decodeInput(I.I(1)): " + decodeInput(getString(1)));
        // System.out.println("decodeInput(I.I(14)): " + decodeInput(getString(14)));
        // System.out.println("decodeInput(I.I(319)): " + decodeInput(getString(319)));
        // System.out.println("decodeInput(I.I(181)): " + decodeInput(getString(181)));
        // System.out.println("decodeInput(I.I(254)): " + decodeInput(getString(254)));

        // System.out.println("I.I(35)): " + getString(35));
        // System.out.println("I.I(364): " + getString(364) + 5);
        // System.out.println("I.I(380)): " + getString(380));
        // System.out.println("I.I(411)): " + getString(411));
        // System.out.println("I.I(65)): " + getString(65));
        // System.out.println("I.I(83): " + getString(83));
        // System.out.println("I.I(108)): " + getString(108));
        // System.out.println("I.I(125)): " + getString(125));
        // System.out.println("I.I(146)): " + getString(146));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap username bạn muốn sử dụng: ");
        String username = scanner.nextLine();
        System.out.println("Username: " + username);

        double username_hash = stringToDouble(username);
        double password = getInfoMachine(username_hash);
        System.out.println("Number: " + Double.toString(password));
    
    }
}