package com.astra.dewa.utils;

import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Pattern;

/**
 * @author psmahmad1402
 */
public class InputValidationUtils {
   public static boolean isNumberOnly(String inputString) {
      Pattern numberOnlyPattern = Pattern.compile("^[0-9]+$");
      return numberOnlyPattern.matcher(inputString).find();
   }

   public static boolean isAlphaNumeric(String inputString) {
      Pattern alphaNumericPattern = Pattern.compile("^[A-Za-z0-9 ]+$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return alphaNumericPattern.matcher(inputString).find();
   }

   public static boolean isAlphabetOnly(String inputString) {
      Pattern alphabetPattern = Pattern.compile("^[A-Za-z ]+$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return alphabetPattern.matcher(inputString).find();
   }

   public static boolean isBasicCharacter(String inputString) {
      Pattern limitedSpecialCharPattern = Pattern.compile("^[A-Za-z0-9./,()@&\\-_ ]+$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return limitedSpecialCharPattern.matcher(inputString).find();
   }

   public static boolean isPhoneNumberValid(String inputString) {
      Pattern phoneNumberPattern = Pattern.compile("^\\+[0-9]+$");
      return phoneNumberPattern.matcher(inputString).find();
   }

   public static boolean isEmailValid(String inputString) {
      Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
      return Validator.isNotNull(inputString) && emailPattern.matcher(inputString).find();
   }

   public static boolean isNameValid(String inputString) {
      Pattern fullNamePattern = Pattern.compile("^[a-zA-Z\\s'.,-]+$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return Validator.isNotNull(inputString) && fullNamePattern.matcher(inputString).find();
   }

   public static boolean isEmailUserNameValid(String inputString) {
      Pattern emailUserNamePattern = Pattern.compile("^[a-zA-Z0-9._+-]+$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return Validator.isNotNull(inputString) && emailUserNamePattern.matcher(inputString).find();
   }

   public static boolean isBasicCharacterWithEnter(String inputString) {
      Pattern limitedSpecialCharPattern = Pattern.compile("^[A-Za-z0-9./,()@&\\-_ \n]+$",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return limitedSpecialCharPattern.matcher(inputString).find();
   }
   
   public static boolean isLink(String param) {
      Pattern pattern = Pattern.compile("^(https?:\\/\\/)?([\\w-]+\\.)+[\\w-]+(\\/[^\\s]*)?$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
      return Validator.isNotNull(param) && pattern.matcher(param).find();
   }
}
