import java.util.Scanner;
import java.io.IOException;
import java.io.File;


public class Day2{

  public void checkPasswords(){
    File data = new File("data.txt");
    int validPart1 = 0;
    int validPart2 = 0;

    try{
      Scanner s = new Scanner(data);
      while(s.hasNext()){
        String line = s.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("-| |: ");
        int firstInt = lineScanner.nextInt();
        int secondInt = lineScanner.nextInt();
        char key = lineScanner.next().charAt(0);
        String password = lineScanner.next();

        int amountOfKey = 0;
        for(int i = 0; i < password.length(); i ++){
          if(password.charAt(i) == key){
            amountOfKey ++;
          }
        }
        if(amountOfKey >= firstInt && amountOfKey <= secondInt){
          validPart1 ++;
        }
        if(password.charAt(firstInt - 1) == key ^ password.charAt(secondInt - 1) == key){
          validPart2 ++;
        }
        lineScanner.close();
      }
      s.close();
    }catch(IOException ioe){
      System.out.println(ioe.getMessage());
    }

    System.out.println(validPart1);
    System.out.println(validPart2);
  }


  public static void main(String[] args) {
    Day2 d = new Day2();
    d.checkPasswords();

  }

}
