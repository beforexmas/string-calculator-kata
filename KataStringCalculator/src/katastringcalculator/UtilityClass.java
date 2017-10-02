/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katastringcalculator;

/**
 *
 * @author keres
 */

class UtilityClass {
    protected String locError   = "";
    
    // Provide error check 
    protected void checkErrorDetected() {
        if (locError.length() > 0) {
            System.out.println("Error detected: " + locError);
        }
    }
    
    // Check if string is a number
    boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
}
