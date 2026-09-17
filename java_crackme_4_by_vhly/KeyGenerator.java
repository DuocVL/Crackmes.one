import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.zip.CRC32;
import java.io.Serializable;

public class KeyFile implements Serializable {
    private String magic = "vhly[FR]'s CrackMe #4 KeyFile";
    private String name = "vhly[FR]";
    private String serial = "null";
    private long crcvalue = 0;

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setSerial(String str) {
        this.serial = str;
    }

    public final void setCRC(long j) {
        this.crcvalue = j;
    }

    public final boolean isVal() {
        CRC32 crc32 = new CRC32();
        crc32.update(this.magic.getBytes());
        crc32.update(this.name.getBytes());
        crc32.update(this.serial.getBytes());
        if (crc32.getValue() != this.crcvalue) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("aAF#@QRAJSEFjkaos0dvjkl;asefQ$@#%@Q$T%Jmoa0pl_)(*&(^*%^%$");
        stringBuffer.append("  ");
        stringBuffer.append(this.name);
        stringBuffer.append("    asdfjkl;asdfQ#$TSHSDFHGSdfgjkopasdu90zxcv");
        String string = stringBuffer.reverse().toString();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(string.getBytes());
            if ( Base64.getEncoder().encodeToString(messageDigest.digest()).equals(this.serial)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

public class KeyGenerator {

    public void main() throws Exception{

        //Nhập name muốn dùng tạo object 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập name mà bạn muốn dùng tạo file object: ");
        String name = scanner.nextLine();
        String magic = "vhly[FR]'s CrackMe #4 KeyFile";
        String secret =
                "aAF#@QRAJSEFjkaos0dvjkl;asefQ$@#%@Q$T%Jmoa0pl_)(*&(^*%^%$"
                + "  "
                + name
                + "    asdfjkl;asdfQ#$TSHSDFHGSdfgjkopasdu90zxcv";

        // Reverse chuỗi
        String reversed = new StringBuilder(secret).reverse().toString();

        // SHA-1
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] digest = sha1.digest(reversed.getBytes(StandardCharsets.UTF_8));

        // Base64
        String serial = Base64.getEncoder().encodeToString(digest);

        // CRC32
        CRC32 crc = new CRC32();

        crc.update(magic.getBytes(StandardCharsets.UTF_8));
        crc.update(name.getBytes(StandardCharsets.UTF_8));
        crc.update(serial.getBytes(StandardCharsets.UTF_8));

        long crcValue = crc.getValue();

        // Tạo KeyFile
        KeyFile keyFile = new KeyFile();

        keyFile.setName(name);
        keyFile.setSerial(serial);
        keyFile.setCRC(crcValue);

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("key.dat"))) {

            out.writeObject(keyFile);
        }

        System.out.println("Name   : " + name);
        System.out.println("Serial : " + serial);
        System.out.println("CRC    : " + crcValue);
        System.out.println("File   : key.dat");
    }
}