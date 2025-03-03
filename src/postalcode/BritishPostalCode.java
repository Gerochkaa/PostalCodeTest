// This file contains material supporting section 2.8 of the textbook:
// "Object-Oriented Software Engineering" and is issued under the open-source license

package postalcode;

/**
 * This class is a subclass of PostalCode used to deal with British
 * postal codes.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @version July 2001
 */
public class BritishPostalCode extends PostalCode
{
    //Constructors ****************************************************

    /**
     * Constructs an instance of a British postal code object.
     *
     * @param code The code to be analysed.
     */
    public BritishPostalCode(String code) throws PostalCodeException
    {
        super(code.toUpperCase());
    }


    //Instance methods **************************************************

    /**
     * This method will return the country of origin of the postal code.
     *
     * @return A String containing the country of origin of this code.
     */
    public String getCountry()
    {
        return "British";
    }

    /**
     * This method will verify the validity of the postal code.
     *
     * @throws PostalCodeException If the code is found to be invalid.
     */
    protected void validate() throws PostalCodeException
    {
        String postCode = getCode().toUpperCase();

        if(postCode.length() < 6)
            throwException("Postal code too short");

        int pos = 0; // character position within the postal code

        // STAGE 1: Expecting one or two letters
        if(!Character.isLetter(postCode.charAt(0)))
            throwException("Expecting letter at position 1");
        pos++;

        if(Character.isLetter(postCode.charAt(1)))
            pos++;


        // STAGE 3: Expecting whitespace
        if(!Character.isWhitespace(postCode.charAt(pos)))
            throwException("Expecting space at position "+(pos+1));
        pos++;

        // STAGE 4: Expecting one or two digits
        if(!Character.isDigit(postCode.charAt(pos)))
            throwException("Expecting number at position "+(pos+1));
        pos++;

        if(postCode.length() > pos
                && Character.isDigit(postCode.charAt(pos)))
            pos++;

        // STAGE 5: Expecting two letters
        for(int i=0; i<2; i++)
        {
            if(postCode.length() <= pos
                    ||!Character.isLetter(postCode.charAt(pos)))
                throwException("Expecting letter at position "+(pos+1));
            pos++;
        }

        // STAGE 6: Expecting nothing
        if(postCode.length() > pos)
            throwException("Unexpected character at end of code");

        // Set destination
        setDestination("outside London.");
        String[] londonCodes = {"NW", "NE", "SW", "SE", "EC", "WC"};
        for(int i=0; i<londonCodes.length; i++)
        {
            if(postCode.startsWith(londonCodes[i]))
            {
                setDestination("within London.");
                return;
            }
        }


    }
}

