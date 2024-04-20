
package postalcode;

public class OotumliaPostalCode extends PostalCode {
    public String getCountry()
    {
        return "Ootumlian";
    }
    public OotumliaPostalCode(String code) throws PostalCodeException {
        super(code.toUpperCase());
    }

    @Override
    protected void validate() throws PostalCodeException {
        String postCode = getCode().toUpperCase();

        if (postCode.length() < 4)
            throwException("Postal code too short");

        int pos = 0; // character position within the postal code

        // STAGE 1: Expecting one or two letters
        if (!Character.isLetter(postCode.charAt(0)))
            throwException("Expecting letter at position 1");
        pos++;

        if (Character.isLetter(postCode.charAt(1)))
            pos++;

        // STAGE 3: Expecting whitespace
        if (!Character.isWhitespace(postCode.charAt(pos)))
            throwException("Expecting space at position " + (pos + 1));
        pos++;

        // STAGE 4: Expecting one or two digits
        if (!Character.isDigit(postCode.charAt(pos)))
            throwException("Expecting number at position " + (pos + 1));
        pos++;

        if (postCode.length() > pos && Character.isDigit(postCode.charAt(pos)))
            pos++;

        // STAGE 6: Expecting nothing
        if (postCode.length() > pos)
            throwException("Unexpected character at end of code");
    }
}
// This file contains material supporting section 2.8 of the textbook:
// "Object-Oriented Software Engineering" and is issued under the open-source license