package com.astra.dewa.utils;

public enum ApplicationAssignStatusEnum {
   WAITING(1),
   APPROVE(2),
   REJECT(3),
   REVISE(4),
   COMPLETE(5),
   SUBMIT(6),
   IN_REVIEW(7);

   private final int id;
   ApplicationAssignStatusEnum(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }
}
