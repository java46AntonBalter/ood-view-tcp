package telran.view.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class OperationsSubmenuTest {

	public static void main(String[] args) {
		Menu menu = new Menu("Types of calculations", getItems());
		menu.perform(new ConsoleInputOutput());
	}

	private static Item[] getItems() {
		Item[] res = { Item.of("Numbers Operations Menu", OperationsSubmenuTest::getSubmenuNumbers),
				Item.of("Dates Operations Menu", OperationsSubmenuTest::getSubmenuDates), Item.exit() };
		return res;
	}

	private static void getSubmenuNumbers(InputOutput io) {
		Menu submenu = new Menu("Numbers Calculator", getItemsSubmenu(1));
		submenu.perform(new ConsoleInputOutput());
	}

	private static void getSubmenuDates(InputOutput io) {
		Menu submenu = new Menu("Dates Calculator", getItemsSubmenu(2));
		submenu.perform(new ConsoleInputOutput());
	}

	private static Item[] getItemsSubmenu(int i) {
		switch (i) {
		case 1: {
			Item[] res = { Item.of("Add numbers", OperationsSubmenuTest::add),
					Item.of("Subtract numbers", OperationsSubmenuTest::subtract),
					Item.of("Multiply numbers", OperationsSubmenuTest::multiply),
					Item.of("Divide numbers", OperationsSubmenuTest::divide), 
					Item.exit()

			};
			return res;
		}
		case 2: {
			Item[] res = { Item.of("Date after given number of days", OperationsSubmenuTest::addDate), 
					Item.of("Date before given number of days", OperationsSubmenuTest::subtractDate),
					Item.of("Number days between two dates", OperationsSubmenuTest::daysBetweenDates), 
					Item.of("Age according to the birthdate", OperationsSubmenuTest::getAgeFromBirthday), 
					Item.exit()

			};
			return res;
		}
		default: {
			Item[] res = null;
			return res;			
		}
		}
		
	}
	
	static void addDate(InputOutput io) {
		int numberOfDays = io.readInt("enter number of days", "no number");
		io.writeLine(LocalDate.now().plus(numberOfDays, ChronoUnit.DAYS));
	}
	
	static void subtractDate(InputOutput io) {
		int numberOfDays = io.readInt("enter number of days", "no number");
		io.writeLine(LocalDate.now().minus(numberOfDays, ChronoUnit.DAYS));
	}
	
	static void daysBetweenDates(InputOutput io) {
		LocalDate date1 = io.readDate("enter starting date inclusive YYYY-MM-DD", "no date");
		LocalDate date2 = io.readDate("enter finishing date exclusive YYYY-MM-DD", "no date");
		io.writeLine(ChronoUnit.DAYS.between(date1, date2));
	}
	
	static void getAgeFromBirthday(InputOutput io) {
		LocalDate birthday = io.readDate("enter birthday in format YYYY-MM-DD", "no date");
		io.writeLine(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
	}

	static void add(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] + numbers[1]);
	}

	static void subtract(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] - numbers[1]);
	}

	static void multiply(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] * numbers[1]);
	}

	static void divide(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] / numbers[1]);
	}

	private static int[] enterNumbers(InputOutput io) {
		int res[] = new int[2];
		res[0] = io.readInt("enter first number", "no number");
		res[1] = io.readInt("enter second number", "no number");
		return res;
	}

}
