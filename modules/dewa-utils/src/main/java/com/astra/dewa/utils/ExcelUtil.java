package com.astra.dewa.utils;

import com.astra.dewa.model.TrainingAgendaParticipant;
import com.astra.dewa.service.DealerLocalService;
import com.astra.dewa.service.TrainingAgendaParticipantLocalServiceUtil;
import com.astra.dewa.utils.dto.TrainingAgendaParticipantDto;
import com.astra.dewa.utils.dto.TrainingParticipantDto;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {
    public ExcelUtil() throws IOException {}

    public static String convertPendaftaranPeserta(String filePath, DealerLocalService _dealerLocalService) throws IOException {
        File file = new java.io.File(filePath);
        FileInputStream excelFile = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String testd = sheet.getRow(1).getCell(1).getStringCellValue();
        Row lastRow = null;
        int columns = 6;

        // column 1 no cell a
        // column 2 email cell b
        // column 3 nama peserta cell c
        // column 4 nomor handphone cell d
        // column 5 nama kode dealer cell e
        // column 6 nama jabatan cell f

        int lastRowNum = sheet.getLastRowNum();
        int j = 0;
        DataFormatter dataFormatter = new DataFormatter();

        String email = "";
        String namaPeserta = "";
        String nomorHandphone = "";
        String kodeDealer = "";
        String jabatan = "";
        XSSFCell ReadInCellValue;
        for (int i = 1; i <= lastRowNum - 1; i++) {
            try {
                email = dataFormatter.formatCellValue(sheet.getRow(i).getCell(1));
            } catch (NullPointerException e) {
                email = "";
            }
            try {
                namaPeserta = dataFormatter.formatCellValue(sheet.getRow(i).getCell(2));
                nomorHandphone = dataFormatter.formatCellValue(sheet.getRow(i).getCell(3));
                kodeDealer = dataFormatter.formatCellValue(sheet.getRow(i).getCell(4));
                jabatan = dataFormatter.formatCellValue(sheet.getRow(i).getCell(5));
            } catch (NullPointerException e) {
                // TODO
            }

            if (!email.isEmpty()) {
                try {
                    int count = TrainingAgendaParticipantLocalServiceUtil.getTrainingAgendaParticipantsCount();
                    TrainingAgendaParticipant trainingAgendaParticipant = TrainingAgendaParticipantLocalServiceUtil.createTrainingAgendaParticipant(count);
                    trainingAgendaParticipant.setEmail(email);
                    trainingAgendaParticipant.setFullName(namaPeserta);
                    trainingAgendaParticipant.setPhone(nomorHandphone);
                    DynamicQuery dynamic = _dealerLocalService.dynamicQuery();
                    dynamic.add(RestrictionsFactoryUtil.gt("Code", kodeDealer));
                    List<TrainingAgendaParticipant> trainingAgendaParticipants = TrainingAgendaParticipantLocalServiceUtil.dynamicQuery(dynamic);
                    if (!trainingAgendaParticipants.isEmpty()) {
                        trainingAgendaParticipant.setDealerId(trainingAgendaParticipants.get(0).getId());
                        TrainingAgendaParticipantLocalServiceUtil.addTrainingAgendaParticipant(trainingAgendaParticipant);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "OK";
    }

    public static List<String> checkValidationPendaftaranPesertaPelatihan(File file) throws IOException {
        List<String> result = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // column 1 no cell a
        // column 2 email cell b
        // column 3 nama peserta cell c
        // column 4 nomor handphone cell d
        // column 5 nama kode dealer cell e
        // column 6 nama jabatan cell f

        int lastRowNum = sheet.getLastRowNum();
        DataFormatter dataFormatter = new DataFormatter();

        String email = "";
        String namaPeserta = "";
        String nomorHandphone = "";
        String namaDealer = "";
        String jabatan = "";

        for (int i = 1; i <= lastRowNum; i++) {
            try {
                XSSFRow row = sheet.getRow(i);
                if (Validator.isNull(row)) {
                    continue;
                }

                if (row.getRowNum() > 0 && isValidRow(row, 6)) {
                    email = dataFormatter.formatCellValue(sheet.getRow(i).getCell(1));
                    namaPeserta = dataFormatter.formatCellValue(sheet.getRow(i).getCell(2));
                    nomorHandphone = dataFormatter.formatCellValue(sheet.getRow(i).getCell(3));
                    namaDealer = dataFormatter.formatCellValue(sheet.getRow(i).getCell(4));
                    jabatan = dataFormatter.formatCellValue(sheet.getRow(i).getCell(5));

                    // Pattern emailPattern =
                    // Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
                    Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
                    Matcher emailMatcher = emailPattern.matcher(email);

                    // Pattern pattern = Pattern.compile("^[0-9]+$");
                    // Matcher matcher = pattern.matcher(nomorHandphone);

                    // Check Email
                    if (Validator.isNull(email)) {
                        result.add("Row " + i + " column Email KOSONG");
                    } else if (!emailMatcher.matches()) {
                        result.add("Row " + i + " column Email tidak valid");
                    }

                    if (Validator.isNull(namaPeserta)) {
                        result.add("Row " + i + " column Nama Peserta KOSONG");
                    } else if (!InputValidationUtils.isBasicCharacter(namaPeserta) || namaPeserta.length() > 100) {
                        result.add("Row " + i + " column Nama Peserta tidak valid");
                    }

                    if (Validator.isNull(nomorHandphone)) {
                        result.add("Row " + i + " column Nomor Handphone KOSONG");
                    } else if (!InputValidationUtils.isNumberOnly(nomorHandphone)) {
                        result.add("Row " + i + " column Nomor Handphone mengandung huruf");
                    } else if (nomorHandphone.length() < 9 || nomorHandphone.length() > 14) {
                        result.add("Row " + i + " Nomor Handphone tidak valid");
                    }

                    System.out.println("=========================================");
                    System.out.println("email : " + email);
                    System.out.println("namaPeserta : " + namaPeserta);
                    System.out.println("nomorHandphone : " + nomorHandphone);
                    System.out.println("namaDealer : " + namaDealer);
                    System.out.println("jabatan : " + jabatan);
                    System.out.println("=========================================");
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
        System.out.println("result ===> " + result.toString());
        return result;
    }

    public static List<TrainingAgendaParticipantDto> convertPendaftaranPesertaPelatihan(
            File file,
            Integer trainingAgendaId,
            Integer dealerId,
            String user
    ) throws IOException {
        List<TrainingAgendaParticipantDto> tapDTO = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0);
        // column 1 no cell a
        // column 2 email cell b
        // column 3 nama peserta cell c
        // column 4 nomor handphone cell d
        // column 5 nama kode dealer cell e
        // column 6 nama jabatan cell f

        int lastRowNum = sheet.getLastRowNum();
        DataFormatter dataFormatter = new DataFormatter();

        String email = "";
        String namaPeserta = "";
        String nomorHandphone = "";

        for (int i = 1; i <= lastRowNum; i++) {
            try {
                XSSFRow row = sheet.getRow(i);
                if (Validator.isNull(row)) {
                    continue;
                }

                if (row.getRowNum() > 0 && isValidRow(row, 6)) {
                    email = dataFormatter.formatCellValue(sheet.getRow(i).getCell(1));
                    namaPeserta = dataFormatter.formatCellValue(sheet.getRow(i).getCell(2));
                    nomorHandphone = dataFormatter.formatCellValue(sheet.getRow(i).getCell(3));

                    TrainingAgendaParticipant tap = TrainingAgendaParticipantLocalServiceUtil.createTrainingAgendaParticipant(0);
                    tap.setFullName(namaPeserta);
                    tap.setEmail(email);
                    tap.setPhone(nomorHandphone);
                    tap.setTrainingAgendaId(trainingAgendaId);
                    tap.setDealerId(dealerId);
                    tap.setCreatedDate(new Date());
                    tap.setCreatedBy(user);
                    tap.setModifiedDate(new Date());
                    tap.setModifiedBy(user);
                    tap.setRowStatus(true);
                    tap = TrainingAgendaParticipantLocalServiceUtil.addTrainingAgendaParticipant(tap);

                    tapDTO.add(new TrainingAgendaParticipantDto(namaPeserta, email, nomorHandphone));
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
        return tapDTO;
    }

    public static List<TrainingParticipantDto> getTrainingParticipants(File file) throws IOException {
        List<TrainingParticipantDto> participants = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0);
        // column 1 no cell a
        // column 2 email cell b
        // column 3 nama peserta cell c
        // column 4 nomor handphone cell d
        // column 5 nama kode dealer cell e
        // column 6 nama jabatan cell f

        int lastRowNum = sheet.getLastRowNum();
        DataFormatter dataFormatter = new DataFormatter();

        String email = "";
        String namaPeserta = "";
        String nomorHandphone = "";
        String namaDealer = "";
        String jabatan = "";

        for (int i = 1; i <= lastRowNum; i++) {
            try {
                XSSFRow row = sheet.getRow(i);
                if (Validator.isNull(row)) {
                    continue;
                }
                if (row.getRowNum() > 0 && isValidRow(row, 6)) {
                    email = dataFormatter.formatCellValue(sheet.getRow(i).getCell(1));
                    namaPeserta = dataFormatter.formatCellValue(sheet.getRow(i).getCell(2));
                    nomorHandphone = dataFormatter.formatCellValue(sheet.getRow(i).getCell(3));
                    namaDealer = dataFormatter.formatCellValue(sheet.getRow(i).getCell(4));
                    jabatan = dataFormatter.formatCellValue(sheet.getRow(i).getCell(5));

                    Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
                    Matcher emailMatcher = emailPattern.matcher(email);

                    Pattern pattern = Pattern.compile("^[0-9]+$");
                    Matcher matcher = pattern.matcher(nomorHandphone);

                    TrainingParticipantDto participant = new TrainingParticipantDto();
                    participant.setNo(i);
                    if (email.isEmpty()) {
                        participant.setEmail("-");
                    } else if (!emailMatcher.matches()) {
                        participant.setEmail("-");
                    } else {
                        participant.setEmail(email);
                    }
                    if (namaPeserta.isEmpty()) {
                        participant.setFullName("-");
                    } else {
                        participant.setFullName(namaPeserta);
                    }
                    if (nomorHandphone.isEmpty()) {
                        participant.setPhone("-");
                    } else if (!matcher.matches()) {
                        participant.setPhone("-");
                    } else {
                        participant.setPhone(nomorHandphone);
                    }
                    participant.setDealerName(namaDealer);
                    participant.setJabatan(jabatan);
                    participants.add(participant);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return participants;
    }

    private static boolean isValidRow(Row row, int cellSize) {
        int cellIndex = 0;
        boolean isValid = false;
        for (Cell cell : row) {
            if (cellIndex >= cellSize) {
                break;
            }
            // Check if cell is not blank (BLANK = 3 in old POI, CellType.BLANK in new)
            if (cell != null) {
                try {
                    // Try to get cell value, if blank will be empty
                    String cellValue = cell.toString();
                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        isValid = true;
                    }
                } catch (Exception e) {
                    // If error, consider as not blank
                    isValid = true;
                }
            }
            cellIndex++;
        }
        return isValid;
    }
}
