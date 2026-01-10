CREATE TABLE common (
   CommonKey    varchar(75) NOT NULL,
   CommonCode   varchar(9)  NOT NULL,
   CommonDesc1  text DEFAULT NULL,
   CommonDesc2  text  DEFAULT NULL,
   CommonDesc3  text  DEFAULT NULL,
   CommonDesc4  text  DEFAULT NULL,
   CommonDesc5  text  DEFAULT NULL,
   IsActive     tinyint       DEFAULT 1,
   Sequence     int DEFAULT NULL,
   CreatedBy    varchar(250)  DEFAULT NULL,
   CreatedDate  datetime      DEFAULT NULL,
   ModifiedBy   varchar(250)  DEFAULT NULL,
   ModifiedDate datetime      DEFAULT NULL,
   PRIMARY KEY (CommonKey)
);

---------------------------------------------
--- Template Email Register CalenderEvent ---
---------------------------------------------
Dear [NAMA_PESERTA]<br><br>Terima kasih anda telah terdaftar sebagai peserta untuk mengikuti <b>[NAMA_AGENDA]</b> yang akan diadakan pada tanggal <b>[TANGGAL_AGENDA] [JAM_AGENDA]</b><br><br>Dimohon untuk hadir tepat waktu.<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office<br><br>

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'CALENDER_EVENT_REGISTER',
   'CER',
   'Dear [NAMA_PESERTA]<br><br>Terima kasih anda telah terdaftar sebagai peserta untuk mengikuti <b>[NAMA_AGENDA]</b> yang akan diadakan pada tanggal <b>[TANGGAL_AGENDA] [JAM_AGENDA]</b><br><br>Dimohon untuk hadir tepat waktu.<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office<br><br>','Registrasi berhasil',
   NULL,
   NULL,NULL,1,NULL,NULL,NULL,NULL,NULL
);

-----------------------------------------------
--- Template Approver Upload Kwitansi Bonus ---
-----------------------------------------------
Dear Bapak/Ibu [NAMA_APPROVER]<br><br>Dimohon untuk melakukan review dan approval atas Permintaan <b>Kwitansi Bonus</b> dengan nomer tiket<br>[NOMOR_TIKET]<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Berikut link untuk melakukan pemeriksaan dokumen<br>[LINK_DIRECT]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'UPLOAD_BONUS_APPROVER_SUBMIT',
   'UBAS',
   'Dear Bapak/Ibu [NAMA_APPROVER]<br><br>Dimohon untuk melakukan review dan approval atas Permintaan <b>[GROUP_TIKET]</b> dengan nomer tiket<br>[NOMOR_TIKET]<br><br>Detail permintaan adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Berikut link untuk melakukan pemeriksaan dokumen<br>[LINK_DIRECT]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office',
   '[NOMOR_TIKET] Menunggu Persetujuan atas Transaksi [GROUP_TIKET]',
   '<a href="http://localhost:8080/group/dewa/upload-bonus" target="new_tab">Kwitansi Bonus</a>',
   '<a href="http://localhost:8080/group/dewa/upload-stnk" target="new_tab">Copy STNK Test Car</a>',
   NULL,1,NULL,NULL,NULL,NULL,NULL
);

--------------------------------------------------------------------------
--- Autosend email ke email requester setelah requester submit request ---
--------------------------------------------------------------------------
Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Anda telah mengajukan Permintaan [Kwitansi Bonus] dengan nomer tiket [NOMOR_TIKET]<br><br>Simpan nomor tersebut, karena akan digunakan sebagai referensi untuk menelusuri status perubahan yang sudah anda.<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Saat ini permintaan tersebut sedang diproses lebih lanjut.<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'UPLOAD_BONUS_REQUESTER_SUBMIT',
   'UBRS',
   'Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Anda telah mengajukan Permintaan <b>[GROUP_TIKET]</b> dengan nomer tiket <b>[NOMOR_TIKET]</b><br><br>Simpan nomor tersebut, karena akan digunakan sebagai referensi untuk menelusuri status perubahan yang sudah ada.<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Saat ini permintaan tersebut sedang diproses lebih lanjut.<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office',
   '[NOMOR_TIKET] Mengajukan Permintaan [GROUP_TIKET]',
   NULL,
   NULL,NULL,1,NULL,NULL,NULL,NULL,NULL
);

----------------------------------------------------------------------------------
--- Auto send email ke requester ketika request telah di approve oleh user DSO ---
----------------------------------------------------------------------------------
Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>Kwitansi Bonus</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> telah disetujui<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'UPLOAD_BONUS_REQUESTER_APPROVED',
   'UBRA',
   'Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>[GROUP_TIKET]</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> telah disetujui<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office',
   '[NOMOR_TIKET] Permintaan Disetujui [GROUP_TIKET]',
   NULL,
   NULL,NULL,1,NULL,NULL,NULL,NULL,NULL
);

---------------------------------------------------------------------------
--- Auto send email ke requester ketika request di reject oleh user DSO ---
---------------------------------------------------------------------------
Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>Kwitansi Bonus</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> telah ditolak untuk diajukan<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'UPLOAD_BONUS_REQUESTER_REJECTED',
   'UBRR',
   'Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>[GROUP_TIKET]</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> telah ditolak untuk diajukan<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINESS_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office',
   '[NOMOR_TIKET] Permintaan Ditolak [GROUP_TIKET]',
   NULL,
   NULL,NULL,1,NULL,NULL,NULL,NULL,NULL
);

--------------------------------------------------------------------------
--- Auto send email ke requester ketika request direvise oleh user DSO ---
--------------------------------------------------------------------------
Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>Kwitansi Bonus</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> perlu dilakukan revisi<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINES_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office

INSERT INTO common (CommonKey,CommonCode,CommonDesc1,CommonDesc2,CommonDesc3,CommonDesc4,CommonDesc5,IsActive,Sequence,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate) VALUES
(
   'UPLOAD_BONUS_REQUESTER_REVISE',
   'UBRRVS',
   'Dear Bapak/Ibu [NAMA_REQUESTER]<br><br>Permintaan <b>[GROUP_TIKET]</b> dengan nomer tiket <b>[NOMOR_TIKET]</b> perlu dilakukan revisi<br><br>Detail permintaan anda adalah sebagai berikut :<br>[REQUEST_DESCRIPTION]<br><br>Dengan manfaat sebagai berikut:<br>[BUSINES_BENEFIT]<br><br>Dan dengan notes sebagai berikut:<br>[NOTES]<br><br>Regards,<br><br>Daihatsu Sales Operation – Head Office',
   '[NOMOR_TIKET] Pengajuan Revisi [GROUP_TIKET]',
   NULL,
   NULL,NULL,1,NULL,NULL,NULL,NULL,NULL
);