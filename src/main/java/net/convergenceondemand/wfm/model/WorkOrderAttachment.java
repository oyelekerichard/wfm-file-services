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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import net.convergenceondemand.wfm.util.DateTimeUtil;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "work_order_attachments")
@NamedQueries({
    @NamedQuery(name = "WorkOrderAttachment.findAll", query = "SELECT w FROM WorkOrderAttachment w")
    , @NamedQuery(name = "WorkOrderAttachment.findById", query = "SELECT w FROM WorkOrderAttachment w WHERE w.id = :id")
    , @NamedQuery(name = "WorkOrderAttachment.findByToken", query = "SELECT w FROM WorkOrderAttachment w WHERE w.token = :token")
    , @NamedQuery(name = "WorkOrderAttachment.findByOwnerId", query = "SELECT w FROM WorkOrderAttachment w WHERE w.ownerId = :ownerId")
    , @NamedQuery(name = "WorkOrderAttachment.findByFilename", query = "SELECT w FROM WorkOrderAttachment w WHERE w.filename = :filename")
    , @NamedQuery(name = "WorkOrderAttachment.findByDescription", query = "SELECT w FROM WorkOrderAttachment w WHERE w.description = :description")
    , @NamedQuery(name = "WorkOrderAttachment.findByCreateTime", query = "SELECT w FROM WorkOrderAttachment w WHERE w.createTime = :createTime")
    , @NamedQuery(name = "WorkOrderAttachment.findByCreatedBy", query = "SELECT w FROM WorkOrderAttachment w WHERE w.createdBy = :createdBy")
    , @NamedQuery(name = "WorkOrderAttachment.findByUpdateTime", query = "SELECT w FROM WorkOrderAttachment w WHERE w.updateTime = :updateTime")
    , @NamedQuery(name = "WorkOrderAttachment.findByUpdatedBy", query = "SELECT w FROM WorkOrderAttachment w WHERE w.updatedBy = :updatedBy")
    , @NamedQuery(name = "WorkOrderAttachment.findByIsActive", query = "SELECT w FROM WorkOrderAttachment w WHERE w.isActive = :isActive")})
public class WorkOrderAttachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "owner_id")
    private int ownerId;
    @JoinColumn(
            name = "work_order_id",
            referencedColumnName = "id",
            nullable = false
    )
    @ManyToOne(optional = false)
    private WorkOrder workOrderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "filename")
    private String filename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_by")
    private int createdBy;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;

    public WorkOrderAttachment() {
    }

    public WorkOrderAttachment(Integer id) {
        this.id = id;
    }

    public WorkOrderAttachment(Integer id, String token, int ownerId, String filename, String description, Date createTime, int createdBy, int isActive) {
        this.id = id;
        this.token = token;
        this.ownerId = ownerId;
        this.filename = filename;
        this.description = description;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.isActive = isActive;
    }

    public WorkOrderAttachment(int workOrderId, String filename, String description, int createdBy, String token) {
        this.workOrderId = new WorkOrder(workOrderId);
        this.token = token;
        this.ownerId = 1;
        this.filename = filename;
        this.description = description;
        this.createTime = DateTimeUtil.getCurrentDate();
        this.createdBy = createdBy;
        this.updateTime = DateTimeUtil.getCurrentDate();
        this.updatedBy = this.createdBy;
        this.isActive = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkOrder getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(WorkOrder workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
        if (!(object instanceof WorkOrderAttachment)) {
            return false;
        }
        WorkOrderAttachment other = (WorkOrderAttachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.convergenceondemand.workforce.domainobject.WorkOrderAttachment[ id=" + id + " ]";
    }

}
