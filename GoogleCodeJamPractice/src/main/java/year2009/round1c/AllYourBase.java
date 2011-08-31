package year2009.round1c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class AllYourBase {

	public long findMinimumValue(String input)
	{
		long answer = 0;
		List<Character> uniqueChar = findUniqueCharacters(input);

		CharTranslate ct = new CharTranslate();
		
		String translatedString = ct.convertString(uniqueChar, input);
		
		answer = convertBaseToDecimal(uniqueChar, translatedString);
		
		return answer;
	}


	private long convertBaseToDecimal(List<Character> uniqueChar,
			String translatedString) {
		long answer;
		if(uniqueChar.size()!=1)
			answer = BaseConverterUtil.fromOtherBaseToDecimal(uniqueChar.size(), translatedString);
		else
			answer = BaseConverterUtil.fromOtherBaseToDecimal(2, translatedString);
		return answer;
	}


	private List<Character> findUniqueCharacters(String input) {
		List<Character> uniqueChar = new ArrayList<Character>();
		uniqueChar.add(input.toCharArray()[0]);
		
		for(char val : input.toCharArray())
		{
			boolean foundChar = false;
			for(int c = 0; c < uniqueChar.size(); c++)
			{
				if(val==uniqueChar.get(c))
				{
					foundChar = true;
					break;
				}
			}

			if(!foundChar)
			{
				uniqueChar.add(val);
			}
		}
		return uniqueChar;
	}

	private class CharTranslate
	{
		char origVal, baseVal;
		String origInput;
		char arrOrigInput[];
		public CharTranslate()
		{
			;
		}
		public CharTranslate(char origVal, char baseVal)
		{
			this.origVal = origVal;
			this.baseVal = baseVal;
		}

		public String convertString(List<Character> uniqueChars, String input)
		{
			List<CharTranslate> result = new ArrayList<AllYourBase.CharTranslate>();
			for(int c = 0; c < uniqueChars.size(); c++)
			{
				switch(c)
				{
				case 0:
					result.add(new CharTranslate(uniqueChars.get(c),'1'));
					break;
				case 1:
					result.add(new CharTranslate(uniqueChars.get(c),'0'));
					break;
				default:
					result.add(new CharTranslate(uniqueChars.get(c),convertIntToChar(c)));					
				}
			}
			origInput = input;
			arrOrigInput = origInput.toCharArray();
			char arrResult[] = new char[arrOrigInput.length];

			for(CharTranslate ct : result)
			{
				for(int c = 0; c < arrOrigInput.length; c++)
				{
					if(arrOrigInput[c]==ct.origVal)
					{
						arrResult[c] = ct.baseVal;
					}
				}
			}
			return String.valueOf(arrResult);
		}

		private char convertIntToChar(int value)
		{
			final String baseDigits = "0123456789abcdefghijklmnopqrstuvwxyz";
			if(value >= baseDigits.length())
				return '\\';
			else
				return baseDigits.toCharArray()[value];
		}
	}

	private static class BaseConverterUtil {

		private static final String baseDigits = "0123456789abcdefghijklmnopqrstuvwxyz";

		public static String toBase62( int decimalNumber ) {
			return fromDecimalToOtherBase( 62, decimalNumber );
		}

		public static String toBase36( int decimalNumber ) {
			return fromDecimalToOtherBase( 36, decimalNumber );
		}

		public static String toBase16( int decimalNumber ) {
			return fromDecimalToOtherBase( 16, decimalNumber );
		}

		public static String toBase8( int decimalNumber ) {
			return fromDecimalToOtherBase( 8, decimalNumber );
		}

		public static String toBase2( int decimalNumber ) {
			return fromDecimalToOtherBase( 2, decimalNumber );
		}

		public static long fromBase62( String base62Number ) {
			return fromOtherBaseToDecimal( 62, base62Number );
		}

		public static long fromBase36( String base36Number ) {
			return fromOtherBaseToDecimal( 36, base36Number );
		}

		public static long fromBase16( String base16Number ) {
			return fromOtherBaseToDecimal( 16, base16Number );
		}

		public static long fromBase8( String base8Number ) {
			return fromOtherBaseToDecimal( 8, base8Number );
		}

		public static long fromBase2( String base2Number ) {
			return fromOtherBaseToDecimal( 2, base2Number );
		}

		private static String fromDecimalToOtherBase ( int base, int decimalNumber ) {
			String tempVal = decimalNumber == 0 ? "0" : "";
			int mod = 0;

			while( decimalNumber != 0 ) {
				mod = decimalNumber % base;
				tempVal = baseDigits.substring( mod, mod + 1 ) + tempVal;
				decimalNumber = decimalNumber / base;
			}

			return tempVal;
		}

		private static long fromOtherBaseToDecimal( int base, String number ) {
			int iterator = number.length();
			long returnValue = 0;
			long multiplier = 1;

			while( iterator > 0 ) {
				returnValue += ( baseDigits.indexOf( number.substring( iterator - 1, iterator ) ) * multiplier );
				multiplier = multiplier * base;
				--iterator;
			}
			return returnValue;
		}

	}


	public static void main(String args[]) throws Exception
	{
		int count = 1;
		AllYourBase ayb = new AllYourBase();
		String input = "102345678";
		long result = ayb.findMinimumValue(input);
		System.out.println("Final Value for " + input + ": " + result);

		Scanner scanner = null;
		FileOutputStream fos = null;
		try
		{
			scanner = new Scanner(new File("src/main/resources/A-large-practice.in"));
			fos = new FileOutputStream("src/main/resources/A-large-practice-output.in");
			scanner.nextLine();
			
			while(scanner.hasNext())
			{
				input = scanner.nextLine();
				
				result = ayb.findMinimumValue(input);
				String finalResult = "Case #" + count + ": " + result + "\n";//" :: \t\tOriginal String: " + input + "\n";
				fos.write(finalResult.getBytes());
				count++;
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			if(scanner!= null)
				scanner.close();
			if(fos!=null)
				fos.close();
		}
	}
}
