import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Day4{

  public static boolean isValid(String str){
    boolean hasAll = str.contains("byr") && str.contains("iyr") && str.contains("eyr") &&
           str.contains("hgt") && str.contains("hcl") && str.contains("ecl") &&
           str.contains("pid");

    boolean byr = false;
    boolean iyr = false;
    boolean eyr = false;
    boolean hgt = false;
    boolean hcl = false;
    boolean ecl = false;
    boolean pid = false;

    String[] passport = str.split(" ");



    for(String field : passport){
      String value = field.substring(4);

      if(field.startsWith("byr:")){
          byr = Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2020;

      }else if(field.startsWith("iyr:")){
        iyr = Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;


      }else if(field.startsWith("eyr:")){
        eyr = Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;

      }else if(field.startsWith("hgt:")){
        if((value.endsWith("cm") || value.endsWith("in"))){
          int height = Integer.parseInt(value.substring(0, value.length()-2));
          if(field.endsWith("cm")){
            hgt = height >= 150 && height <= 193;
          }else if(field.endsWith("in")){
            hgt = height >= 59 && height <= 76;
          }
        }

      }else if(field.startsWith("hcl:")){
        hcl = value.startsWith("#") && value.length() == 7;

      }else if(field.startsWith("ecl:")){
        if(value.equals("amb") || value.equals("blu") || value.equals("brn") ||
           value.equals("gry") || value.equals("grn") || value.equals("hzl") ||
           value.equals("oth")){
          ecl = true;
        }

      //  String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};


      }else if(field.startsWith("pid:")){
        pid = value.length() == 9;

      }else if(field.startsWith("cid")){
        if (passport.length != 8){
          hasAll = false;
        }
      }
    }




    return hasAll && byr && iyr && eyr && hgt && hcl && ecl && pid;



  }

  public static ArrayList<String> getData(String filename) throws IOException {
    Scanner s = new Scanner(new File(filename)).useDelimiter("\\r\\n\\r\\n");
    ArrayList<String> passports = new ArrayList<>();
    while(s.hasNext()){

    String passport = s.next().replace("\r\n", " ");

      passports.add(passport);
    }


    return passports;

  }

  public static void main(String[] args) throws IOException{
    ArrayList<String> p = getData("data.txt");
    int valid = 0;
    for(String str : p){
      System.out.println(str + "\n");
      String[] passport = str.split(" ");

      for(String pass : passport){
        System.out.println(pass);

      }
      if(isValid(str)){
        valid ++;
      }
    }



    System.out.println(valid);


  }
}
