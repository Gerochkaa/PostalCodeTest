// This file contains material supporting section 2.8 of the textbook:
// "Object-Oriented Software Engineering" and is issued under the open-source license

import postalcode.*;

/**
 * This class retrieves a postal code that will be inputted by the user
 * through the console.  It then calls the appropriate methods to verify
 * it.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @version July 2001
 */
public class PostalTest
{
    //Class methods *****************************************************

    /**
     * Retrieves a postal code from the console and analyses it.
     */
    public static void main(String[] args)
    {
        PostalCode code = null;
        String codeString = null;

        System.out.println("\nPostal code analysis program.");

        codeString = getInput();  //Get the code to analyse
        try
        {
            code = new BritishPostalCode(codeString);
        }
        catch (PostalCodeException ex1)
        {
            try
            {
                code = new CanadianPostalCode(codeString);
            }
            catch (PostalCodeException ex2)
            {
                try
                {
                    code = new USZipCode(codeString);
                }
                catch (PostalCodeException ex3) {}
            }
        }
        if (code != null)
            System.out.println(code);
        else
            System.out.println(codeString + " is an invalid ZIP or postal code.");
    }

    /**
     * This method handles the input from the console.  It returns a
     * String containing the postal code to be analysed.
     *
     * @return A String containing a postal code.
     */
    static private String getInput()
    {
        byte[] buffer = new byte[1024];
        String myString = "";

        System.out.print("Please enter a ZIP/postal code: ");
        try
        {
            // Read the data into a byte array, and then convert it to a
            // String while trimming all useless whitespace, i.e. the leading
            // or trailing spaces.

            System.in.read(buffer);
            myString = new String(buffer).trim();
        }
        catch(Exception e){}
        return myString;
    }
}

