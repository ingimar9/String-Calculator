package is.ru.stringcalculator;
import java.util.Vector;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",") || text.contains("\n")){
    			String[] numbers = splitNumbers(text);
			checkIfAnyNegative(numbers);
			return sum(numbers);
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
		String message = "Negatives not allowed: ";
		for(int i = 0; i < NegativeNumbers.size() -1; i++){
			message += NegativeNumbers.get(i) + ",";
		}
		message += NegativeNumbers.get(NegativeNumbers.size() -1);
		throw new RuntimeException(message); 
	}
	}
}
