import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class Day10{
  private static HashMap<Integer, Long> cache = new HashMap<>();

  public static List<Integer> getData(String filename)throws IOException{
    List<String> lines = Files.readAllLines(Paths.get(filename));
    List<Integer> input = new ArrayList<>();
    for(String line : lines){
      input.add(Integer.parseInt(line));
    }
    return input;
  }

  public static int part1(String filename)throws IOException{
    List<Integer> input = getData(filename);
    Collections.sort(input);
    input.add(input.get(input.size()-1) + 3);
    List<Integer> diff = new ArrayList<>();
    int previous = 0;
    for(int i = 0; i < input.size(); i++){
      diff.add(input.get(i) - previous);
      previous = input.get(i);
    }
    int numberOfOnes = 0;
    int numberOfThrees = 0;
    for(int num : diff){
      if(num == 1){
        numberOfOnes ++;
      }else if(num == 3){
        numberOfThrees ++;
      }
    }
    return numberOfOnes * numberOfThrees;
  }

  public static Long part2(String filename)throws IOException{
    List<Integer> input = getData(filename);
    Collections.sort(input);

    List<Integer> diff = new ArrayList<>();
    diff.add(0);
    int previous = 0;
    for(int i = 0; i < input.size(); i++){
      diff.add(input.get(i) - previous);
      previous = input.get(i);
    }
    diff.add(3);

    Long[] cache = new Long[diff.size()];
    cache[diff.size()-1] = 1l;
    cache[diff.size()-2] = 1l;
    for(int i = diff.size()-3; i >= 0; i--){
      int compatibleAdapters = -1;
      int diffSum = 0;
      int next = i + 1;
      while(diffSum <= 3 && next < diff.size()){
        compatibleAdapters ++;
        diffSum += diff.get(next);
        next ++;
      }

      if(compatibleAdapters == 1){
        cache[i] = cache[i + 1];
      }else if(compatibleAdapters == 2){
        cache[i] = cache[i + 1] + cache[i + 2];
      }else if (compatibleAdapters == 3){
        cache[i] = cache[i + 1] + cache[i + 2] + cache[i + 3];
      }
    }
    return cache[0];
  }

  public static void main(String[] args)throws IOException {
    System.out.println(part1("data.txt"));
    System.out.println(part2("data.txt"));
  }
}
