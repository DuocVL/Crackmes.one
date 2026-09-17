import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class ExtractClass {
    void extractClass(){
        String[] nameFiles = { "KeyFile.class", "Main$1.class", "Main.class" };
        for (String namefile : nameFiles){
            String fullPathIn = "com/vhly/crackmes/cm4/" + namefile;
            String fullPathOut = "extracted/" + namefile;
            try (
                InputStream in = new FileInputStream(fullPathIn);
                OutputStream out = new FileOutputStream(fullPathOut)
            ){
                byte[] buffer = new byte[8192];
                int n;
                while ((n = in.read(buffer)) != -1) {
                    for(int i = 0; i < n ; i++){
                        buffer[i] = (byte) (buffer[i] ^ 168 ^ 99);
                    }
                    out.write(buffer, 0, n);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            System.out.println("ĐỌc và ghi thành công file: " + namefile);
        }
        System.out.println("ĐỌc và ghi thành công tất cả các File!");
    }
    public void main(){
        extractClass();
    }
}
