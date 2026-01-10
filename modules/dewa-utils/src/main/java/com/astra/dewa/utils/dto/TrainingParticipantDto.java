package com.astra.dewa.utils.dto;

public class TrainingParticipantDto {

   private Integer no;
   private String email;
   private String fullName;
   private String phone;
   private String dealerName;
   private String jabatan;

   public Integer getNo() {
      return no;
   }

   public void setNo(Integer no) {
      this.no = no;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getDealerName() {
      return dealerName;
   }

   public void setDealerName(String dealerName) {
      this.dealerName = dealerName;
   }

   public String getJabatan() {
      return jabatan;
   }

   public void setJabatan(String jabatan) {
      this.jabatan = jabatan;
   }
}
