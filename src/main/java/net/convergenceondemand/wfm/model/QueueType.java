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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "queue_type")
public class QueueType implements Serializable {

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
    @Size(max = 40)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "description")
    private String description;
    @Size(max = 400)
    @Column(name = "channel")
    private String channel;
    @Size(max = 400)
    @Column(name = "resource_type")
    private String resourceType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "needs_auth")
    private short needsAuth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auto_assign_to_resource")
    private byte autoAssignToResource;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_duplicate")
    private byte checkDuplicate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_duplicate_delay")
    private int checkDuplicateDelay;
    @Basic(optional = false)
    @NotNull
    @Column(name = "show_amount_on_batch_assign")
    private byte showAmountOnBatchAssign;
    @Column(name = "channels_needs_auth")
    private byte channelsNeedsAuth;
    @ManyToOne(optional = false)
    @JoinColumn(name = "queue_id")
    private Queue queue;

    public QueueType() {
    }

    public QueueType(Integer id) {
        this.id = id;
    }

    public QueueType(Integer id, String token, int ownerId, String description, byte needsAuth, Date createTime, int isActive, byte autoAssignToResource, byte checkDuplicate, int checkDuplicateDelay, byte showAmountOnBatchAssign) {
        this.id = id;
        this.token = token;
        this.ownerId = ownerId;
        this.description = description;
        this.needsAuth = needsAuth;
        this.createTime = createTime;
        this.isActive = isActive;
        this.autoAssignToResource = autoAssignToResource;
        this.checkDuplicate = checkDuplicate;
        this.checkDuplicateDelay = checkDuplicateDelay;
        this.showAmountOnBatchAssign = showAmountOnBatchAssign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public short getNeedsAuth() {
        return needsAuth;
    }

    public void setNeedsAuth(short needsAuth) {
        this.needsAuth = needsAuth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getAutoAssignToResource() {
        return autoAssignToResource;
    }

    public void setAutoAssignToResource(byte autoAssignToResource) {
        this.autoAssignToResource = autoAssignToResource;
    }

    public byte getCheckDuplicate() {
        return checkDuplicate;
    }

    public void setCheckDuplicate(byte checkDuplicate) {
        this.checkDuplicate = checkDuplicate;
    }

    public int getCheckDuplicateDelay() {
        return checkDuplicateDelay;
    }

    public void setCheckDuplicateDelay(int checkDuplicateDelay) {
        this.checkDuplicateDelay = checkDuplicateDelay;
    }

    public byte getShowAmountOnBatchAssign() {
        return showAmountOnBatchAssign;
    }

    public void setShowAmountOnBatchAssign(byte showAmountOnBatchAssign) {
        this.showAmountOnBatchAssign = showAmountOnBatchAssign;
    }

    public byte getChannelsNeedsAuth() {
        return channelsNeedsAuth;
    }

    public void setChannelsNeedsAuth(byte channelsNeedsAuth) {
        this.channelsNeedsAuth = channelsNeedsAuth;
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
        if (!(object instanceof QueueType)) {
            return false;
        }
        QueueType other = (QueueType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "net.convergenceondemand.workforce.domainobject.QueueType[ id=" + id + " ]";
    }

}
