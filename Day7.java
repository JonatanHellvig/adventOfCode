import java.util.*;
import java.io.IOException;
import java.io.File;

public class Day7{

  public static HashMap<String, String[]> getData(String filename)throws IOException{
    Scanner s = new Scanner(new File(filename));
    HashMap<String, String[]> rules = new HashMap<>();

    while(s.hasNext()){
      String[] line = s.nextLine().split("bags?,?.?");
      for(int i = 0; i < line.length; i++){
         line[i] = line[i].replaceAll("contain", "").trim();
      }
      if(line[1].equals("no other")){
        line = Arrays.copyOfRange(line, 0, line.length-1);
      }
      rules.put(line[0], Arrays.copyOfRange(line, 1, line.length));
    }
    return rules;
  }

  public static int part1(String filename)throws IOException{
    HashMap<String, String[]> rules = getData(filename);
    for(String[] bags : rules.values()){
      for(int i = 0; i < bags.length; i++){
        bags[i] = bags[i].replaceAll(".*\\d ", "").trim();
      }
    }
    int sum = 0;
    for(String color : rules.keySet()){
      if(containBag("shiny gold", color, rules)){
        sum ++;
      }
    }
    System.out.println(sum);
    return sum;
  }

  public static boolean containBag(String target, String outerBag, HashMap<String, String[]> rules){
    if(rules.get(outerBag).length == 0){
      return false;
    }
    if(Arrays.asList(rules.get(outerBag)).contains(target)){
      return true;
    }
    boolean contains = false;
    for(String bag : rules.get(outerBag)){
      if (containBag(target, bag, rules)){
        contains = true;
      }
    }
    return contains;
  }

  public static int part2(String filename)throws IOException{
    HashMap<String, String[]> rules = getData(filename);
    int count = countBagsInside("shiny gold", rules);
    System.out.println(count);
    return count;
  }

  private static int countBagsInside(String bag, HashMap<String, String[]> rules){
    if(rules.get(bag).length == 0){
      return 0;
    }
    int sum = 0;
    for(String color : rules.get(bag)){
      int quantity = Character.getNumericValue(color.charAt(0));
      sum += quantity + quantity * countBagsInside(color.replaceAll("\\d ", ""), rules);
    }
    return sum;
  }

  public static void main(String[] args)throws IOException {
    part1("data.txt");
    part2("data.txt");
  }
}
