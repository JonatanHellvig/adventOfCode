import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Day5{

  public static ArrayList<String[]> getData(String filename)throws IOException{
    ArrayList<String[]> seats = new ArrayList<>();
    Scanner s = new Scanner(new File(filename));

    while(s.hasNext()){
      String line = s.nextLine();
      String[] seat = {line.substring(0,7), line.substring(7)};
      seats.add(seat);
    }

    return seats;
  }

  public static int getSeatID(String[] seat){
    seat[0] = seat[0].replace('F', '0').replace('B', '1');
    seat[1] = seat[1].replace('L', '0').replace('R', '1');

    int seatID = 8 * Integer.parseInt(seat[0], 2) + Integer.parseInt(seat[1], 2);
    return seatID;
  }

  public static void day5()throws IOException{
    ArrayList<String[]> seats = getData("data.txt");
    List<Integer> seatIDs= new ArrayList<>();

    for(String[] seat : seats){
      int seatID = getSeatID(seat);
      seatIDs.add(seatID);
    }
    Collections.sort(seatIDs);
  

    int mySeatID = 0;
    for(int i = 0; i < seatIDs.size()-1;  i++){
      if(seatIDs.get(i + 1) - seatIDs.get(i) == 2){
        mySeatID = seatIDs.get(i) + 1;
        break;
      };
    }
    System.out.println(Collections.max(seatIDs) + " " + mySeatID);
  }


  public static void main(String[] args)throws IOException {
    day5();
  }
}
