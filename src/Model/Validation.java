package Model;

import Exceptions.AgeException;
import Exceptions.EmptyFieldException;
import Exceptions.IdException;
import Exceptions.OutOfBoundsNumberException;

import java.time.Year;

public class Validation {

	//================================================

	public static boolean sickDays(int numberOfSickDays) throws OutOfBoundsNumberException {
		if (numberOfSickDays < 0)
			throw new OutOfBoundsNumberException();
		return true;
	}

	//================================================

	public static boolean name(String name, String mode) throws IllegalArgumentException, EmptyFieldException {
		if(name.equals(""))
			throw new EmptyFieldException("Please enter a name");
		for (int i = 0; i < name.length(); i++) {
			if (!((Character.isAlphabetic(name.charAt(i)) || name.charAt(i) == ' '))) {
				throw new IllegalArgumentException("Enter a valid " + mode + "(without digits or signs )");
			}
		}
		return true;
	}

	//================================================

	public static boolean id(String id) throws IdException {
		if(id.length() != 9) 
			throw new IdException("Enter a valid id (9 digits)");
		else {
			for (int i = 0; i < id.length(); i++) {
				if (!(Character.isDigit(id.charAt(i))))
					throw new IdException("Enter a valid id (with out characters)");
			}
		}
		return true;
	}

	//================================================

	public static void age(int yearOfBirth) throws AgeException {
		int age = Year.now().getValue() - yearOfBirth;
		if (age < 18 || age >= 120) 
			throw new AgeException(age + " is Not an Eligible Age For Elections");
	}

	//================================================

	public static void validateInput(String name, String id, int birthYear, int sickDaysNum) throws EmptyFieldException, IdException, AgeException, OutOfBoundsNumberException {
		Validation.name(name, "Name");
		Validation.id(id);
		Validation.age(birthYear);
		Validation.sickDays(sickDaysNum);
	}
}
