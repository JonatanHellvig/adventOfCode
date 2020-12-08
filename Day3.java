import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Day3{

  public static ArrayList<char[]> getData(String filename)throws IOException{
    ArrayList<char[]> al = new ArrayList<>();
    Scanner s = new Scanner(new File(filename));

    while(s.hasNext()){
      al.add(s.nextLine().toCharArray());
    }

    s.close();

    return al;
  }

  public static int day3(ArrayList<char[]> al, int right, int down){
    int trees = 0;
    int index = 0;
    int columns = al.get(0).length;

    for(int i = 0; i < al.size(); i += down){

      if(al.get(i)[index] == '#'){
        trees ++;
      }

      index = (index + right) % columns;

    }

    return trees;
  }

  public static int part2(ArrayList<char[]> al){
    int slope1 = day3(al, 1, 1);
    int slope2 = day3(al, 3, 1);
    int slope3 = day3(al, 5, 1);
    int slope4 = day3(al, 7, 1);
    int slope5 = day3(al, 1, 2);

    return slope1 * slope2 * slope3 * slope4 * slope5;
  }

  public static void main(String[] args)throws IOException {

    System.out.println(day3(getData("data.txt"), 3, 1));
    System.out.println(part2(getData("data.txt")));
  }
}
