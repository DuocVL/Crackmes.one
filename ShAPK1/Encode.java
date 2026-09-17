import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Encode {

    String key = "NQALCgEDDDEzUjpTBwocBgcDPTIIGwIK";

    public byte[] encrypt(byte[] bArr) {
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ bytes[i % length]);
        }
        return bArr2;
    }
    public void main(String[] args) {
        //decode byte key 
        byte[] decode = Base64.getDecoder().decode(key);
        //tao chuoi pass
        String pass = new String(encrypt(decode), StandardCharsets.UTF_8);
        System.out.println("Pass: " + pass);

        //Kiem tra
        if (Arrays.equals(Base64.getEncoder().encode(encrypt(pass.getBytes())), key.getBytes())) {
            System.out.println("YES! PASSWORD IS CORRECT!!");
        } else {
            System.out.println("PASSWORD IS WRONG!!");
        }
    }
}
