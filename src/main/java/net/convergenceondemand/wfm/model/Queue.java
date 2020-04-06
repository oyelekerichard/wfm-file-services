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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "queue")
@NamedQueries({
    @NamedQuery(name = "Queue.findAll", query = "SELECT q FROM Queue q")
    , @NamedQuery(name = "Queue.findById", query = "SELECT q FROM Queue q WHERE q.id = :id")
    , @NamedQuery(name = "Queue.findByToken", query = "SELECT q FROM Queue q WHERE q.token = :token")
    , @NamedQuery(name = "Queue.findByOwnerId", query = "SELECT q FROM Queue q WHERE q.ownerId = :ownerId")
    , @NamedQuery(name = "Queue.findByName", query = "SELECT q FROM Queue q WHERE q.name = :name")
    , @NamedQuery(name = "Queue.findByDescription", query = "SELECT q FROM Queue q WHERE q.description = :description")
    , @NamedQuery(name = "Queue.findByChannel", query = "SELECT q FROM Queue q WHERE q.channel = :channel")
    , @NamedQuery(name = "Queue.findByResourceType", query = "SELECT q FROM Queue q WHERE q.resourceType = :resourceType")
    , @NamedQuery(name = "Queue.findByCreateTime", query = "SELECT q FROM Queue q WHERE q.createTime = :createTime")
    , @NamedQuery(name = "Queue.findByIsActive", query = "SELECT q FROM Queue q WHERE q.isActive = :isActive")
    , @NamedQuery(name = "Queue.findByUpdateTime", query = "SELECT q FROM Queue q WHERE q.updateTime = :updateTime")})
public class Queue implements Serializable {

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

    public Queue() {
    }

    public Queue(Integer id) {
        this.id = id;
    }

    public Queue(Integer id, String token, int ownerId, String description, Date createTime, int isActive) {
        this.id = id;
        this.token = token;
        this.ownerId = ownerId;
        this.description = description;
        this.createTime = createTime;
        this.isActive = isActive;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Queue)) {
            return false;
        }
        Queue other = (Queue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.convergenceondemand.workforce.domainobject.Queue[ id=" + id + " ]";
    }

}
