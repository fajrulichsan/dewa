package com.astra.dewa.utils.models;

public class FileData {

   private Long fileId;
   private String fileName;
   private String filePath;

   // untuk operasi delete data
   private Boolean deleted;

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

   public Boolean getDeleted() {
      return deleted;
   }

   public void setDeleted(Boolean deleted) {
      this.deleted = deleted;
   }

}
