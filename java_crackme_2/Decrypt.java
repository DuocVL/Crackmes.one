import java.io.InputStream;
import java.security.MessageDigest;
import java.util.zip.CRC32;
import java.util.Scanner;

public class Decrypt {

    byte[] data;
    String[] cache = new String[256];
    int[] offsets = new int[256];

    public String getString(int offset) {

        // int cacheIndex = offset & 0xFF;
        int cacheIndex = offset;
        if (offsets[cacheIndex] != offset) {
            offsets[cacheIndex] = offset;
            // if (offset < 0) offset &= 0xFFFF;
            //int length = data[offset - 1] & 0xFF;
            int length = data[offset - 1];
            cache[cacheIndex] = new String(data, offset, length).intern();
        }

        return cache[cacheIndex];
    }
    public void main(String[] args){
        System.out.println("Chuong trinh đọc file I.gif!");
        
        //Đọc các chuỗi lưu trong tệp I.gif
        try {
            InputStream in = Decrypt.class.getResourceAsStream("I.gif");
            //Đọc kích thước lưu kiểu big endian
            int size = (in.read() << 16) | (in.read() << 8) | in.read();
            data = new byte[size];
            byte key = (byte) size;
            in.read(data);

            for (int i = 0; i < size; i++) {
                data[i] ^= key;
            }
            in.close();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("I.I(1): " + getString(1));
        System.out.println("I.I(6): " + getString(6));
        System.out.println("I.I(12): " + getString(12));
        System.out.println("I.I(47): " + getString(47));
        System.out.println("I.I(51): " + getString(51));
        System.out.println("I.I(60): " + getString(60));
        System.out.println("I.I(76): " + getString(76));

        //Tính toán chuỗi secret 
        System.out.println("Nhập name dùng để sinh mã hóa độ dài > 3:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(getString(47));
            messageDigest.update(getString(51).getBytes());
            messageDigest.update(name.getBytes());
            byte[] passwordlayer1 = messageDigest.digest();

            CRC32 crc32 = new CRC32();
            crc32.update(passwordlayer1);
            long passwordlayer2 = crc32.getValue();
            String passwordfinal = Long.toString(passwordlayer2, 36);

            System.out.println("Mật khẩu dùng để nhập là: " + passwordfinal);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}