
package Model;

import java.time.Year;
import java.util.ArrayList;

import Exceptions.AgeException;
import Exceptions.EmptyFieldException;
import Exceptions.FoundInListsException;
import Exceptions.IdException;
import Exceptions.OutOfBoundsNumberException;
import javafx.scene.control.TextField;

public class Validation {
	
// -----------------------------------------------------------------------------------------------------		
// Check Exist 
	
	public static boolean checkIdExists(ArrayList<VotingBox<Citizen>> listOfVotingBoxs,String id) throws FoundInListsException {
		for (int i = 0; i < listOfVotingBoxs.size(); i++) {
			ArrayList<Citizen> citizensList = listOfVotingBoxs.get(i).getListOfCitizen();
				for (int j = 0; j < citizensList.size(); j++) {
					if(citizensList.get(j).getId().equals(id)) {
						System.out.println();
						 throw new FoundInListsException(citizensList.get(j).getId());
					}
				}
		}
		return false;
	}

// -----------------------------------------------------------------------------------------------------		
// Check methods

		public static boolean checkValidNumberOfSickDays(int numberOfSickDays) throws OutOfBoundsNumberException {	
			boolean valid = false;
			if (numberOfSickDays < 0) {
				valid =  true;
				throw new OutOfBoundsNumberException();
			}
			return valid;
		}
		
		public static void checkEmptyTextFields(ArrayList<TextField> tfArr) throws EmptyFieldException {
			for (int i = 0; i < tfArr.size(); i++) {
				if(tfArr.get(i).getText().isEmpty())
					throw new EmptyFieldException("Please Fill in All fields!");
			}
		}
		
// -----------------------------------------------------------------------------------------------------		
// Validation
	
	public static boolean validName(String name, String mode) throws IllegalArgumentException {
		if(name.equals(""))
			throw new StringIndexOutOfBoundsException();
		for (int i = 0; i < name.length(); i++) {
			if (!((Character.isAlphabetic(name.charAt(i)) || name.charAt(i) == ' '))) {
				throw new IllegalArgumentException("Enter a valid " + mode + "(without digits or signs )");
			}
		}
		return true;
	}
	
	public static boolean validId(String id) throws IdException {
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
	
	public static void validAge(int yearOfBirth) throws AgeException {
		int age = Year.now().getValue() - yearOfBirth;
		if (age < 18 || age >= 120) 
			throw new AgeException(age + " is Not an Eligble Age For Elections");
	}

// -----------------------------------------------------------------------------------------------------		
	
}
