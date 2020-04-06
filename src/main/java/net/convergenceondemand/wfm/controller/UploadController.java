/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.convergenceondemand.wfm.UploadService;
import net.convergenceondemand.wfm.response.ApiResponse;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @GetMapping(value = "/test")
    public String uploadingPost() {
        return "Everything works. Just test that image shit!";
    }

    @PostMapping(value = "/createWorkOrderWithAttachement", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadImage(@RequestParam MultipartFile file,
            @RequestParam(name = "imageName", required = false) String imageName,
            @RequestParam(name = "moduleId", required = false) Long module) {
        try {
            return ResponseEntity.ok(uploadService.saveImage(file, imageName, module));
        } catch (DataIntegrityViolationException e) {
            System.out.println("This file already Exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, HttpStatus.FORBIDDEN.value(), "File with this name already exists"));
        } catch (Exception e) {
            System.out.println("Error uploading file " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Uploading file"));
        }
    }

    @PostMapping(value = "/createWorkOrderWithAttachements", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadImage(@RequestParam MultipartFile[] file,
            @RequestParam(name = "ticketId", required = false) int ticketId,
            @RequestParam(name = "userId", required = false) int userId) {
        try {
            return ResponseEntity.ok(uploadService.saveImages(file, ticketId, userId));
        } catch (DataIntegrityViolationException e) {
            System.out.println("This file already Exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, HttpStatus.FORBIDDEN.value(), "File with this name already exists"));
        } catch (Exception e) {
            System.out.println("Error uploading file " + file[0].getOriginalFilename());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Uploading file"));
        }
    }

    @PostMapping(value = "/downloadMeterRequest")
    public ResponseEntity downloadMeterRequest(@RequestParam(name = "email", required = true) final String email,
            @RequestParam(name = "startDate", required = true) final String startDate,
            @RequestParam(name = "endDate", required = true) final String endDate) {
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        uploadService.processMAPReports(startDate, endDate, email);
                    } catch (IOException | EmailException ex) {
                        Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();

            return new ResponseEntity("MAP Report Request Received And Is Been Processed", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            System.out.println("This file already Exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, HttpStatus.FORBIDDEN.value(), "File with this name already exists"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Uploading file"));
        }
    }
}
