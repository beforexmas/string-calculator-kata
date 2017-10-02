/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katastringcalculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author keres
 */
public class StringCalculator {

    protected String locNumbers = "";

    // Step 1: main function
    int add(String numbers) throws Exception {
        UtilityClass uc = new UtilityClass();
        
        NumbersParser np = new NumbersParser();
        
        locNumbers = numbers;
        
        np.init(this, uc, locNumbers);
        
        ArrayList<Integer> values = np.parse(locNumbers, np.locDelimiters);
        
        uc.checkErrorDetected();
        
        return sum(values);
    }

    // Sum all values
    int sum(ArrayList<Integer> values) throws Exception {
        if (values != null && values.size() > 0) {
            int res = 0;
            for (int x : values) {                                              // Step 2
                if(x < 0) {                                                     // Step 5
                    throw new Exception("negatives not allowed: " + x, null); 
                }
                if(x < 1001) {                                                  // Step 6
                    res += x;
                }
            }
            return res;
        }
        return 0;
    }
    
}
