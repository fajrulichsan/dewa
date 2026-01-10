package com.astra.dewa.login.command.util;

import com.astra.dewa.model.OTP;
import com.astra.dewa.service.OTPLocalServiceUtil;
import com.astra.dewa.utils.DateUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author psmahmad1402
 */
public class RegistrationUtil {

   /**
    * This method is used to create OTP to the database
    * @param date current date when user perform register action
    * @param newUserEmail email of new user that used for registration process
    * @return generated OTP object
    */
   public static OTP createOTP(Date date, String newUserEmail) {
      OTP otp = null;
      try {
         OTP newOtp = OTPLocalServiceUtil.createOTP(0);
         newOtp.setCreatedDate(date);
         newOtp.setExpiredDate(DateUtil.expiredDateOTP(date));
         newOtp.setEmailNewUser(newUserEmail);
         newOtp.setOTPNumber(generateOTP());
         newOtp.setIsVerified(false);
         otp = OTPLocalServiceUtil.addOTP(newOtp);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return otp;
   }

   /**
    * This method is used to generate 6 digits random integer as OTP value
    * @return generated OTP value
    */
   private static Integer generateOTP() {
      Integer otp = null;
      try {
         Random random = new Random();
         otp = random.nextInt(900000) + 100000;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return otp;
   }

   /**
    * This method is used to validate OTP based on OTP number, email, and expired date
    * @param otpNumber 6 digits OTP sent to new user
    * @param email email of new user that used for registration process
    * @return status of OTP validity, TRUE or FALSE
    */
   public static boolean isOTPValid(Integer otpNumber, String email) {
      boolean result = false;
      try {
         DynamicQuery dynamicQuery = OTPLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("EmailNewUser", email));
         List<OTP> otpList = OTPLocalServiceUtil.dynamicQuery(dynamicQuery);
         if (!otpList.isEmpty()) {
            int size = otpList.size();
            OTP otp = otpList.get(size - 1);
            if (otp.getOTPNumber() == otpNumber && new Date().before(otp.getExpiredDate())) {
               result = true;
               otp.setIsVerified(true);
               OTPLocalServiceUtil.updateOTP(otp);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }

   public static boolean isOTPVerified(Integer otpNumber, String email) {
      try {
         DynamicQuery dynamicQuery = OTPLocalServiceUtil.dynamicQuery()
               .add(RestrictionsFactoryUtil.eq("EmailNewUser", email))
               .add(RestrictionsFactoryUtil.eq("OTPNumber", otpNumber))
               .add(RestrictionsFactoryUtil.eq("IsVerified", true));
         List<OTP> otpList = OTPLocalServiceUtil.dynamicQuery(dynamicQuery);
         if (!otpList.isEmpty()) {
            int size = otpList.size();
            OTP otp = otpList.get(size - 1);
            if (otp.getOTPNumber() == otpNumber && new Date().before(otp.getExpiredDate())) {
               return true;
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return false;
   }
}
