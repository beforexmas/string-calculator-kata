/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katastringcalculator;

import java.util.ArrayList;

/**
 *
 * @author keres
 */

// Convert the string into valid numbers
class NumbersParser {    

    private StringCalculator sc = null;
    private UtilityClass     uc = null;
    
    protected ArrayList<String> locDelimiters = new ArrayList<>();
    

    // Assign class variables and call delimiter-check functions
    protected void init(StringCalculator strCalc, UtilityClass utilityClass, String numbers) {
        sc = strCalc;
        uc = utilityClass;
        
        loadDefaultDelimeters();
        findNewDelimiters(numbers, uc);
    }
        
    // Base delimiters
    protected void loadDefaultDelimeters() {
        locDelimiters.add(",");
        locDelimiters.add("\n"); // Step 3
    }
    
    // Step 4: Find and add new delimiters
    protected void findNewDelimiters(String str,UtilityClass uc) {
        String slashes           = "";
        String possibleDelimiter = "";
        String carriageReturn    = "";
        
        int slashesDeletedCounter = 0;

        for (int i = 0; i < str.length() - 4; i++) {
            slashes           = str.substring(i, i+2);
            possibleDelimiter = "" + str.charAt(i+2);
            carriageReturn    = "" + str.charAt(i+3);
            if(slashes.equals("//") && !uc.isNumeric(possibleDelimiter) && carriageReturn.equals("\n")) {
                locDelimiters.add(possibleDelimiter);
                
                // Remove "//" if new delimiter was found
                StringBuilder sb = new StringBuilder(sc.locNumbers);
                sb.deleteCharAt(i - 2*slashesDeletedCounter);
                sb.deleteCharAt(i - 2*slashesDeletedCounter);
                slashesDeletedCounter++;
                sc.locNumbers = sb.toString();
            }
        }
    }
    
    protected ArrayList<Integer> parse(String str, ArrayList<String> locDelimiters) {
        
        ArrayList<Integer> Tmp = new ArrayList();

        if (DelimitersInSequence(str)) {
            uc.locError = "Bad input, delimiters cannot be sequential";
            return null;
        }
        
        String delimiterSplitList = "";

        // Create list of delimiter for the string to be splittd
        for (int i = 0; i < locDelimiters.size(); i++) {
            delimiterSplitList += locDelimiters.get(i);
            if (i != locDelimiters.size() - 1) {
                delimiterSplitList += "|";
            }
        }

        String numbers[] = str.split(delimiterSplitList);

        if (str.length() == 0) {
            return null;
        }

        // Add the elemnt if is a number
        for (String item : numbers) {
            if (item.equals("")) {
                item = "0";
            }
            if (!uc.isNumeric(item)) {
                uc.locError = "Bad input, found non-digit symbol";
                return null;
            }
            Tmp.add(Integer.parseInt(item.trim()));
        }
        return Tmp;
    }


    // Check if for each delimiter exist a number
    private boolean DelimitersHaveNumbers(String str) {
        String first = "" + str.charAt(0);
        String last = "" + str.charAt((str.length() - 1));

        for (String sep : locDelimiters) {
            if (sep.equals(first) || sep.equals(last)) {
                return false;
            }
        }
        return true;
    }

    // Check if some delimiters are sequential (find 2 non-numbers sequentially)
    private boolean DelimitersInSequence(String str) {
        String firstChar = "";
        String secondChar = "";

        for (int i = 0; i < str.length() - 1; i++) {
            firstChar = "" + str.charAt(i);
            secondChar = "" + str.charAt(i + 1);

            // If both delimiter and not "\n"
            if (isDelimiter(firstChar) && isDelimiter(secondChar) && !firstChar.equals("\n") && !secondChar.equals("\n")) {
                return true;
            }
        }

        return false;
    }

    // Check if char/string is a delimiter
    private boolean isDelimiter(String del) {
        for (String sx : locDelimiters) {
            if (del.equals(sx)) {
                return true;
            }
        }
        return false;
    }

}
