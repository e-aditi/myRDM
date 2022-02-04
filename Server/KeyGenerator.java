package Server;
import java.util.Random;

// Generating a 10 character alphanumeric secret key.
class KeyGenerator {
  static String keyGenerate() {
    Random random = new Random();
    String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz"; 
    String numbers = "0123456789"; 
    String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers; 
    StringBuffer randomString = new StringBuffer(); 
    for (int i = 0; i < 10; i++) {
      int randomIndex = random.nextInt(allCharacters.length()); 
      randomString.append(allCharacters.charAt(randomIndex)); 
    }
    return randomString.toString();
  }
}