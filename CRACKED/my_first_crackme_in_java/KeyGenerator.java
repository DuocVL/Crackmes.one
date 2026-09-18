import java.util.ArrayList;
import java.util.Scanner;

class PermutationGenerator {
    private String word;

    public PermutationGenerator(String aWord) {
        this.word = aWord;
    }

    public ArrayList<String> getPermutations() {
        ArrayList<String> result = new ArrayList<>();
        if (this.word.length() == 0) {
            result.add(this.word);
            return result;
        }
        for (int i = 0; i < this.word.length(); i++) {
            String shorterWord = this.word.substring(0, i) + this.word.substring(i + 1);
            PermutationGenerator shorterPermutationGenerator = new PermutationGenerator(shorterWord);
            ArrayList<String> shorterWordPermutations = shorterPermutationGenerator.getPermutations();
            for (String s : shorterWordPermutations) {
                result.add(this.word.charAt(i) + s);
            }
        }
        return result;
    }
}



class KeyGenerator {

    public String keyGen(String country, int sum){
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        int c2;
        int c3;
        int c4;
        int n1;
        int n2;
        int n3;
        int d;
        int y;
        int y1;
        int y2;
        char ch1;
        char ch2;
        char ch3;
        String s1 = country.substring(0, 5);
        PermutationGenerator pm = new PermutationGenerator(s1);
        ArrayList<String> permut = pm.getPermutations();
        s2 = permut.get(3);
        s3 = permut.get(10);
        s4 = permut.get(17);
        ch1 = s2.charAt(3);
        ch2 = s3.charAt(3);
        ch3 = s4.charAt(3);
        n1 = ch1;
        n2 = ch2;
        n3 = ch3;
        int c1 = sum << 2;
        c2 = c1 & 255;
        c3 = c1 ^ c2;
        c4 = c1 | c3;
        d = (c2 * 2) + (c3 * 3) + (c4 * 4) + (n1 * 10) + (n2 * 11) + (n3 * 12);
        s5 = String.valueOf(d);
        s6 = s5;
        s5 = s5.substring(0, 2);
        y = Integer.parseInt(s5);
        y1 = Integer.parseInt(s6);
        int[][] a = {new int[]{2, 2, y}, new int[]{4, 6, 2}, new int[]{3, 4, 4}};
        int[][] b = {new int[]{2, 2, 3}, new int[]{8, 9, 5}, new int[]{6, 2, 2}};
        int[][] c = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    c[i][j] = c[i][j] + (a[i][k] * b[k][j]);
                }
            }
        }
        y2 = c[2][2];
        y2 *= y1;

        return String.valueOf(y2);
    }

    public void main() {
        System.out.println("Chương trình sinh key từ name và country!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập name:");
        String name = scanner.nextLine();
        
        
        System.out.print("Nhập country Australia, Brazil, Egypt, Germany, India, Mexico, Other:");
        String country = scanner.nextLine();
        int sum = 0;
        for  (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i))) {
                int m = name.charAt(i);
                sum += m;
            }
        }
        String key = keyGen(country, sum);

        System.out.println("Name:" + name);
        System.out.println("Country:" + country);
        System.out.println("Key:" + key);
    }
}