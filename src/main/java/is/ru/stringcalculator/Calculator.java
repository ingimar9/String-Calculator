package is.ru.stringcalculator;
import java.util.Vector;
import java.util.regex.Pattern;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",") || text.contains("\n")){
			checkIfAnyNegative(text);
			return sum(splitNumbers(text));
		}
		else{
            		return toInt(text);
		}
	}
	private static int toInt(String number){
    		return Integer.parseInt(number);
	}

	private static int numberOfOccurrences(String text, String character){
		return text.length() - text.replace(character, "").length();
	}		

	private static String[] splitNumbers(String numbers) {
		String allDelimiters = "";
		int numberOfOpeningBrackets = numberOfOccurrences(numbers, "[");
		int indexOfOpeningBracket = 0;
		int indexOfClosingBracket = 0;
		
		if (numbers.matches("//[^0-9]\n.*")) {
			allDelimiters = numbers.substring(2, 3);
			numbers = numbers.substring(4);
			return numbers.split("[,\n" + allDelimiters + "]");
		}
		else if(numberOfOpeningBrackets > 0){
			for(int i = numberOfOpeningBrackets; i > 0; i--){
				String findDelimeter = "";
				indexOfOpeningBracket = numbers.indexOf("[");
				indexOfClosingBracket = numbers.indexOf("]");
				findDelimeter += numbers.substring(indexOfOpeningBracket + 1, indexOfClosingBracket);
				allDelimiters += "[" + findDelimeter.substring(0,1) + "]{" + findDelimeter.length() + "}";
				if(i > 1){
					allDelimiters += "|";
				}
				numbers = numbers.substring(indexOfClosingBracket + 1);
			}
			numbers = numbers.substring(numbers.indexOf("\n") + 1);
			return numbers.split(allDelimiters);
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

	private static void checkIfAnyNegative(String text){
	String[] numbers = splitNumbers(text);
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
