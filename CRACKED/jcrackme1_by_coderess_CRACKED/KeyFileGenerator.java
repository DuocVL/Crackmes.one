import java.io.FileOutputStream;
import java.util.Arrays;

class KeyFileGenerator {
    public void main(){
        System.out.println("Chương trình tạo keyfile.txt đơn giản!");
        System.out.println("Tạo 1 file có kích thước 10101 byte tính cả kí tự xuống dòng(nếu có)");
        System.out.println("Yêu cầu kí tự thứ 10001 phải là '#'");
        System.out.println("Các kí tự khác túy ý trong khoảng '0'-'9' hoặc 'A'-'Z'");
        char c = 'A';
        byte[] data = new byte[10101];

        Arrays.fill(data, (byte) c);

        data[10000] = '#';

        try (FileOutputStream fos = new FileOutputStream("keyfile.txt")) {
            fos.write(data);
        } catch (Exception e){
            System.err.println(e);
        }

        System.out.println("Ghi file thành công!");
    }
}