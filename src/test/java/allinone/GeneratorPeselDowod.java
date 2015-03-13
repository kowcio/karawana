/*
 * 
 */
package rlhd.hd.base;

import java.util.Random;

/**
 * Generator z http://devpytania.pl/questions/18180/generator-pesel-i-nr-dowodu-osobistego-pod-java-w-celu-automatyzacji-testow
 * http://www.algorytm.org/numery-identyfikacyjne/pesel.html
 * @author p.kowalski
 *
 */
public class GeneratorPeselDowod {
	
	
    public static void main(String[] args) throws Exception {

    	//pesel test
    	int tCnt=0;
    	int fCnt=0;
    	
    	for (int i = 0 ; i < 10 ; i++) {
    		String pesel = getPesel(1);
    		System.out.println("M "+ pesel + "  Valid = "+ isValidPesel(pesel));
    		String pesel2 = getPesel(2);
    		System.out.println("F "+ pesel2 + "  Valid = "+ isValidPesel(pesel2));
    		
    		if(isValidPesel(pesel))
    				tCnt++;
    		else
    				fCnt++;
    	}
    	System.out.println("% zweryfikowanych peseli =" + (double) tCnt/fCnt);
    
    	
    	//regon test
    	for (int i = 0 ; i < 10 ; i++) {
    		String regon = getRegon();
    		System.out.println("REGON : "+ regon  );
    	}
    
    	
    	
    	
    	
    	
    	
    }
    
   
	
	public static String getRegon() {
		
	    int wagi[] = new int[] { 8, 9, 2, 3, 4, 5, 6, 7 };

	    
	    Random rn = new Random();
	    String randPart = String.valueOf(	 rn.nextInt(789)+111 );//3 cyfry
	    		
	    
	    String pesel2 =randPart + "12345" ;
	    
	    char[] regonElem = pesel2.toCharArray();
	    
	    int sumaKontrolna = 0;
	    
	    for (int i = 0; i < wagi.length; i++) {
	        sumaKontrolna += wagi[i] * Integer.parseInt(""+regonElem[i]);
	    }
	    sumaKontrolna = sumaKontrolna % 11;
	    
	    if(sumaKontrolna==10) sumaKontrolna=0;
	    
	    
	    
	    return String.valueOf(	regonElem	) + sumaKontrolna;
	}
	
    
    
    
    
    
    
    
    
    
    
	

/**
 * Generowanie peselu 	
 * liczba parzysta - kobieta, liczba nieparzysta - mężczyzna
 * @param plec - 0-9
 * @return pesel
 */
	public static String getPesel(int plec) {
		
	    int wagi[] = new int[] { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };

	    String pesel_part1 = "880303";	//6
	    
	    Random rn = new Random();
	    String randMale = String.valueOf(	 rn.nextInt(789)+111 );
	  //4	-> plecliczba parzysta - kobieta, liczba nieparzysta - mężczyzna)	    		
	    String pesel2 =pesel_part1 + randMale + plec;
	    
	    char[] peselElem = pesel2.toCharArray();
	    
	    int sumaKontrolna = 0;
	    for (int i = 0; i < wagi.length; i++) {
	        sumaKontrolna += wagi[i] * Integer.parseInt(""+peselElem[i]);
	    }
	    sumaKontrolna = sumaKontrolna % 10;
	    sumaKontrolna =10 - sumaKontrolna;
	    sumaKontrolna =sumaKontrolna % 10;
	    
	    return String.valueOf(	peselElem	) + sumaKontrolna;
	}
	

	
	 /**
     * Weryfikacja numeru PESEL
     * @param pesel - pesel
     * @return true jeśli prawidłowy
     */
    public static boolean isValidPesel(String pesel) {
        pesel = trimInput(pesel);
        int psize = pesel.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = {1,3,7,9,1,3,7,9,1,3};
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(pesel.substring(psize - 1)).intValue();
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }
    private static String trimInput(String input) {
        return input.replaceAll("\\D*","");
    }
    
    
    
	
	
	
	
	
	
}
