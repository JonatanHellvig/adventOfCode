import java.nio.file.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.*;
import java.lang.Math;

public class Day12{

  public static int part1(String filename)throws IOException{
    List<String> input = Files.readAllLines(Paths.get(filename));
    int north = 0;
    int east = 0;
    int direction = 1;

    for(String instruction : input){
      char command = instruction.charAt(0);
      int value = Integer.parseInt(instruction.substring(1));

      switch(command){
        case 'N':
          north += value;
          break;
        case 'E':
          east += value;
          break;
        case 'S':
          north -= value;
          break;
        case 'W':
          east -= value;
          break;
        case 'F':
          if(direction == 0){
            north += value;
          }else if(direction == 1 || direction == -3){
            east += value;
          }else if(direction == 2 || direction == -2){
            north -= value;
          }else if(direction == 3 || direction == -1){
            east -= value;
          }
          break;
        case 'R':
          direction = (direction + 1*value/90)%4;
          break;
        case 'L':
          direction = (direction - 1*value/90)%4;
          break;
      }
    }
    System.out.println(east + " " + north);
    return Math.abs(north) + Math.abs(east);
  }

  public static int part2(String filename)throws IOException{
    List<String> input = Files.readAllLines(Paths.get(filename));
    int[] waypoint = new int[]{10, 1};
    int[] ship = new int[]{0,0};

    for(String instruction : input){
      char command = instruction.charAt(0);
      int value = Integer.parseInt(instruction.substring(1));

      switch(command){
        case 'N':
          waypoint[1] += value;
          break;
        case 'E':
          waypoint[0] += value;
          break;
        case 'S':
          waypoint[1] -= value;
          break;
        case 'W':
          waypoint[0] -= value;
          break;
        case 'F':
          ship[0] += value * waypoint[0];
          ship[1] += value * waypoint[1];
          break;
        case 'R':
          for(int i = 0; i < value/90; i++){
            int temp = waypoint[0];
            waypoint[0] = waypoint[1];
            waypoint[1] = -1 * temp;
          }
          break;
        case 'L':
          for(int i = 0; i < value/90; i++){
            int temp = waypoint[0];
            waypoint[0] = -1 * waypoint[1];
            waypoint[1] = temp;
          }
          break;
      }
    }
    System.out.println(ship[0] + " " + ship[1]);
    return Math.abs(ship[0]) + Math.abs(ship[1]);
  }

  public static void main(String[] args)throws IOException {
    System.out.println(part1("data.txt"));
    System.out.println(part2("data.txt"));
  }
}
