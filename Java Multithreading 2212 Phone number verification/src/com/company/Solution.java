package com.company;

/*

2212 Phone number verification

The checkTelNumber method should check if the telNumber argument is a valid phone number.
Validity Criteria:
1) if the number starts with '+', then it contains 12 digits
2) if the number starts with a digit or an opening bracket, then it contains 10 digits
3) may contain 0-2 characters '-', which can not go in a row
4) may contain 1 pair of brackets '(' and ')', and if it is, it is located to the left of the signs '-'
5) brackets inside contain clearly 3 digits
6) the number does not contain letters
7) the number ends with a digit
Examples:
+380501234567 - true
+38 (050) 1234567 - true
+ 38050123-45-67 - true
050123-4567 - true
+38) 050 (1234567 - false
+38 (050) 1-23-45-6-7 - false
050xxx4567 - false
050123456 - false
(0) 501234567 - false

Requirements:
1. The checkTelNumber method should return a value of type boolean.
2. The checkTelNumber method must be public.
3. The checkTelNumber method must accept a single parameter of type String.
4. The checkTelNumber method should correctly check the validity of the phone number passed to it as a parameter.


*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        // Check null
        if(telNumber == null) return false;
        //check digit in the end
        if(!telNumber.matches(".*\\d$")) return false;
        //check letters in number
        if (!(telNumber.replaceAll("\\d","").length()== telNumber.replaceAll("\\w","").length())) return false;
        // check "-"
        if ((telNumber.length()-telNumber.replaceAll("-","").length() < 3)) {

            if ( telNumber.indexOf("-") == telNumber.lastIndexOf("-") - 1) return false;
        }
        else return false;

        // check "(" ")" and check that () contain 3 digit
        if (telNumber.contains("(") || telNumber.contains(")")){
            if (telNumber.indexOf("(")== telNumber.lastIndexOf("(") && telNumber.indexOf(")")== telNumber.lastIndexOf(")")) {
                if(telNumber.contains("-")&&telNumber.indexOf(")")>telNumber.indexOf("-")) return false;
                if(!telNumber.matches(".*\\(\\d{3}\\).*")) return false;
            }
            else return false;
        }

        //check + (12 digit), else (10) digit
        if (telNumber.charAt(0) == '+') {
            if (telNumber.replaceAll("\\D", "").length() != 12) return false;
            else return true;
        }
        else if (telNumber.matches("^\\d.*|^\\(.*")){
            if (telNumber.replaceAll("\\D", "").length() != 10) return false;
            else return true;
        }



        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("05(025)44567"));

    }
}
