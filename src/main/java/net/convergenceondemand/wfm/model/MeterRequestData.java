/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author adekanmbi
 */
@Entity
@Table(name = "meter_request_data")
public class MeterRequestData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "disco")
    private String disco;
    @Column(name = "business_unit")
    private String businessUnit;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "bus_stop")
    private String busstop;
    @Column(name = "land_mark")
    private String landMark;
    @Column(name = "lga_of_occupant")
    private String lgaOfOccupant;
    @Column(name = "name_of_occupant")
    private String occupantName;
    @Column(name = "occupant_phone")
    private String occupantPhone;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "distribution_transformer")
    private String distributionTransformer;
    @Column(name = "feeder")
    private String feeder;
    @Column(name = "upriser")
    private String upriser;
    @Column(name = "gps_coordinate")
    private String gpsCoordinate;
    @Column(name = "old_meter_number")
    private String oldMeterNumber;
    @Column(name = "type_of_application")
    private String typeOfApplication;
    @Column(name = "type_of_meter")
    private String typeOfMeter;

    @Column(name = "queueType")
    private String queueType;
    @Column(name = "ticket_status")
    private String ticketStatus;

    @Basic(optional = false)
    @NotNull
    @Column(name = "application_number")
    private String applicationNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticket_id")
    private String ticketId;
    @Column(name = "submission_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submissionDate;

    public MeterRequestData() {
        this.submissionDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBusstop() {
        return busstop;
    }

    public void setBusstop(String busstop) {
        this.busstop = busstop;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getLgaOfOccupant() {
        return lgaOfOccupant;
    }

    public void setLgaOfOccupant(String lgaOfOccupant) {
        this.lgaOfOccupant = lgaOfOccupant;
    }

    public String getOccupantPhone() {
        return occupantPhone;
    }

    public void setOccupantPhone(String occupantPhone) {
        this.occupantPhone = occupantPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDistributionTransformer() {
        return distributionTransformer;
    }

    public void setDistributionTransformer(String distributionTransformer) {
        this.distributionTransformer = distributionTransformer;
    }

    public String getFeeder() {
        return feeder;
    }

    public void setFeeder(String feeder) {
        this.feeder = feeder;
    }

    public String getUpriser() {
        return upriser;
    }

    public void setUpriser(String upriser) {
        this.upriser = upriser;
    }

    public String getGpsCoordinate() {
        return gpsCoordinate;
    }

    public void setGpsCoordinate(String gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public String getOldMeterNumber() {
        return oldMeterNumber;
    }

    public void setOldMeterNumber(String oldMeterNumber) {
        this.oldMeterNumber = oldMeterNumber;
    }

    public String getTypeOfApplication() {
        return typeOfApplication;
    }

    public void setTypeOfApplication(String typeOfApplication) {
        this.typeOfApplication = typeOfApplication;
    }

    public String getTypeOfMeter() {
        return typeOfMeter;
    }

    public void setTypeOfMeter(String typeOfMeter) {
        this.typeOfMeter = typeOfMeter;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeterRequestData)) {
            return false;
        }
        MeterRequestData other = (MeterRequestData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
