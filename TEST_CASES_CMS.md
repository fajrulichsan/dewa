# Test Cases - CMS Module (Positive Test Only)

## 1. CMS Wilayah

### TC-WIL-001: View List Wilayah
**Deskripsi**: Menampilkan daftar wilayah
**Precondition**: User sudah login dengan role yang memiliki akses CMS Wilayah
**Test Steps**:
1. Akses menu CMS Wilayah
2. Sistem menampilkan tabel list wilayah

**Expected Result**: 
- Tabel wilayah ditampilkan dengan kolom: No, Name, Action
- Data wilayah ditampilkan dengan benar
- Pagination berfungsi
- Header tabel berwarna biru (#014689)

### TC-WIL-002: Search Wilayah
**Deskripsi**: Mencari data wilayah berdasarkan keyword
**Precondition**: Terdapat data wilayah di database
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Input keyword di search box (minimal 3 karakter)
3. Tekan Enter atau tunggu auto-search

**Expected Result**: 
- Data wilayah yang sesuai keyword ditampilkan
- Data yang tidak sesuai tidak ditampilkan

### TC-WIL-003: Add New Wilayah
**Deskripsi**: Menambahkan wilayah baru
**Precondition**: User memiliki akses untuk menambah wilayah
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Klik tombol "Tambah"
3. Isi form nama wilayah dengan data valid
4. Klik tombol Save

**Expected Result**: 
- Form add wilayah ditampilkan
- Data wilayah baru tersimpan
- Muncul notifikasi success
- Redirect ke halaman list wilayah
- Data baru muncul di tabel

### TC-WIL-004: Edit Wilayah
**Deskripsi**: Mengubah data wilayah yang sudah ada
**Precondition**: Terdapat data wilayah di database
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Klik icon edit pada salah satu data wilayah
3. Ubah nama wilayah
4. Klik tombol Save

**Expected Result**: 
- Form edit wilayah ditampilkan dengan data existing
- Data wilayah berhasil diupdate
- Muncul notifikasi success
- Data terupdate di tabel list

### TC-WIL-005: Pagination Wilayah
**Deskripsi**: Navigasi antar halaman menggunakan pagination
**Precondition**: Terdapat data wilayah lebih dari 1 halaman
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Klik tombol next page atau nomor halaman
3. Observe data yang ditampilkan

**Expected Result**: 
- Data halaman berikutnya ditampilkan
- Nomor halaman aktif highlight dengan warna biru (#10569C)
- Icon chevron left/right menggunakan FontAwesome
- Icon aktif berwarna theme (#014689), disabled berwarna abu-abu

### TC-WIL-006: Sort Data Wilayah
**Deskripsi**: Mengurutkan data berdasarkan kolom
**Precondition**: Terdapat data wilayah di database
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Klik header kolom Name untuk sorting
3. Observe urutan data

**Expected Result**: 
- Data diurutkan ascending/descending
- Icon sorting FontAwesome muncul (fa-sort-up/fa-sort-down)
- Icon sorting di posisi kanan kolom

### TC-WIL-007: Change Entries Per Page
**Deskripsi**: Mengubah jumlah data per halaman
**Precondition**: Terdapat data wilayah di database
**Test Steps**:
1. Akses halaman CMS Wilayah
2. Pilih jumlah entries dari dropdown (10, 25, 50, 100)
3. Observe data yang ditampilkan

**Expected Result**: 
- Jumlah data per halaman berubah sesuai pilihan
- Pagination terupdate
- Dropdown menggunakan Select2

---

## 2. CMS Cabang

### TC-CAB-001: View List Cabang
**Deskripsi**: Menampilkan daftar cabang
**Precondition**: User sudah login dengan role yang memiliki akses CMS Cabang
**Test Steps**:
1. Akses menu CMS Cabang
2. Sistem menampilkan tabel list cabang

**Expected Result**: 
- Tabel cabang ditampilkan dengan kolom: No, Name, Action
- Data cabang ditampilkan dengan benar
- Pagination berfungsi
- Header tabel berwarna biru (#014689)

### TC-CAB-002: Search Cabang
**Deskripsi**: Mencari data cabang berdasarkan keyword
**Precondition**: Terdapat data cabang di database
**Test Steps**:
1. Akses halaman CMS Cabang
2. Input keyword di search box (minimal 3 karakter)
3. Tekan Enter atau tunggu auto-search

**Expected Result**: 
- Data cabang yang sesuai keyword ditampilkan
- Data yang tidak sesuai tidak ditampilkan

### TC-CAB-003: Add New Cabang
**Deskripsi**: Menambahkan cabang baru
**Precondition**: User memiliki akses untuk menambah cabang
**Test Steps**:
1. Akses halaman CMS Cabang
2. Klik tombol "Tambah"
3. Isi form data cabang dengan data valid
4. Klik tombol Save

**Expected Result**: 
- Form add cabang ditampilkan
- Data cabang baru tersimpan
- Muncul notifikasi success
- Redirect ke halaman list cabang
- Data baru muncul di tabel

### TC-CAB-004: Edit Cabang
**Deskripsi**: Mengubah data cabang yang sudah ada
**Precondition**: Terdapat data cabang di database
**Test Steps**:
1. Akses halaman CMS Cabang
2. Klik icon edit pada salah satu data cabang
3. Ubah data cabang
4. Klik tombol Save

**Expected Result**: 
- Form edit cabang ditampilkan dengan data existing
- Data cabang berhasil diupdate
- Muncul notifikasi success
- Data terupdate di tabel list

### TC-CAB-005: Pagination Cabang
**Deskripsi**: Navigasi antar halaman menggunakan pagination
**Precondition**: Terdapat data cabang lebih dari 1 halaman
**Test Steps**:
1. Akses halaman CMS Cabang
2. Klik tombol next/previous atau nomor halaman
3. Observe data yang ditampilkan

**Expected Result**: 
- Data halaman berikutnya/sebelumnya ditampilkan
- Nomor halaman aktif highlight dengan warna biru (#10569C)
- Icon chevron berfungsi dengan benar

### TC-CAB-006: Sort Data Cabang
**Deskripsi**: Mengurutkan data berdasarkan kolom
**Precondition**: Terdapat data cabang di database
**Test Steps**:
1. Akses halaman CMS Cabang
2. Klik header kolom untuk sorting
3. Observe urutan data

**Expected Result**: 
- Data diurutkan ascending/descending
- Icon sorting FontAwesome muncul dengan benar

---

## 3. CMS Master Dealer

### TC-DEAL-001: View List Dealer
**Deskripsi**: Menampilkan daftar master dealer
**Precondition**: User sudah login dengan role yang memiliki akses CMS Master Dealer
**Test Steps**:
1. Akses menu CMS Master Dealer
2. Sistem menampilkan tabel list dealer

**Expected Result**: 
- Tabel dealer ditampilkan dengan semua kolom yang diperlukan
- Data dealer ditampilkan dengan benar
- Pagination berfungsi
- Header tabel berwarna biru (#014689)

### TC-DEAL-002: Search Dealer
**Deskripsi**: Mencari data dealer berdasarkan keyword
**Precondition**: Terdapat data dealer di database
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Input keyword di search box (minimal 3 karakter)
3. Tekan Enter atau tunggu auto-search

**Expected Result**: 
- Data dealer yang sesuai keyword ditampilkan
- Data yang tidak sesuai tidak ditampilkan
- Search berfungsi pada multiple kolom

### TC-DEAL-003: Add New Dealer
**Deskripsi**: Menambahkan dealer baru
**Precondition**: User memiliki akses untuk menambah dealer
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Klik tombol "Tambah"
3. Isi semua form data dealer dengan data valid
4. Klik tombol Save

**Expected Result**: 
- Form add dealer ditampilkan dengan semua field
- Data dealer baru tersimpan
- Muncul notifikasi success
- Redirect ke halaman list dealer
- Data baru muncul di tabel

### TC-DEAL-004: Edit Dealer
**Deskripsi**: Mengubah data dealer yang sudah ada
**Precondition**: Terdapat data dealer di database
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Klik icon edit pada salah satu data dealer
3. Ubah data dealer
4. Klik tombol Save

**Expected Result**: 
- Form edit dealer ditampilkan dengan data existing
- Data dealer berhasil diupdate
- Muncul notifikasi success
- Data terupdate di tabel list

### TC-DEAL-005: View Dealer Tabs
**Deskripsi**: Navigasi antar tabs pada halaman dealer
**Precondition**: User akses halaman CMS Master Dealer
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Observe tabs yang tersedia (Master Data Dealer, Master Data SK IRIS)
3. Klik tab untuk berpindah

**Expected Result**: 
- Tabs ditampilkan dengan benar
- Navigasi antar tabs berfungsi
- Content sesuai dengan tab yang dipilih

### TC-DEAL-006: Pagination Dealer
**Deskripsi**: Navigasi antar halaman menggunakan pagination
**Precondition**: Terdapat data dealer lebih dari 1 halaman
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Klik tombol next/previous atau nomor halaman
3. Observe data yang ditampilkan

**Expected Result**: 
- Data halaman berikutnya/sebelumnya ditampilkan
- Pagination styling konsisten dengan design system

### TC-DEAL-007: Sort Data Dealer
**Deskripsi**: Mengurutkan data berdasarkan kolom
**Precondition**: Terdapat data dealer di database
**Test Steps**:
1. Akses halaman CMS Master Dealer
2. Klik header kolom untuk sorting
3. Observe urutan data

**Expected Result**: 
- Data diurutkan ascending/descending
- Icon sorting berfungsi dengan benar

---

## 4. CMS Master Role

### TC-ROLE-001: View List Master Role
**Deskripsi**: Menampilkan daftar master role
**Precondition**: User sudah login dengan role yang memiliki akses CMS Master Role
**Test Steps**:
1. Akses menu CMS Master Role
2. Sistem menampilkan tabel list master role

**Expected Result**: 
- Tabel master role ditampilkan dengan kolom yang diperlukan
- Data role ditampilkan dengan benar
- Pagination berfungsi
- Header tabel berwarna biru (#014689)

### TC-ROLE-002: Search Master Role
**Deskripsi**: Mencari data master role berdasarkan keyword
**Precondition**: Terdapat data master role di database
**Test Steps**:
1. Akses halaman CMS Master Role
2. Input keyword di search box (minimal 3 karakter)
3. Tekan Enter atau tunggu auto-search

**Expected Result**: 
- Data master role yang sesuai keyword ditampilkan
- Data yang tidak sesuai tidak ditampilkan

### TC-ROLE-003: Add New Master Role
**Deskripsi**: Menambahkan master role baru
**Precondition**: User memiliki akses untuk menambah master role
**Test Steps**:
1. Akses halaman CMS Master Role
2. Klik tombol "Tambah"
3. Isi form role name dengan data valid
4. Klik tombol Save

**Expected Result**: 
- Modal/form add master role ditampilkan
- Data master role baru tersimpan
- Muncul notifikasi success
- Modal tertutup
- Data baru muncul di tabel

### TC-ROLE-004: Edit Master Role
**Deskripsi**: Mengubah data master role yang sudah ada
**Precondition**: Terdapat data master role di database
**Test Steps**:
1. Akses halaman CMS Master Role
2. Klik icon edit pada salah satu data master role
3. Ubah role name
4. Klik tombol Save

**Expected Result**: 
- Modal/form edit master role ditampilkan dengan data existing
- Data master role berhasil diupdate
- Muncul notifikasi success
- Data terupdate di tabel list

### TC-ROLE-005: Pagination Master Role
**Deskripsi**: Navigasi antar halaman menggunakan pagination
**Precondition**: Terdapat data master role lebih dari 1 halaman
**Test Steps**:
1. Akses halaman CMS Master Role
2. Klik tombol next/previous atau nomor halaman
3. Observe data yang ditampilkan

**Expected Result**: 
- Data halaman berikutnya/sebelumnya ditampilkan
- Pagination styling konsisten dengan design system
- Icon chevron berfungsi dengan benar

### TC-ROLE-006: Sort Data Master Role
**Deskripsi**: Mengurutkan data berdasarkan kolom
**Precondition**: Terdapat data master role di database
**Test Steps**:
1. Akses halaman CMS Master Role
2. Klik header kolom untuk sorting
3. Observe urutan data

**Expected Result**: 
- Data diurutkan ascending/descending
- Icon sorting FontAwesome muncul dengan benar

---

## 5. CMS Master Approval

### TC-APP-001: View List Master Approval
**Deskripsi**: Menampilkan daftar master approval
**Precondition**: User sudah login dengan role yang memiliki akses CMS Master Approval
**Test Steps**:
1. Akses menu CMS Master Approval
2. Sistem menampilkan tabel list master approval

**Expected Result**: 
- Tabel master approval ditampilkan dengan semua kolom
- Data master approval ditampilkan dengan benar
- Pagination berfungsi
- Header tabel berwarna biru (#014689)
- Tombol Tambah disabled jika sudah ada approval aktif

### TC-APP-002: Search Master Approval
**Deskripsi**: Mencari data master approval berdasarkan keyword
**Precondition**: Terdapat data master approval di database
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Input keyword di search box (minimal 3 karakter)
3. Tekan Enter atau tunggu auto-search

**Expected Result**: 
- Data master approval yang sesuai keyword ditampilkan
- Data yang tidak sesuai tidak ditampilkan

### TC-APP-003: Add New Master Approval
**Deskripsi**: Menambahkan master approval baru
**Precondition**: 
- User memiliki akses untuk menambah master approval
- Tidak ada master approval yang sedang aktif
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Klik tombol "Tambah" (pastikan tidak disabled)
3. Isi semua form data approval dengan data valid
4. Tambahkan approver jika diperlukan
5. Klik tombol Save

**Expected Result**: 
- Form/page add master approval ditampilkan
- Data master approval baru tersimpan
- Muncul notifikasi success
- Redirect ke halaman list master approval
- Data baru muncul di tabel
- Tombol Tambah menjadi disabled

### TC-APP-004: Edit Master Approval
**Deskripsi**: Mengubah data master approval yang sudah ada
**Precondition**: Terdapat data master approval di database
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Klik icon edit pada salah satu data master approval
3. Ubah data approval
4. Update approver jika diperlukan
5. Klik tombol Save

**Expected Result**: 
- Form edit master approval ditampilkan dengan data existing
- Data master approval berhasil diupdate
- Muncul notifikasi success
- Data terupdate di tabel list

### TC-APP-005: View Master Approval Detail
**Deskripsi**: Melihat detail master approval termasuk list approver
**Precondition**: Terdapat data master approval dengan approver di database
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Klik salah satu data master approval untuk view detail
3. Observe data yang ditampilkan

**Expected Result**: 
- Detail master approval ditampilkan
- List approver ditampilkan dengan urutan level
- Semua informasi lengkap dan akurat

### TC-APP-006: Add Approver to Master Approval
**Deskripsi**: Menambahkan approver ke master approval
**Precondition**: User sedang di halaman add/edit master approval
**Test Steps**:
1. Di form master approval, klik tombol Tambah Approver
2. Pilih user approver
3. Set level approval
4. Klik Save/Add

**Expected Result**: 
- Form/modal add approver muncul
- Approver ditambahkan ke list
- Level approval terurut dengan benar
- Tombol delete approver muncul

### TC-APP-007: Remove Approver from Master Approval
**Deskripsi**: Menghapus approver dari master approval
**Precondition**: Terdapat approver di list master approval
**Test Steps**:
1. Di form master approval, klik icon delete pada approver
2. Confirm deletion jika ada

**Expected Result**: 
- Approver dihapus dari list
- List approver terupdate
- Urutan level tetap konsisten

### TC-APP-008: Pagination Master Approval
**Deskripsi**: Navigasi antar halaman menggunakan pagination
**Precondition**: Terdapat data master approval lebih dari 1 halaman
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Klik tombol next/previous atau nomor halaman
3. Observe data yang ditampilkan

**Expected Result**: 
- Data halaman berikutnya/sebelumnya ditampilkan
- Pagination styling konsisten
- Icon chevron berfungsi dengan benar

### TC-APP-009: Sort Data Master Approval
**Deskripsi**: Mengurutkan data berdasarkan kolom
**Precondition**: Terdapat data master approval di database
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Klik header kolom untuk sorting
3. Observe urutan data

**Expected Result**: 
- Data diurutkan ascending/descending
- Icon sorting FontAwesome muncul dengan benar

### TC-APP-010: Disable Add Button When Active Approval Exists
**Deskripsi**: Tombol Tambah disabled ketika sudah ada approval aktif
**Precondition**: Terdapat master approval dengan status aktif
**Test Steps**:
1. Akses halaman CMS Master Approval
2. Observe tombol Tambah

**Expected Result**: 
- Tombol Tambah dalam keadaan disabled
- Cursor menunjukkan not-allowed
- Tidak bisa diklik

---

## Global Test Cases (Berlaku untuk Semua Modul CMS)

### TC-GLOBAL-001: DataTable Header Styling
**Deskripsi**: Memverifikasi styling header table
**Test Steps**:
1. Akses salah satu halaman CMS
2. Observe header table

**Expected Result**: 
- Header table berwarna biru (#014689)
- Text berwarna putih
- Border radius di pojok atas

### TC-GLOBAL-002: DataTable Sorting Icons
**Deskripsi**: Memverifikasi icon sorting menggunakan FontAwesome
**Test Steps**:
1. Akses salah satu halaman CMS dengan sortable column
2. Observe icon sorting
3. Klik header untuk sort

**Expected Result**: 
- Icon default: fa-sort (opacity 0.3)
- Icon ascending: fa-sort-up (opacity 1)
- Icon descending: fa-sort-down (opacity 1)
- Icon di posisi kanan header
- Icon default DataTables (:after) tidak muncul

### TC-GLOBAL-003: Pagination Styling
**Deskripsi**: Memverifikasi styling pagination
**Test Steps**:
1. Akses salah satu halaman CMS dengan data lebih dari 1 halaman
2. Observe pagination

**Expected Result**: 
- Pagination di posisi tengah
- Current page: background #10569C, border-radius 10px
- Inactive page: background transparent
- Icon chevron left/right menggunakan FontAwesome
- Icon aktif: warna theme (#014689)
- Icon disabled: warna abu-abu (#ccc)
- First/Last button hidden
- Padding button: 5px

### TC-GLOBAL-004: Select2 Dropdown
**Deskripsi**: Memverifikasi fungsi Select2 pada dropdown
**Test Steps**:
1. Akses halaman CMS dengan dropdown (entries per page)
2. Klik dropdown
3. Pilih salah satu option

**Expected Result**: 
- Dropdown menggunakan Select2
- Style konsisten dengan design
- Overflow visible (tidak hidden)
- Selection berfungsi dengan baik

### TC-GLOBAL-005: Search Functionality
**Deskripsi**: Memverifikasi fungsi search box
**Test Steps**:
1. Akses salah satu halaman CMS
2. Input keyword di search box (< 3 karakter)
3. Input keyword (>= 3 karakter)
4. Clear search box

**Expected Result**: 
- Search tidak trigger jika < 3 karakter
- Search trigger otomatis jika >= 3 karakter
- Data filter sesuai keyword
- Clear search menampilkan semua data

### TC-GLOBAL-006: Add Button Styling
**Deskripsi**: Memverifikasi styling tombol Tambah
**Test Steps**:
1. Akses salah satu halaman CMS
2. Observe tombol Tambah

**Expected Result**: 
- Tombol di sebelah kiri search box
- Background: #014689
- Color: white
- Icon plus menggunakan FontAwesome (fa-plus)
- Border-radius: 5px
- Height: 40px

### TC-GLOBAL-007: Responsive Layout
**Deskripsi**: Memverifikasi responsive layout pada berbagai ukuran layar
**Test Steps**:
1. Akses halaman CMS
2. Resize browser ke ukuran mobile/tablet/desktop
3. Observe layout

**Expected Result**: 
- Layout adjust sesuai ukuran layar
- Table responsive (horizontal scroll jika perlu)
- Column layout adjust (col-md-1 dan col-md-11)
- Semua element tetap accessible

### TC-GLOBAL-008: Form Inline Display
**Deskripsi**: Memverifikasi form inline display flex
**Test Steps**:
1. Akses halaman dengan form inline
2. Observe layout form

**Expected Result**: 
- Form inline menggunakan display: flex
- Element dalam form tersusun horizontal
- Spacing konsisten

---

## Notes:
- Semua test case di atas adalah **positive test cases**
- Test dilakukan dengan data valid dan kondisi normal
- Expected result harus sesuai dengan requirement dan design system
- Color theme yang digunakan: #014689 (primary), #10569C (active state)
- Icon menggunakan FontAwesome 6.4.0
- Pagination menggunakan style custom sesuai design banner

## Test Environment:
- Browser: Chrome/Firefox/Safari latest version
- Resolution: 1920x1080 (desktop), 768x1024 (tablet), 375x667 (mobile)
- Liferay DXP 7.4.13.u92

## Test Data Requirements:
- Minimal 15 data per module untuk test pagination
- Data dengan berbagai variasi untuk test sorting
- Data dengan keyword unik untuk test search
