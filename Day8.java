import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day8{

  public static List<String[]> getData(String filename)throws FileNotFoundException{
    Scanner s  = new Scanner(new File(filename));
    List<String[]> input = new ArrayList<>();
    while(s.hasNext()){
      String line = s.nextLine();
      input.add(line.split(" "));
    }
    return input;
  }

  public static boolean runProgram(List<String[]> input)throws FileNotFoundException{
    int accumulator = 0;
    Set<Integer> visited = new HashSet<>();
    int index = 0;

    boolean finished = false;
    boolean terminated = false;
    while(!finished){
      String command = input.get(index)[0];
      int value = Integer.parseInt(input.get(index)[1]);

      switch(command){
        case "nop":
          index ++;
          break;
        case "acc":
          accumulator += value;
          index ++;
          break;
        case "jmp":
          index += value;
          break;
      }

      if(!visited.add(index)){
        finished = true;
        System.out.print("Loop found  ");
      }
      if(index == input.size()){
        finished = true;
        terminated = true;
        System.out.print("Terminated  ");
      }
    }
    System.out.println(accumulator);
    return terminated;
  }

  public static void changeCommand(List<String[]> input, int index){
    String command = input.get(index)[0];
    if(command.equals("nop")){
      input.get(index)[0] = "jmp";
    }else if(command.equals("jmp")){
      input.get(index)[0] = "nop";
    }
  }

  public static void fixProgram(String filename)throws FileNotFoundException{
    List<String[]> input = getData(filename);

    for(int i = 0; i < input.size(); i++){
      String command = input.get(i)[0];
      if(command.matches("nop|jmp")){
        changeCommand(input, i);
        if(runProgram(input)){
          break;
        }else{
          changeCommand(input, i);
        }
      }

    }
  }

  public static void main(String[] args)throws FileNotFoundException {
    fixProgram("data.txt");
  }
}
