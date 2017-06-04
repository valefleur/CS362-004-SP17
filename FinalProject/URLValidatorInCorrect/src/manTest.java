

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;




public class manTest
{
	public static void main (String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		
		while(true)
		{
			System.out.println("Type 'file' to test a file of URLs,");
			System.out.println("'man' to enter them manually");
			System.out.println(" or quit to end test.");
		
			input = scanner.next();
		
			if(input.equals("Quit") || input.equals("quit") )
			{
				//end test
				System.out.println("Quitting Test");
				scanner.close();
				return;
			}
			else if(input.equals(""))
			{
				System.out.println("No Text Entered");
			}
			else if(input.equals("file") || input.equals("File"))
			{
				System.out.println("Enter filename");
				
				input = scanner.next();
				
				if(isFilenameValid(input))
				{
					try 
					{
						File file = new File("src/" + input);
						FileReader fileReader = new FileReader(file);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						String line;
						PrintWriter writer = new PrintWriter("src//URLTestResults.txt", "UTF-8");
						
						while ((line = bufferedReader.readLine()) != null) 
						{		
							System.out.println(line);
							if(urlVal.isValid(line))
							{
								writer.println(line + " is a valid URL");
							}
							else
							{
								writer.println(line + " is not a valid URL");
							}
						}
					
						fileReader.close();
						writer.close();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					System.out.println("Results exported to URLTestResults.txt");
				}
				else
				{
					System.out.println("Invalid filename");
				}
			}
			else//assuming URL was entered
			{
				System.out.println("Enter URL");
				
				input = scanner.next();
				
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
	
	public static boolean isFilenameValid(String file) 
	{
		File f = new File(file);
  
  		try 
  		{
    		f.getCanonicalPath();
    		return true;
  		} catch (IOException e) 
  		{
    		return false;
  		}
	}
}

