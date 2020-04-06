/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import net.convergenceondemand.wfm.model.MeterRequestData;
import net.convergenceondemand.wfm.model.WorkOrder;
import net.convergenceondemand.wfm.response.ApiResponse;
import net.convergenceondemand.wfm.response.DefaultResponses;
import net.convergenceondemand.wfm.service.WFMService;
import net.convergenceondemand.wfm.util.SendEmail;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author adekanmbi
 */
@Service
public class UploadService {

    @Value("${file.upload.url}")
    public String UPLOAD_DIR;

    @Autowired
    private WFMService wfmService;
    @Autowired
    private SendEmail emm;

    public ApiResponse saveImage(MultipartFile file, String imageName, Long moduleId) throws IOException {
        //normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        System.out.println(UPLOAD_DIR);
        //get upload directory
        Path fileStorageLocation = Paths.get(UPLOAD_DIR);

        //resolve location string
        Path targetLocation = fileStorageLocation.resolve(fileName);

        //copy file to location and override any existing file in the dir
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return DefaultResponses.response200("Successfully Uploaded Image", null);
    }

    public ApiResponse saveImages(MultipartFile[] files, int ticketId, int userId) throws IOException {
        try {
            for (MultipartFile file : files) {
                //normalize file name
                String fileName = ticketId + "_" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                System.out.println(fileName);
                System.out.println(UPLOAD_DIR);
                //get upload directory
                Path fileStorageLocation = Paths.get(UPLOAD_DIR);

                //resolve location string
                Path targetLocation = fileStorageLocation.resolve(fileName);

                //copy file to location and override any existing file in the dir
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                Integer createAttachment = wfmService.createAttachment(ticketId, fileName, "Attached Files", userId);
                System.out.println(createAttachment);
            }

            return DefaultResponses.response200("Successfully Uploaded Image", null);
        } catch (Exception exception) {
            return DefaultResponses.response500("File upload not successful!");
        }
    }

    public ApiResponse downloadImage(String fileName) throws MalformedURLException, FileNotFoundException {
        //get upload directory
        Path fileStorageLocation = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();

        //get Path to download
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        //Get Resource Url
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File " + fileName + " Not Found");
        }

        return DefaultResponses.response200("Successfully Downloaded File " + fileName, resource);
    }

    public void processMAPReports(String startDate, String endDate, String email) throws FileNotFoundException, IOException, EmailException {
        long started = System.currentTimeMillis();
        System.out.println("MAP Meter Request Data Download");
        System.out.println("Email report will be sent to :: " + email);

        SXSSFWorkbook workbook = new SXSSFWorkbook(100);

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeight((short) 200);

        CellStyle h = workbook.createCellStyle();
        Font f = workbook.createFont();
        f.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        f.setFontHeight((short) 300);
        h.setFont(f);
        h.setAlignment(CellStyle.ALIGN_CENTER);

        headerStyle.setFont(font);

        //List<WorkOrder> lwListt = wdao.getWorkOrderByParams(districts.get(i), from, to, queueTypeId, tariffs);
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("METER REQUESTS");

        Cell info0 = sheet.createRow(2).createCell(0);
        info0.setCellStyle(h);
        info0.setCellValue("EKO ELECTRICITY DISTRIBUTION COMPANY (EKEDP)");
        sheet.addMergedRegion(new CellRangeAddress(
                2, //first row (0-based)
                2, //last row  (0-based)
                0, //first column (0-based)
                13 //last column  (0-based)
        ));

        Cell info1 = sheet.createRow(3).createCell(13);
        info1.setCellValue(startDate + " - " + endDate);
        info1.setCellStyle(headerStyle);

        Row xheader = sheet.createRow(5);
        String[] header = {"DISCO", "BUSINESS UNIT", "CUSTOMER ACCOUNT NUMBER", "CUSTOMER ACCOUNT NAME",
            "HOUSE NUMBER", "ADDRESS LINE 1", "ADDRESS LINE 2", "BUS STOP",
            "LANDMARK", "LGA", "NAME OF OCCUPANT", "CONTACT TELEPHONE NO",
            "E-MAIL ADDRESS", "FEEDER", "DISTRIBUTION TRANSFORMER", "NAME OF UPRISER", "GPS COORDINATE (LAT.,LONG.)",
            "OLD METER NUMBER", "TYPE OF APPLICATION", "TYPE OF METER", "APPLICATION NUMBER",
            "TICKET ID", "QUEUE TYPE", "STATUS"};
        for (int col = 0; col < 23; col++) {
            Cell cell = xheader.createCell(col);
            cell.setCellValue(header[col]);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }
        int rownum = 6;
        System.out.println("Dates passed :: " + startDate + " End Date :: " + endDate);
        List<MeterRequestData> meterRequestData = wfmService.getMeterRequestData(startDate, endDate);
        System.out.println("Size of Data returned is ::: " + meterRequestData.size());
        System.out.println("Processing sheet........");
        Row row;
        Cell cell;
        for (MeterRequestData mrd : meterRequestData) {

            row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue("EKEDP");
            row.createCell(1).setCellValue(mrd.getBusinessUnit());
            row.createCell(2).setCellValue(mrd.getAccountNumber());
            row.createCell(3).setCellValue(mrd.getAccountName());
            row.createCell(4).setCellValue(mrd.getHouseNumber());
            row.createCell(5).setCellValue(mrd.getAddressLine1());
            row.createCell(6).setCellValue(mrd.getAddressLine2());
            row.createCell(7).setCellValue(mrd.getBusstop());
            row.createCell(8).setCellValue(mrd.getLandMark());
            row.createCell(9).setCellValue(mrd.getLgaOfOccupant());
            row.createCell(10).setCellValue(mrd.getOccupantName());
            row.createCell(11).setCellValue(mrd.getOccupantPhone());
            row.createCell(12).setCellValue(mrd.getEmailAddress());
            row.createCell(13).setCellValue(mrd.getFeeder());
            row.createCell(14).setCellValue(mrd.getDistributionTransformer());
            row.createCell(15).setCellValue(mrd.getUpriser());
            row.createCell(16).setCellValue(mrd.getGpsCoordinate());
            row.createCell(17).setCellValue(mrd.getOldMeterNumber());
            row.createCell(18).setCellValue(mrd.getTypeOfApplication());
            row.createCell(19).setCellValue(mrd.getTypeOfMeter());
            row.createCell(20).setCellValue(mrd.getApplicationNumber());
            row.createCell(21).setCellValue(mrd.getTicketId());

            if (mrd.getTicketId() != null) {
                WorkOrder workOrder = wfmService.findWorkOrderbyTicketId(Integer.parseInt(mrd.getTicketId()));
                row.createCell(23).setCellValue(workOrder.getQueueTypeId().getName());
                row.createCell(24).setCellValue(workOrder.getCurrentStatus());
            }
        }
        String localfile = "MAP_Report_" + System.currentTimeMillis() + ".xlsx";
        String report = "/var/files/email/" + localfile;
        FileOutputStream outputStream = new FileOutputStream(report);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

        System.out.println("File is " + report);
        long ended = System.currentTimeMillis();

        System.out.println("Total processing time ::: " + ((ended - started) / 1000));
        emm.sendAnEmail("a12wq_minions", "MAP Report Download", email, "Your MAP report is now ready. Please find attached the requested report", localfile, null);

    }

}
