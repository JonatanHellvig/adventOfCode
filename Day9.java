import java.io.IOException;
import java.nio.file.*;
import java.util.*;



public class Day9{

  public static List<Long> getData(String filename)throws IOException{
    List<String> lines = Files.readAllLines(Paths.get(filename));
    List<Long> input = new ArrayList<>();
    for(String number : lines){
      input.add(Long.parseLong(number));
    }
    return input;
  }

  public static boolean sumTo(Long num, List<Long> list){
    for(int i = 0; i < list.size()-1; i++){
      for(int j = i+1; j < list.size(); j++){
        if(list.get(i) + list.get(j) == num){
          return true;
        }
      }
    }
    return false;
  }

  public static Long part1(String filename)throws IOException{
    List<Long> input = getData(filename);
    for(int i = 25; i < input.size(); i++){
      if(!sumTo(input.get(i), input.subList(i-25, i))){
        System.out.println(input.get(i));
        return input.get(i);
      }
    }
    return -1l;
  }

  public static Long part2(String filename)throws IOException{
    List<Long> input = getData(filename);
    Long num = part1(filename);
    List<Long> shortInput = input.subList(0, input.indexOf(num));
    for(int i = 0; i < shortInput.size()-1; i ++){
      for(int j = i + 2; j < shortInput.size(); j++){
        List<Long> list = input.subList(i, j);
        Long sum = list.stream().mapToLong(l -> l).sum();
        if (sum.equals(num)){
          Long min = list.stream().mapToLong(l -> l).min().getAsLong();
          Long max = list.stream().mapToLong(l -> l).max().getAsLong();
          return min + max;
        }
      }
    }
    return -1l;
  }

  public static void main(String[] args)throws IOException {
    System.out.println(part2("data.txt"));
  }
}
