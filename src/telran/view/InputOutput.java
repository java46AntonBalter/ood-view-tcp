package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeObject(Object obj);

	default void close() {
	}

	default void writeLine(Object obj) {
		String str = obj + "\n";
		writeObject(str);
	}

	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		R result = null;
		while (true) {
			String str = readString(prompt);
			try {
				result = mapper.apply(str);
				break;
			} catch (Exception e) {
				writeLine(errorPrompt + e.getMessage());
			}
		}
		return result;

	}

	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}

	default Integer readInt(String prompt, String errorPrompt, int min, int max) {
		return readObject(prompt, errorPrompt, s -> {
			int num = Integer.parseInt(s);
			if (num < min) {
				throw new RuntimeException("less than " + min);
			}
			if (num > max) {
				throw new RuntimeException("greater than " + max);
			}
			return num;

		});
	}

	default Long readLong(String prompt, String errorPrompt) {
		try {
			return readObject(prompt, errorPrompt, Long::parseLong);
		} catch (NumberFormatException e) {
			throw new RuntimeException("string does not contain a parsable long");
		}
	}

	default String readOption(String prompt, String errorPrompt, List<String> options) {
		return readObject(prompt, errorPrompt, s -> {
			if (!options.contains(s.toUpperCase())) {
				throw new RuntimeException(". enter correct option");
			}
			return s;
		});
	}

	default LocalDate readDate(String prompt, String errorPrompt) {
		try {
			return readObject(prompt, errorPrompt, s -> LocalDate.parse(s));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage() + "Not able to parse the date from the given pattern");
		}
	}

	default LocalDate readDate(String prompt, String errorPrompt, String format) {
		try {
			return readObject(prompt, errorPrompt,
					s -> LocalDate.parse(s, new DateTimeFormatterBuilder()
							.parseCaseInsensitive().append(DateTimeFormatter.ofPattern(format)).toFormatter(Locale.ENGLISH)));
		} catch (DateTimeParseException e) {
			throw new RuntimeException("Not able to parse the date from the given pattern");
		}
	}

	default String readPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, s -> {
			if (!predicate.test(s)) {
				throw new RuntimeException("entry didn't pass the predicate test");
			}
			return s;
		});
	}

}