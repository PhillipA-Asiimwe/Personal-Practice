import java.util.ArrayList;

public class Challenge {
    static String status ;
    static int oddsum =0 ;
	static int evensum =0;	
	 private static ArrayList digits(int num){
        boolean flag = true; 
        int chk = num;
        int one ;
        ArrayList send = new ArrayList<Integer>() ;
        while(flag){
            if (chk == 0 ){
                flag = false; 
            }else{
                one = chk %10; 
                chk = chk / 10; 
                send.add(one);
            }
        }
        return send;
    }
    public static void calc(int n){
        if(n % 2 == 0 ){
            evensum += n ; 				
        }else {
            oddsum += n;
        }   

    }
	public static String oddsVsEvens(int num) {	
		ArrayList numbrs = digits(num);	
		numbrs.forEach(n-> calc((int) n));
        if (evensum>oddsum){
            status = "even";
            
        }else if ( evensum < oddsum){
            status = "odd";
            
        }else{
            status = "equal";
        }
        return status; 

			
	}
}