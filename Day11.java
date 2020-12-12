import java.nio.file.*;
import java.util.*;
import java.io.IOException;
import java.util.stream.*;

public class Day11{
  public static List<char[]> getData(String filename)throws IOException{
    List<char[]> input = Files.readAllLines(Paths.get(filename)).stream()
                                .map(l -> l.toCharArray())
                                .collect(Collectors.toList());
    return input;
  }

  private static boolean willChangeRule1(List<char[]> input, int row, int col){
    int adjacent = 0;
    for(int i = row - 1; i <= row + 1; i++){
      for(int j = col - 1; j <= col + 1; j++){
        if(i >= 0 && i < input.size() && j >= 0 && j < input.get(0).length){
          if (input.get(i)[j] == '#'){
            adjacent ++;
          }
        }
      }
    }

    if(input.get(row)[col] == 'L' && adjacent == 0){
      return true;

    }else if(input.get(row)[col] == '#' && adjacent > 4){
      return true;
    }
    return false;
  }

  public static boolean willChangeRule2(List<char[]> input, int row, int col){
    int occupied = 0;

    for(int i = -1; i <= 1; i++){
      for(int j = -1; j <= 1; j++){
        int distance = 1;
        boolean stillLooking = true;
        while(stillLooking){
          if(row-i*distance < 0 || row-i*distance >= input.size() ||
            col-j*distance < 0 || col-j*distance >= input.get(0).length){
            break;
          }
          if(i == 0 && j == 0){
            break;
          }
          char seenSeet = input.get(row-i*distance)[col-j*distance];
          if(seenSeet == '#'){
            stillLooking = false;
            occupied ++;
          }else if(seenSeet == 'L'){
            stillLooking = false;
          }
          distance ++;
        }
      }
    }
    if(input.get(row)[col] == 'L' && occupied == 0){
      return true;
    }else if(input.get(row)[col] == '#' && occupied > 4){
      return true;
    }
    return false;

  }

  public static int day11(int part, String filename)throws IOException{
    List<char[]> input = getData(filename);
    List<int[]> seatsToChange = new ArrayList<>();
    seatsToChange.add(new int[]{0,0});
    int occupied = 0;

    while(seatsToChange.size() > 0){
      seatsToChange.removeAll(seatsToChange);
      for(int row = 0; row < input.size(); row++){
        for(int col = 0; col < input.get(0).length; col++){

          if(part == 1){
            if(willChangeRule1(input, row, col)){
              seatsToChange.add(new int[]{row, col});
            }
          }else if(part == 2){
            if(willChangeRule2(input, row, col)){
              seatsToChange.add(new int[]{row, col});
            }
          }else{
            System.out.println("Must be part 1 or part 2");
            return -1;
          }

        }
      }
      for(int[] seat : seatsToChange){
        if(input.get(seat[0])[seat[1]] == 'L'){
          char[] newRow = input.get(seat[0]);
          newRow[seat[1]] = '#';
          input.set(seat[0], newRow);
          occupied ++;
        }else if(input.get(seat[0])[seat[1]] == '#'){
          char[] newRow = input.get(seat[0]);
          newRow[seat[1]] = 'L';
          input.set(seat[0], newRow);
          occupied --;
        }
      }
    }
    return occupied;
  }

  public static void main(String[] args)throws IOException {
    System.out.println(day11(1, "data.txt"));
    System.out.println(day11(2, "data.txt"));
  }

}
