package com.astra.dewa.utils.dto;

import java.util.List;

public class TrainingDealerDto {

   private String dealerName;
   private List<TrainingParticipantDto> details;

   public String getDealerName() {
      return dealerName;
   }

   public void setDealerName(String dealerName) {
      this.dealerName = dealerName;
   }

   public List<TrainingParticipantDto> getDetails() {
      return details;
   }

   public void setDetails(List<TrainingParticipantDto> details) {
      this.details = details;
   }

}
