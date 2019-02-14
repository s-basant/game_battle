/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

/**
 * @author vncs
 *
 */
public class UI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * //Options for the combo box dialog String[] choices = {"Monday", "Tuesday"
		 * ,"Wednesday", "Thursday", "Friday"}; //Input dialog with a combo box String
		 * picked = (String)JOptionPane.showInputDialog(null, "Pick a Day:" ,
		 * "ComboBox Dialog", JOptionPane.QUESTION_MESSAGE , null, choices, choices[0]);
		 */
		MapInterface m = new MapInterface();
		List<Country> countries = m.getCountries();

		Country picked = selectCountry("select country", countries);

		System.out.println(picked);
		
		System.out.println(askNumber("pick a number", 1, 12));
		System.out.println(askNumber("pick a number", 20, 55));
		
		System.out.println(isUserOk("test"));

	}

	public static boolean isUserOk(String string) {
		int picked = JOptionPane.showConfirmDialog(null, string, "Yes/No?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (picked==JOptionPane.OK_OPTION)?true:false;
	}

	public static int askNumber(String prompt,  int min, int max) {
		List<Integer> collect = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
		Integer[] options = (Integer[]) collect.toArray((new Integer[collect.size()]));
		Integer picked = null;
		do {
			picked = (Integer) JOptionPane.showInputDialog(null, prompt
					, "Select one (MANDATORY)", JOptionPane.QUESTION_MESSAGE
					, null, options, options[0]);
		} while (picked == null);
		
		return picked;
	}

	public static Country selectCountry(String prompt, List<Country> countries) {
		//MapInterface m = new MapInterface();
		//String[] y = x.toArray(new String[0]);
		Country[] countries_array = countries.toArray(new Country[0]);
		
		Country picked;
		do {
			picked = (Country) JOptionPane.showInputDialog(null, prompt
					, "Select one (MANDATORY)", JOptionPane.QUESTION_MESSAGE
					, null, countries_array, countries_array[0]);
		} while (picked == null);
		
		return picked;
	}

}
