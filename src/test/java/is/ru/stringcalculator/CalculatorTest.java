package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      		org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    	}
	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}
	@Test
	public void testOneNumber(){
    		assertEquals(1, Calculator.add("1"));
	}
	@Test
	public void testAnotherOneNumber(){
    		assertEquals(3, Calculator.add("3"));
	}
	@Test
	public void testTwoNumbers(){
    		assertEquals(3, Calculator.add("1,2"));
	}
	@Test
	public void testMultipleNumbers(){
	    assertEquals(6, Calculator.add("1,2,3"));
	}	
	@Test
	public void testMultipleNumbersWithNewLineAndComma(){
	    assertEquals(6, Calculator.add("1\n2,3"));
	}
	@Test
	public void testMultipleNumbersWithOnlyNewLines(){
	    assertEquals(7, Calculator.add("1\n3\n3"));
	}
	@Test
	public void testWithDifferentDelimeter(){
	    assertEquals(3, Calculator.add("//;\n1;2"));
	}
	@Test
	public void testWithNegativeNumbers(){
		try{
			Calculator.add("-1,2");
		}catch(RuntimeException ex){
		assertEquals("Negatives not allowed: -1", ex.getMessage());
		}
	}
	@Test
	public void testWitTwoNegativeNumbers(){
		try{
			Calculator.add("2,-4,3,-5");
		}catch(RuntimeException ex){
		assertEquals("Negatives not allowed: -4,-5", ex.getMessage());
		}
	}
	@Test
        public void testWitMultipleNegativeNumbers(){
                try{
                        Calculator.add("2,-4,3,-5,-6");
                }catch(RuntimeException ex){
                assertEquals("Negatives not allowed: -4,-5,-6", ex.getMessage());
                }
        }
	@Test
        public void testWithNumberbiggerThan1000(){
        	assertEquals(2, Calculator.add("1001,2"));
        }
	@Test
	public void testWithDelimeterOfLenghtThree(){
	    assertEquals(6, Calculator.add("//[***]\n1***2***3"));
	}
	@Test
	public void testWithDelimeterOfLenghtFour(){
	    assertEquals(8, Calculator.add("//[%%%%]\n1%%%%2%%%%5"));
	}
	@Test
	public void testWithMultipleDelimiters(){
	    assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
	}
	@Test
	public void testWithMultipleDelimitersOfAnyLenght(){
	    assertEquals(13, Calculator.add("//[***][$][#####]\n1***2$3$1***4#####2"));
	}
}
