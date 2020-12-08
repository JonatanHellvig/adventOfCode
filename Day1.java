import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Day1{

  public ArrayList<Integer> makeListFromFile(String filename)throws IOException{
    ArrayList<Integer> numbers = new ArrayList<>();
    Scanner s = new Scanner(new File("data.txt"));
    while(s.hasNext()){
      numbers.add(s.nextInt());
    }

    return numbers;
  }

  public int findProductOfSum2020(ArrayList<Integer> numbers){
    for(int i = 0; i < numbers.size(); i ++){
      int number1 = numbers.get(i);
      for(int j = i + 1; j < numbers.size(); j ++){
        int number2 = numbers.get(j);
        for(int k = j + 1; k < numbers.size(); k ++){
          int number3 = numbers.get(k);
          if(number1 + number2 + number3 == 2020){
            return number1 * number2 * number3;
          }
        }
      }
    }
    return -1;
  }


  public static void main(String[] args)throws IOException {
    Day1 d = new Day1();
    int answer = d.findProductOfSum2020(d.makeListFromFile("data.txt"));
    System.out.println(answer);
  }

}
