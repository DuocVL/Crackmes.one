import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class SianVMKeyFind {

    private static boolean T() {
        try {
            int[] iArr = {89, 125, 124, 97, 122, 123, 108, 126};
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[5];
            for (int i = 0; i < 4; i++) {
                bArr[i] = (byte) (iArr[i] ^ 15);
            }
            for (int i = 0; i < 5; i++) {
                bArr2[i] = (byte) (iArr[i + 3] ^ 15);
            }
            String str = new String(bArr, "UTF-8");
            String str2 = new String(bArr2, "UTF-8");
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 85);
            }
            for (int i = 0; i < bytes2.length; i++) {
                bytes2[i] = (byte) (bytes2[i] ^ 85);
            }
            String str3 = new String(bytes, "UTF-8");
            String str4 = new String(bytes2, "UTF-8");
            for (String str5 : java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                if (str5.contains(str3) || str5.contains(str4)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private byte[] Y(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        byte[] bArr3 = {58, 127, -60, -46};

        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ bArr3[i % bArr3.length]);
            bArr2[i] = (byte) ((bArr2[i] - (i * 7)) & 255);
        }

        return bArr2;
    }

    private byte[] readFile(String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) { throw new FileNotFoundException("Không tìm thấy: " + path); }

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream input = new FileInputStream(file)) {
            int offset = 0;

            while (offset < data.length) {
                int count = input.read(data, offset, data.length - offset);
                if (count == -1) {
                    throw new IOException("Không đọc đủ file: " + path);
                }
                offset += count;
            }
        }
        return data;
    }

    public static void main(String[] args) {

        String inputFile = "server/data2.sian";
        String outputFile = "com.sian.A.class";

        try {
            System.out.println("Đọc: " + inputFile);

            // 1. Đọc data1.sian
            byte[] data = new SianVMKeyFind().readFile(inputFile);

            System.out.println("Kích thước: " + data.length + " bytes");

            // 2. Giải mã
            byte[] value = new SianVMKeyFind().Y(data);

            // 3. Kiểm tra magic number CA FE BA BE
            if (value.length < 4 ||
                (value[0] & 0xFF) != 0xCA ||
                (value[1] & 0xFF) != 0xFE ||
                (value[2] & 0xFF) != 0xBA ||
                (value[3] & 0xFF) != 0xBE) {
                System.err.println("Giải mã không tạo ra Java class.");
                return;
            }

            // 4. Xuất bytecode ra file .class
            try (FileOutputStream output = new FileOutputStream(outputFile)) {

                output.write(value);
            }

            System.out.println("Giải mã thành công.");
            System.out.println("Đã xuất: " + outputFile);
            System.out.println("Kích thước class: " + value.length + " bytes");

        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}