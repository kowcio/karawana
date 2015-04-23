package allinone;

import java.util.Scanner;

public class Spoj {
    
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(System.in);
            String line ;
            while(  s.hasNext() ){
                if(! (s.nextInt()!=42)){
           //         System.out.println(line);
                }else{
                    break;
                }
            }
        
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }

}
