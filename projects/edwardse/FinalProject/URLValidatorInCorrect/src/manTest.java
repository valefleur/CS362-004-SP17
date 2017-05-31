

import junit.framework.TestCase;
import java.util.Scanner;


public class manTest
{
	public static void main (String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		
		while(true)
		{
			System.out.println("Enter URL to test or quit to end test.");
		
			input = scanner.next();
		
			if(input.equals("Quit") || input.equals("quit") )
			{
				//end test
				System.out.println("Quitting Test");
				return;
			}
			else if(input.equals(""))
			{
				System.out.println("No Text Entered");
			}
			else//assuming URL was entered
			{
				if(urlVal.isValid(input))
				{
					System.out.println("URL is valid");
				}
				else
				{
					System.out.println("URL is not valid");
				}
			}
		}
		
	}

}
