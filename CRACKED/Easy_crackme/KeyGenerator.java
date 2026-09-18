import java.util.Random;

class KeyGenerator{
    public void main(){
        String key = "KEY";
        int keyPoints = 0;

        //random cac so in duoc
        int min = 32;
        int max = 126;
        while(keyPoints < 4){
            int randomNum = min + new Random().nextInt((max - min) + 1);
            if((randomNum & 3) != 0) continue;
            char ch = (char) randomNum;
            key += ch;
            keyPoints += 1;
        }

        while (key.length() < 10) {
            int randomNum = min + new Random().nextInt((max - min) + 1);
            if((randomNum & 3) == 0) continue;
            char ch = (char) randomNum;
            key += ch;
        }

        System.out.println("Key: " + key);
    }
}