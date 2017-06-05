/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;




/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   //private boolean printStatus = false;
   //private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   /*
    * Manual test allows the user to manually enter a string through the command line and prints out the results of isValid(). 
    * Test also allows user to use a text file to input URLs for test. In this mode it outputs the results in a file called 
    * URLTestResults.txt.
    */
   public void testManualTest()
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
   
   /*
    * This partition tests all known schemes from URL_Schemes.txt
    * Known URL schemes obtained from https://www.iana.org/assignments/uri-schemes/uri-schemes.xhtml
    */
   public void testSchemePartition()
   {

	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String known_URL = "://www.google.com";
	   try 
		{
		    //Get known schemes
			File file = new File("src/URL_Schemes.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String schemeRead;
			PrintWriter writer = new PrintWriter("src//URLSchemeTestResults.txt", "UTF-8");
			
			while ((schemeRead = bufferedReader.readLine()) != null) 
			{		
				String[] splitScheme = schemeRead.split("\\s");
				String scheme = splitScheme[0];
				Boolean result = Boolean.valueOf(splitScheme[1]);
				if(urlVal.isValid(scheme + known_URL) == result)
				{
					writer.println(scheme + known_URL + " 			PASS expected " + result + ", received " + urlVal.isValid(scheme + known_URL));
				}
				else
				{
					writer.println(scheme + known_URL + " 			FAIL expected " + result + ", received " + urlVal.isValid(scheme + known_URL));
				}
			}
		
			fileReader.close();
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Results exported to URLSchemeTestResults.txt");
   }
   
   public void testAuthorityPartition()
   {
	   UrlValidator urlAuthVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String known_Scheme = "http://";
	   try 
		{
		    //Get known schemes
			File file = new File("src/URL_Authorities.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String authorityRead;
			PrintWriter writer = new PrintWriter("src//URLAuthorityTestResults.txt", "UTF-8");
			
			while ((authorityRead = bufferedReader.readLine()) != null) 
			{		
				String[] splitAuth = authorityRead.split("\\s");
				String Authority = splitAuth[0];
				Boolean result = Boolean.valueOf(splitAuth[1]);
				
				if(urlAuthVal.isValid(known_Scheme + Authority) == result)
				{
					writer.println(known_Scheme + Authority + " 			PASS: expected " + result + ", received " + urlAuthVal.isValid(known_Scheme + Authority));
				}
				else
				{
					writer.println(known_Scheme + Authority + " 			FAIL: expected " + result + ", received " + urlAuthVal.isValid(known_Scheme + Authority));
				}
			}
		
			fileReader.close();
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Results exported to URLAuthorityTestResults.txt");
   }
   
   public void testPortPartition()
   {
	   UrlValidator urlPortVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String known_URL = "http://www.google.com";
	   try 
		{
		    //Get known schemes
			File file = new File("src/URL_Ports.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String PortRead;
			PrintWriter writer = new PrintWriter("src//URLPortTestResults.txt", "UTF-8");
			
			while ((PortRead = bufferedReader.readLine()) != null) 
			{		
				String[] splitPort = PortRead.split("\\s");
				String Port = splitPort[0];
				Boolean result = Boolean.valueOf(splitPort[1]);
				
				if(urlPortVal.isValid(known_URL + Port) == result)
				{
					writer.println(known_URL + Port + " 			PASS: expected " + result + ", received " + urlPortVal.isValid(known_URL + Port));
				}
				else
				{
					writer.println(known_URL + Port + " 			FAIL: expected " + result + ", received " + urlPortVal.isValid(known_URL + Port));
				}
			}
		
			fileReader.close();
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Results exported to URLPortTestResults.txt");
   }

   public void testPathPartition(){
	   
	   UrlValidator urlPathVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String known_URL = "http://www.google.com";
	   try 
		{
		    //Get known schemes
			File file = new File("src/URL_Paths.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String PathRead;
			PrintWriter writer = new PrintWriter("src//URLPathTestResults.txt", "UTF-8");
			
			while ((PathRead = bufferedReader.readLine()) != null) 
			{		
				String[] splitPath = PathRead.split("\\s");
				String Path = splitPath[0];
				Boolean result = Boolean.valueOf(splitPath[1]);
				
				if(urlPathVal.isValid(known_URL + Path) == result)
				{
					writer.println(known_URL + Path + " 			PASS: expected " + result + ", received " + urlPathVal.isValid(known_URL + Path));
				}
				else
				{
					writer.println(known_URL + Path + " 			FAIL: expected " + result + ", received " + urlPathVal.isValid(known_URL + Path));
				}
			}
		
			fileReader.close();
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Results exported to URLPathTestResults.txt");
   }

	public void testQueryPartition()
	{
		UrlValidator urlQueryVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		   String known_URL = "http://www.google.com";
		   try 
			{
			    //Get known schemes
				File file = new File("src/URL_Queries.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String QueryRead;
				PrintWriter writer = new PrintWriter("src//URLQueryTestResults.txt", "UTF-8");
				
				while ((QueryRead = bufferedReader.readLine()) != null) 
				{		
					String[] splitQuery = QueryRead.split("\\s");
					String Query = splitQuery[0];
					Boolean result = Boolean.valueOf(splitQuery[1]);
					
					if(urlQueryVal.isValid(known_URL + Query) == result)
					{
						writer.println(known_URL + Query + " 			PASS: expected " + result + ", received " + urlQueryVal.isValid(known_URL + Query));
					}
					else
					{
						writer.println(known_URL + Query + " 			FAIL: expected " + result + ", received " + urlQueryVal.isValid(known_URL + Query));
					}
				}
			
				fileReader.close();
				writer.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			System.out.println("Results exported to URLQueryTestResults.txt");
	}
	
   /*
    * Tests the output of isValid() randomly.
    */
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   /*
    * Test to make sure all parts of unit test for validity and outputs results in a text file 
    * called UnitTestResults.txt
    */
   public void testAnyOtherUnitTest()
   {
	   
   }
   
   
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   
   /*
    * Method used to determine if file is valid. used for manual testing. 
    */
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
