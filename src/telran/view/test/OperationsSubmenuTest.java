package telran.view.test;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class OperationsSubmenuTest {
	
	public static void main(String[] args) {
		Menu menu = new Menu("Calculator", getItems());
		menu.perform(new ConsoleInputOutput());

	}
	
	private static Item[] getItems() {
		Item[] res = { Item.of("Add numbers", CalculatorTest::add),
				Item.of("Subtract numbers", CalculatorTest::subtract),
				Item.of("Multiply numbers", CalculatorTest::multiply),
				Item.of("Divide numbers", CalculatorTest::divide), Item.exit()

		};
		return res;
	}


}
