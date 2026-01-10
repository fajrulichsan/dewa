package com.astra.dewa.utils.dto;

import com.astra.dewa.model.SalesReport;

import java.util.Date;

public class SalesReportDto {
   private int id;
   private String dealerCode;
   private String dealerName;
   private Long fileId;
   private String fileName;
   private String filePath;
   private Date periode;
   private String keterangan;
   private Date createdDate;
   private String createdBy;
   private Date modifiedDate;
   private String modifiedBy;

   public SalesReportDto(SalesReport d) {
      this.id = d.getId();
//      this.dealerCode = d.getDealerCode();
//      this.dealerName = d.getDealerName();
      this.fileId = d.getFileId();
      this.fileName = d.getFileName();
      this.filePath = d.getFilePath();
      this.periode = d.getPeriode();
      this.keterangan = d.getKeterangan();
      this.createdDate = d.getCreatedDate();
      this.createdBy = d.getCreatedBy();
      this.modifiedDate = d.getModifiedDate();
      this.modifiedBy = d.getModifiedBy();
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDealerCode() {
      return dealerCode;
   }

   public void setDealerCode(String dealerCode) {
      this.dealerCode = dealerCode;
   }

   public String getDealerName() {
      return dealerName;
   }

   public void setDealerName(String dealerName) {
      this.dealerName = dealerName;
   }

   public Long getFileId() {
      return fileId;
   }

   public void setFileId(Long fileId) {
      this.fileId = fileId;
   }

   public String getFileName() {
      return fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public String getFilePath() {
      return filePath;
   }

   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   public Date getPeriode() {
      return periode;
   }

   public void setPeriode(Date periode) {
      this.periode = periode;
   }

   public String getKeterangan() {
      return keterangan;
   }

   public void setKeterangan(String keterangan) {
      this.keterangan = keterangan;
   }

   public Date getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public String getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public Date getModifiedDate() {
      return modifiedDate;
   }

   public void setModifiedDate(Date modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public String getModifiedBy() {
      return modifiedBy;
   }

   public void setModifiedBy(String modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

}
