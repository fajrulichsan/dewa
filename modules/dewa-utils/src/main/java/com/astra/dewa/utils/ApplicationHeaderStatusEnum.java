package com.astra.dewa.utils;

import java.util.ArrayList;
import java.util.List;

public enum ApplicationHeaderStatusEnum {
   WAITING(1),
   APPROVE(2),
   REJECT(3),
   REVISE(4),
   DRAFT(5),
   SUBMIT(6),
   WAITING_LEVEL_1(7),
   WAITING_LEVEL_2(8),
   WAITING_LEVEL_3(9),
   WAITING_LEVEL_4(10),
   WAITING_LEVEL_5(11);

   private final int id;
   ApplicationHeaderStatusEnum(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public static int getLevel(int statusId) {
      switch (statusId) {
         case 7:
            return 1;
         case 8:
            return 2;
         case 9:
            return 3;
         case 10:
            return 4;
         case 11:
            return 5;
         default:
            return 0;
      }
   }

   public static boolean isWaiting(int statusId) {
      for (ApplicationHeaderStatusEnum status : ApplicationHeaderStatusEnum.values()) {
         if (status.getId() == statusId) {
            return status.name().startsWith("WAITING");
         }
      }
      return false;
   }

   public static List<Integer> waitingStatusIdList() {
      List<Integer> waitingStatusIds = new ArrayList<>();
      for (ApplicationHeaderStatusEnum status : ApplicationHeaderStatusEnum.values()) {
         if (status.name().startsWith("WAITING")) waitingStatusIds.add(status.getId());
      }
      return waitingStatusIds;
   }
}
