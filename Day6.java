import java.util.*;
import java.io.File;
import java.io.IOException;

public class Day6{
  public static List<String> getData(String filename)throws IOException{
    Scanner s = new Scanner(new File(filename)).useDelimiter("\\r\\n\\r\\n");

    List<String> groups = new ArrayList<>();
    while(s.hasNext()){
      groups.add(s.next());
    }
    return groups;
  }



  public static void part1(String filename)throws IOException{
    List<String> groups = getData(filename);
    List<Integer> results = new ArrayList<>();

    for(String group : groups){
      results.add((int)group.replaceAll("[^a-z]", "").chars().distinct().count());
    }
    int sum = 0;
    for(int group : results){
      sum += group;
    }
    System.out.println(sum);
  }

  public static void part2(String filename)throws IOException{
    List<String> input = getData(filename);
    List<Integer> results = new ArrayList<>();

    for(String group : input){
      String[] groupAnswers = group.split("[^a-z]+");
      results.add(findCommonAnswers(groupAnswers[0], groupAnswers));
    }
    int sum = 0;
    for(int group : results){
      sum += group;
    }

    System.out.println(sum);

  }

  private static int findCommonAnswers(String intersection, String[] array){
    if(array.length == 0){
      return intersection.length();
    }
    String newIntersection = "";

    for(char intersectionChar : intersection.toCharArray()){
      for(char nextChar : array[0].toCharArray()){
        if (intersectionChar == nextChar){
          newIntersection += intersectionChar;
        }
      }
    }
    return findCommonAnswers(newIntersection, Arrays.copyOfRange(array, 1, array.length));
  }

  public static void main(String[] args)throws IOException {
    part1("data.txt");
    part2("data.txt");
  }
}
