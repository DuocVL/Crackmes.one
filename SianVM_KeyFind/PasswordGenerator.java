import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PasswordGenerator {

    public static void main(String[] args) {
        // Mảng byte định nghĩa trong khối static của lớp L
        byte[] bArr = {90, 60, -89, 17, -29, 119, -103, 15, 66, -79, 109, -120, 42};
        
        // Mảng XOR tạo nguồn cho H_
        byte[] encodedBytes = new byte[]{
            (byte) (108 ^ bArr[0]), (byte) (111 ^ bArr[1]), (byte) (108 ^ bArr[2]), 
            (byte) (119 ^ bArr[3]), (byte) (45 ^ bArr[4]), (byte) (115 ^ bArr[5]), 
            (byte) (105 ^ bArr[6]), (byte) (97 ^ bArr[7]), (byte) (110 ^ bArr[8]), 
            (byte) (45 ^ bArr[9]), (byte) (106 ^ bArr[10]), (byte) (97 ^ bArr[11]), 
            (byte) (114 ^ bArr[12])
        };

        // Hàm J thực hiện phép XOR giải mã nguyên bản
        byte[] decodedBytes = J(encodedBytes, bArr);
        String targetString = new String(decodedBytes, StandardCharsets.UTF_8);

        System.out.println("Chuỗi cần tìm: " + targetString);
        System.out.println("Kết quả L.XD(\"" + targetString + "\"): " + L.XD(targetString));
    }

    private static byte[] J(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[Math.min(bArr.length, bArr2.length)];
        for (int i = 0; i < bArr3.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    // --- CLASS A ---
    public static final class A {
        private A() {}

        public static byte[] exec(byte[] bArr, byte[] bArr2) {
            int i = 12 + (bArr.length > 0 ? bArr[0] & 15 : 0);
            int length = 48 + (bArr.length % 29);
            byte[] bArr3 = new byte[length];
            byte[] bArr4 = new byte[32];
            int length2 = -1;
            int iRotateLeft = 0;
            int i2 = 0;
            long j = 2882400001L;
            for (int i3 = 0; i3 < length; i3++) {
                j = ((j * 6364136223846793005L) + 1442695040888963407L) ^ ((long) ((bArr[i3 % bArr.length] & 255) + i3));
                bArr3[i3] = (byte) ((j >>> (i3 % 8)) & 255);
            }
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = (bArr[((i4 * 13) + 7) % bArr.length] & 255) % 12;
                int i6 = bArr[((i4 * 5) + 11) % bArr.length] & 255;
                switch (i5) {
                    case 0:
                        length2++;
                        if (length2 >= bArr4.length) {
                            length2 = bArr4.length - 1;
                        }
                        bArr4[length2] = bArr3[i6 % bArr3.length];
                        break;
                    case 1:
                        if (length2 >= 0) {
                            int length3 = i6 % bArr3.length;
                            bArr3[length3] = (byte) (bArr3[length3] ^ bArr4[length2]);
                            length2--;
                        }
                        break;
                    case 2:
                        if (length2 >= 0) {
                            bArr4[length2] = (byte) ((bArr4[length2] + bArr3[i6 % bArr3.length]) & 255);
                        }
                        break;
                    case 3:
                        iRotateLeft = Integer.rotateLeft(iRotateLeft ^ i6, (i6 % 7) + 1);
                        break;
                    case 4:
                        if ((iRotateLeft & 1) == 1) {
                            int i7 = iRotateLeft;
                            iRotateLeft = i2;
                            i2 = i7;
                        }
                        break;
                    case 5:
                        bArr3[i6 % bArr3.length] = bArr2[(bArr3[i6 % bArr3.length] & 255) % bArr2.length];
                        break;
                    case 6:
                        xorBlock(bArr3, i6 % bArr3.length, Math.max(1, i6 % 7));
                        break;
                    case 7:
                        rotateRight(bArr3, i6 % bArr3.length, Math.max(1, (i6 % (bArr3.length / 2)) + 1));
                        break;
                    case 8:
                        length2++;
                        if (length2 >= bArr4.length) {
                            length2 = bArr4.length - 1;
                        }
                        bArr4[length2] = (byte) (iRotateLeft & 255);
                        break;
                    case 9:
                        if (length2 >= 0) {
                            bArr4[length2] = (byte) ((bArr4[length2] ^ (i2 & 255)) & 255);
                        }
                        break;
                    case 10:
                        scramble(bArr3, i6 % bArr3.length, Math.min(8, bArr3.length - (i6 % bArr3.length)), bArr2);
                        break;
                    case 11:
                        if (length2 >= 0) {
                            int length4 = (i6 ^ (bArr4[length2] & 255)) % bArr3.length;
                            bArr3[length4] = (byte) ((bArr3[length4] + bArr4[length2]) & 255);
                            length2--;
                        }
                        break;
                }
                i2 = (i2 + (i4 * 31) + (bArr3[i4 % bArr3.length] & 255)) & (-1);
            }
            int length5 = 17 + (bArr.length % 11);
            byte[] bArr5 = new byte[length5];
            for (int i8 = 0; i8 < length5; i8++) {
                bArr5[i8] = (byte) (((bArr3[(((((i8 * 13) ^ (iRotateLeft + i2)) ^ (bArr[i8 % bArr.length] & 255)) % bArr3.length) + bArr3.length) % bArr3.length] ^ ((iRotateLeft >>> (i8 % 8)) & 255)) ^ (i2 & 255)) & 255);
            }
            for (int i9 = 0; i9 < bArr5.length; i9++) {
                bArr5[i9] = (byte) ((bArr5[i9] + ((bArr5[(i9 + 3) % bArr5.length] & 255) ^ (i9 * 7))) & 255);
            }
            return bArr5;
        }

        private static void xorBlock(byte[] bArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                int length = (i + i3) % bArr.length;
                bArr[length] = (byte) (bArr[length] ^ ((byte) ((i * (i3 + 3)) & 255)));
            }
        }

        private static void rotateRight(byte[] bArr, int i, int i2) {
            if (i2 <= 1) {
                return;
            }
            int length = bArr.length;
            int i3 = i % length;
            byte b = bArr[((i3 + i2) - 1) % length];
            for (int i4 = i2 - 1; i4 > 0; i4--) {
                bArr[(i3 + i4) % length] = bArr[((i3 + i4) - 1) % length];
            }
            bArr[i3 % length] = b;
        }

        private static void scramble(byte[] bArr, int i, int i2, byte[] bArr2) {
            for (int i3 = 0; i3 < i2; i3++) {
                int length = (i + i3) % bArr.length;
                bArr[length] = bArr2[(bArr[length] & 255) % bArr2.length];
                bArr[length] = (byte) ((bArr[length] ^ (i3 * 17)) & 255);
            }
        }
    }

    // --- CLASS L ---
    public static final class L {
        private static final byte[] H_;
        private static final byte[][] B;
        private static final byte[] C = new byte[256];

        static {
            int i = 7967;
            for (int i2 = 0; i2 < 256; i2++) {
                i = ((i * 1103515245) + 12345) ^ (i2 * 31);
                C[i2] = (byte) ((i >>> (i2 % 8)) & 255);
            }
            B = new byte[][]{F(new byte[]{17, 34, 51, 68}), F(new byte[]{-86, -69, -52}), F(new byte[]{1, 2, 3, 4, 5})};
            byte[] bArr = {90, 60, -89, 17, -29, 119, -103, 15, 66, -79, 109, -120, 42};
            H_ = K(J(new byte[]{(byte) (108 ^ bArr[0]), (byte) (111 ^ bArr[1]), (byte) (108 ^ bArr[2]), (byte) (119 ^ bArr[3]), (byte) (45 ^ bArr[4]), (byte) (115 ^ bArr[5]), (byte) (105 ^ bArr[6]), (byte) (97 ^ bArr[7]), (byte) (110 ^ bArr[8]), (byte) (45 ^ bArr[9]), (byte) (106 ^ bArr[10]), (byte) (97 ^ bArr[11]), (byte) (114 ^ bArr[12])}, bArr));
            for (int i3 = 0; i3 < B.length; i3++) {
                B[i3] = M(B[i3], (byte) (95 + i3));
            }
        }

        private L() {
        }

        public static boolean XD(String str) {
            if (str == null) {
                return false;
            }
            byte[] bArrExec = A.exec(Y(W(U(S(Q(str.getBytes(StandardCharsets.UTF_8)))))), C);
            for (byte[] bArr : B) {
                if (Arrays.equals(bArrExec, bArr)) {
                    return false;
                }
            }
            return DD(CC(bArrExec), H_);
        }

        private static byte[] Q(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length + 3];
            int i = 2499;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                int i3 = (((((bArr[i2] & 255) * 59) + 101) + (i & 255)) & 255) ^ ((i2 * 17) & 255);
                int length = ((i2 * 2) + 3) % bArr2.length;
                bArr2[length] = (byte) (bArr2[length] ^ ((byte) i3));
                i = ((i << 3) ^ i3) & 65535;
            }
            LL(bArr2, 3);
            return bArr2;
        }

        private static byte[] S(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            int i = 51966;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                i = ((i * 1664525) + 1013904223) ^ (i2 * 7);
                bArr2[i2] = (byte) (C[((bArr[i2] & 255) ^ ((i >>> (i2 % 11)) & 255)) & 255] & 255);
            }
            return bArr2;
        }

        private static byte[] U(byte[] bArr) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            for (int i = 0; i < 3; i++) {
                for (int i2 = 0; i2 < bArrCopyOf.length - 1; i2++) {
                    int length = ((i2 * 31) + i) % bArrCopyOf.length;
                    byte b = bArrCopyOf[i2];
                    bArrCopyOf[i2] = (byte) ((((bArrCopyOf[length] & 255) ^ (b & 255)) ^ (i * 13)) & 255);
                    bArrCopyOf[length] = (byte) (((b & 255) + (bArrCopyOf[length] & 255) + i) & 255);
                }
                YY(bArrCopyOf);
            }
            return bArrCopyOf;
        }

        private static byte[] W(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            long j = 324508639;
            for (int i = 0; i < bArr.length; i++) {
                j = ((j * 6364136223846793005L) + 1442695040888963407L) & (-1);
                bArr2[i] = (byte) (((Integer.rotateLeft(bArr[i] & 255, (i % 7) + 1) & 255) ^ ((int) ((j >>> (i % 32)) & 255))) & 255);
            }
            return bArr2;
        }

        private static byte[] Y(byte[] bArr) {
            int iMax = Math.max(4, bArr.length / 3);
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                int i2 = 0;
                for (int i3 = 0; i3 < iMax; i3++) {
                    i2 ^= (bArr[(i + i3) % bArr.length] & 255) << (i3 % 5);
                }
                bArr2[i] = (byte) ((i2 ^ (i * 37)) & 255);
            }
            LLL(bArr2);
            return bArr2;
        }

        private static byte[] CC(byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) ((C[((bArr[i] & 255) + 158) & 255] ^ (i * 31)) & 255);
            }
            if (bArr2.length > 5) {
                for (int i2 = 0; i2 < bArr2.length / 3; i2++) {
                    int iFloorMod = Math.floorMod((i2 * 7) + (bArr2[i2] & 255), bArr2.length);
                    byte b = bArr2[i2];
                    bArr2[i2] = bArr2[iFloorMod];
                    bArr2[iFloorMod] = b;
                }
            }
            return bArr2;
        }

        private static byte[] K(byte[] bArr) {
            return CC(A.exec(Y(W(U(S(Q(bArr))))), C));
        }

        private static byte[] J(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[Math.min(bArr.length, bArr2.length)];
            for (int i = 0; i < bArr3.length; i++) {
                bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            }
            return bArr3;
        }

        private static byte[] F(byte[] bArr) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            for (int i = 0; i < bArrCopyOf.length; i++) {
                bArrCopyOf[i] = (byte) ((bArrCopyOf[i] ^ 90) + (i * 3));
            }
            return bArrCopyOf;
        }

        private static byte[] M(byte[] bArr, byte b) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            for (int i = 0; i < bArrCopyOf.length; i++) {
                bArrCopyOf[i] = (byte) ((bArrCopyOf[i] + b) ^ (i * 11));
            }
            return bArrCopyOf;
        }

        private static void LL(byte[] arr, int i) {
            if (arr.length < i || i <= 0) {
                return;
            }
            for (int i2 = 0; i2 < i; i2++) {
                byte b = arr[i2];
                int i3 = i2;
                while (true) {
                    int i4 = i3;
                    if (i4 < arr.length - i) {
                        arr[i4] = arr[i4 + i];
                        i3 = i4 + i;
                    }
                }
                //arr[(arr.length - i) + i2] = b;
            }
        }

        private static void YY(byte[] bArr) {
            for (int i = 0; i < bArr.length / 2; i++) {
                byte b = bArr[i];
                bArr[i] = bArr[(bArr.length - 1) - i];
                bArr[(bArr.length - 1) - i] = b;
            }
        }

        private static void LLL(byte[] bArr) {
            for (int i = 0; i < bArr.length / 2; i++) {
                int i2 = i;
                bArr[i2] = (byte) (bArr[i2] ^ bArr[(bArr.length - 1) - i]);
                bArr[(bArr.length - 1) - i] = (byte) ((bArr[(bArr.length - 1) - i] + (i * 13)) & 255);
            }
        }

        private static boolean DD(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return false;
            }
            int i = 0;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                i |= bArr[i2] ^ bArr2[i2];
            }
            return i == 0;
        }
    }
}