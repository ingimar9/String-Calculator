package is.ru.stringcalculator;
import java.util.Vector;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",") || text.contains("\n")){
    			return sum(splitNumbers(text));
		}
		else{
            		return 1;
		}
	}
	private static int toInt(String number){
    		return Integer.parseInt(number);
	}		

	 private static String[] splitNumbers(String numbers){
    	   String delimiter = "";
    	   if(numbers.matches("//[^0-9]\n.*")){		   
    		   delimiter = numbers.substring(2,3);
    		   numbers = numbers.substring(4);
    	   }
    	   String regex = "[,\n" + delimiter + "]";
	   checkIfAnyNegative(numbers.split(regex));
    	   return numbers.split(regex);
	}

	private static int sum(String[] numbers){
    	int total = 0;
    	for(String number : numbers){
        	total += toInt(number);
    	}
    	return total;
	}

	private static void checkIfAnyNegative(String[] numbers){
    	Vector<Integer> NegativeNumbers = new Vector<Integer>();
    	for(String number : numbers){
		if(toInt(number) < 0)
        	NegativeNumbers.add(toInt(number));
    	}
	if(!NegativeNumbers.isEmpty()){
		throw new IllegalArgumentException("Negatives not allowed: " + NegativeNumbers.get(0)); 
	}
	}
}
