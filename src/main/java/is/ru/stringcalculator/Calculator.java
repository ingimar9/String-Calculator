package is.ru.stringcalculator;
import java.util.Vector;
import java.util.regex.Pattern;

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

	private static String[] splitNumbers(String numbers) {
		String delimiter = "";
		int numberOfOpeningBrackets = numbers.length() - numbers.replace("[","").length(); 
		int indexOfOpeningBracket = 0;
		int indexOfClosingBracket = 0;
		
		if (numbers.matches("//[^0-9]\n.*")) {
			delimiter = numbers.substring(2, 3);
			numbers = numbers.substring(4);
			return numbers.split("[,\n" + delimiter + "]");
		}
		else if(numberOfOpeningBrackets == 1){
			delimiter = numbers.substring(3, numbers.indexOf("\n") - 1);
			numbers = numbers.substring(numbers.indexOf("\n") + 1);
			return numbers.split("[" + delimiter.substring(0, 1) + "]{" + delimiter.length() + "}");
		}
		else if(numberOfOpeningBrackets >= 2){
			
			for(int i = numberOfOpeningBrackets; i > 0; i--){
				indexOfOpeningBracket = numbers.indexOf("[");
				indexOfClosingBracket = numbers.indexOf("]");
				delimiter += numbers.substring(indexOfOpeningBracket + 1, indexOfClosingBracket);
				numbers = numbers.substring(indexOfClosingBracket + 1);
			}
			numbers = numbers.substring(numbers.indexOf("\n") + 1);
			return numbers.split("[" + delimiter + "]");
		}	
		return numbers.split("[,\n]");
	}

	private static int sum(String[] numbers){
    	int total = 0;
	int currNumber = 0;
    	for(String number : numbers){
		currNumber = toInt(number);
		if(currNumber <= 1000){
        		total += currNumber;
    		}
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
