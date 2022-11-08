package telran.net.test;

import telran.view.InputOutput;
import telran.view.Item;

public class CalculatorMenu {
	
	private static Calculator thisCalculator;
	
	public static Item[] getCalculatorItems(Calculator calculator) {
		thisCalculator = calculator;
		Item [] items = {
				Item.of("Add two numbers", CalculatorMenu::add),
				Item.of("Subtract two numbers", CalculatorMenu::subtract),
				Item.of("Divide two numbers", CalculatorMenu::divide),
				Item.of("Multiply two numbers", CalculatorMenu::multiply)
		};
		return items;
		
	}
	
	static Double[] getTwoNumbers(InputOutput io) {
		Double  firstNumber = io.readDouble("Enter first number", "no number");
		Double secondNumber = io.readDouble("Enter second number","no number");
		return new Double[] {firstNumber, secondNumber};
	}

	static void add(InputOutput io) {
		Double[] numbers =  getTwoNumbers(io);
		io.writeLine(thisCalculator.add(numbers[0], numbers[1]));
	}

	static void subtract(InputOutput io) {
		Double[] numbers =  getTwoNumbers(io);
		io.writeLine(thisCalculator.subtract(numbers[0], numbers[1]));
	}
	
	static void divide(InputOutput io) {
		Double[] numbers =  getTwoNumbers(io);
		io.writeLine(thisCalculator.divide(numbers[0], numbers[1]));
	}
	
	static void multiply(InputOutput io) {
		Double[] numbers =  getTwoNumbers(io);
		io.writeLine(thisCalculator.multiply(numbers[0], numbers[1]));
	}

}
