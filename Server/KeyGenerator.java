package Server;
import java.util.Random;


class KeyGenerator {
  static String keyGenerate() {
    Random random = new Random();
    String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz"; 
    String numbers = "0123456789"; 
    // create a super set of all characters 
    String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers; 
    // initialize a string to hold result 
    StringBuffer randomString = new StringBuffer(); 
    // loop for 10 times 
    for (int i = 0; i < 10; i++) { 
      // generate a random number between 0 and length of all characters 
      int randomIndex = random.nextInt(allCharacters.length()); 
      // retrieve character at index and add it to result 
      randomString.append(allCharacters.charAt(randomIndex)); 
    }
    return randomString.toString();
  }
}
