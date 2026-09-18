import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: KeyFile.class */
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
            if (Base64.getEncoder().encodeToString(messageDigest.digest()).equals(this.serial)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}