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
public class KataStringCalculator {
    
    public static void main(String[] args) throws Exception {
        StringCalculator S = new StringCalculator();
        System.out.println(S.add("//x\n3x2//y\n4y2")); //x\n3x3//=\n4=3
    }
    
}
