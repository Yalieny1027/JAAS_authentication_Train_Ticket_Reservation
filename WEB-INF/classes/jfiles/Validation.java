package jfiles;

import java.util.regex.*;

public class Validation {
    public static boolean isValidString(String string)
	{
		String regex = "[a-zA-Z ]*$";
		Pattern p = Pattern.compile(regex);
		if (string == null) {
			return false;
		}
		Matcher m = p.matcher(string);
		return m.matches();
	}
    public static boolean isValidNumber(String number)
	{
		String regex = "\\d{10}$";
		Pattern p = Pattern.compile(regex);
		if (number == null) {
			return false;
		}
		Matcher m = p.matcher(number);
		return m.matches();
	}
	public static boolean isValidtrainNumber(String number)
	{
		String regex = "\\d{4}$";
		Pattern p = Pattern.compile(regex);
		if (number == null) {
			return false;
		}
		Matcher m = p.matcher(number);
		return m.matches();
	}
    public static boolean isValidAge(String age)
	{
		String regex = "\\d{2}$";
		Pattern p = Pattern.compile(regex);
		if (age == null) {
			return false;
		}
		Matcher m = p.matcher(age);
		return m.matches();
	}
	public static boolean isValidprice(String age)
	{
		String regex = "^[0-9]+$";
		Pattern p = Pattern.compile(regex);
		if (age == null) {
			return false;
		}
		Matcher m = p.matcher(age);
		return m.matches();
	}
	public static boolean isValidCla(String cla)
	{
		String regex = "[F]|[S]|[E]+";
		Pattern p = Pattern.compile(regex);
		if (cla == null) {
			return false;
		}
		Matcher m = p.matcher(cla);
		return m.matches();	}

		public static boolean isValidGender(String gen)
	{
		String regex = "[M]|[F]+";
		Pattern p = Pattern.compile(regex);
		if (gen == null) {
			return false;
		}
		Matcher m = p.matcher(gen);
		return m.matches();	}

}

