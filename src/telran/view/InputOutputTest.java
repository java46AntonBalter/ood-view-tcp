package telran.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
	InputOutput io = new ConsoleInputOutput();

	@Test
	@Disabled
	void readObjectTest() {
		Integer[] array = io.readObject("Enter numbers separated by space", "no number ", s -> {

			String strNumbers[] = s.split(" ");
			return Arrays.stream(strNumbers).map(str -> Integer.parseInt(str)).toArray(Integer[]::new);

		});
		io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));

	}

	@Test
	@Disabled
	void readIntMinMax() {
		Integer res = io.readInt("Enter any number in range [1, 40]", "no number ", 1, 40);
		io.writeLine(res);
	}

	@Test
	@Disabled
	void readLongTest() {
		Long res = io.readLong("Enter any number in range [" + Long.MIN_VALUE + ", " + Long.MAX_VALUE + "]",
				"no number ");
		io.writeLine(res);
	}

	@Test
	@Disabled
	void readOptionTest() {
		ArrayList<String> options = new ArrayList<>();
		options.add("FORWARD");
		options.add("BACK");
		options.add("UP");
		options.add("DOWN");
		String res = io.readOption("Choose and enter an option: " + options, "wrong option", options);
		io.writeLine(res);
	}

	@Test
	@Disabled
	void readDateISO() {
		LocalDate res = io.readDate("Enter date in format YYYY-MM-DD", "no date ");
		io.writeLine(res);
	}

	@Test
	void readDateFormated() {
		LocalDate res = io.readDate("Enter date in format d MMM uuuu", "no date ", "d MMM uuuu");
		io.writeLine(res.toString());
	}

	@Test
	@Disabled
	void readPredicateTest() {
		String res = io.readPredicate("Enter password. min length - 7 characters", "password is too short ",
				s -> s.length() < 7 ? false : true);
		io.writeLine(res);
	}

}