/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osita
 */
@Entity
@Table(name = "users", catalog = "wfm_new", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    ,
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    ,
    @NamedQuery(name = "Users.findByToken", query = "SELECT u FROM Users u WHERE u.token = :token")
    ,
    @NamedQuery(name = "Users.findByOwnerId", query = "SELECT u FROM Users u WHERE u.ownerId = :ownerId")
    ,
    @NamedQuery(name = "Users.findByNewpass", query = "SELECT u FROM Users u WHERE u.newpass = :newpass")
    ,
    @NamedQuery(name = "Users.findByLevel", query = "SELECT u FROM Users u WHERE u.level = :level")
    ,
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    ,
    @NamedQuery(name = "Users.findByFirstname", query = "SELECT u FROM Users u WHERE u.firstname = :firstname")
    ,
    @NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname")
    ,
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    ,
    @NamedQuery(name = "Users.findByDepartment", query = "SELECT u FROM Users u WHERE u.department = :department")
    ,
    @NamedQuery(name = "Users.findBySalt", query = "SELECT u FROM Users u WHERE u.salt = :salt")
    ,
    @NamedQuery(name = "Users.findByRoles", query = "SELECT u FROM Users u WHERE u.roles = :roles")
    ,
    @NamedQuery(name = "Users.findByQueues", query = "SELECT u FROM Users u WHERE u.queues = :queues")
    ,
    @NamedQuery(name = "Users.findByDistricts", query = "SELECT u FROM Users u WHERE u.districts = :districts")
    ,
    @NamedQuery(name = "Users.findByTariffs", query = "SELECT u FROM Users u WHERE u.tariffs = :tariffs")
    ,
    @NamedQuery(name = "Users.findByStatuses", query = "SELECT u FROM Users u WHERE u.statuses = :statuses")
    ,
    @NamedQuery(name = "Users.findByTempPassword", query = "SELECT u FROM Users u WHERE u.tempPassword = :tempPassword")
    ,
    @NamedQuery(name = "Users.findBySentEmail", query = "SELECT u FROM Users u WHERE u.sentEmail = :sentEmail")
    ,
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone")
    ,
    @NamedQuery(name = "Users.findByLogonTime", query = "SELECT u FROM Users u WHERE u.logonTime = :logonTime")
    ,
    @NamedQuery(name = "Users.findByHasConfirmed", query = "SELECT u FROM Users u WHERE u.hasConfirmed = :hasConfirmed")
    ,
    @NamedQuery(name = "Users.findByIsEngineer", query = "SELECT u FROM Users u WHERE u.isEngineer = :isEngineer")
    ,
    @NamedQuery(name = "Users.findByCreateTime", query = "SELECT u FROM Users u WHERE u.createTime = :createTime")
    ,
    @NamedQuery(name = "Users.findByUpdateTime", query = "SELECT u FROM Users u WHERE u.updateTime = :updateTime")
    ,
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "token", nullable = false, length = 30)
    private String token;
    @Basic(optional = false)
    @Column(name = "owner_id", nullable = false)
    private int ownerId;
    @Basic(optional = false)
    @Column(name = "newpass", nullable = false)
    private int newpass;
    @Basic(optional = false)
    @Column(name = "level", nullable = false)
    private int level;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;
    @Column(name = "password", length = 200)
    private String password;
    @Basic(optional = false)
    @Column(name = "department", nullable = false, length = 100)
    private String department;
    @Basic(optional = false)
    @Column(name = "salt", nullable = false, length = 100)
    private String salt;
    @Basic(optional = false)
    @Column(name = "roles", nullable = false, length = 200)
    private String roles;
    @Basic(optional = false)
    @Column(name = "queues", nullable = false, length = 200)
    private String queues;
    @Basic(optional = false)
    @Column(name = "districts", nullable = false, length = 200)
    private String districts;
    @Column(name = "tariffs", length = 200)
    private String tariffs;
    @Column(name = "statuses", length = 300)
    private String statuses;
    @Basic(optional = false)
    @Column(name = "temp_password", nullable = false, length = 20)
    private String tempPassword;
    @Basic(optional = false)
    @Column(name = "sent_email", nullable = false)
    private int sentEmail;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;
    @Column(name = "logon_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logonTime;
    @Column(name = "has_confirmed")
    private Integer hasConfirmed;
    @Column(name = "is_engineer")
    private Integer isEngineer;
    @Basic(optional = false)
    @Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Basic(optional = false)
    @Column(name = "is_active", nullable = false)
    private int isActive;
    @OneToMany(mappedBy = "updatedBy")
    private List<WorkOrderStatusQueueType> workOrderStatusQueueTypeList1;
    @OneToMany(mappedBy = "createdBy")
    private List<WorkOrder> workOrderList;
    @OneToMany(mappedBy = "updatedBy")
    private List<WorkOrder> workOrderList1;
    @OneToMany(mappedBy = "assignedBy")
    private List<WorkOrder> workOrderList2;
    @OneToMany(mappedBy = "closedBy")
    private List<WorkOrder> workOrderList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<EscalationSettings> escalationSettingsList;
    @OneToMany(mappedBy = "updatedBy")
    private List<EscalationSettings> escalationSettingsList1;
    @OneToMany(mappedBy = "createdBy")
    private List<Users> usersList;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne
    private Users createdBy;
    @OneToMany(mappedBy = "updatedBy")
    private List<Users> usersList1;
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @ManyToOne
    private Users updatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<WorkOrderStatus> workOrderStatusList;
    @OneToMany(mappedBy = "updatedBy")
    private List<WorkOrderStatus> workOrderStatusList1;
    @Column(name = "staff_code")
    private String staffCode;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String token, int ownerId, int newpass, int level, String email, String firstname, String lastname, String department, String salt, String roles, String queues, String districts, String tempPassword, int sentEmail, String phone, Date createTime, int isActive) {
        this.id = id;
        this.token = token;
        this.ownerId = ownerId;
        this.newpass = newpass;
        this.level = level;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.salt = salt;
        this.roles = roles;
        this.queues = queues;
        this.districts = districts;
        this.tempPassword = tempPassword;
        this.sentEmail = sentEmail;
        this.phone = phone;
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

    public int getNewpass() {
        return newpass;
    }

    public void setNewpass(int newpass) {
        this.newpass = newpass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getQueues() {
        return queues;
    }

    public void setQueues(String queues) {
        this.queues = queues;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getTariffs() {
        return tariffs;
    }

    public void setTariffs(String tariffs) {
        this.tariffs = tariffs;
    }

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public int getSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(int sentEmail) {
        this.sentEmail = sentEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public Integer getHasConfirmed() {
        return hasConfirmed;
    }

    public void setHasConfirmed(Integer hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }

    public Integer getIsEngineer() {
        return isEngineer;
    }

    public void setIsEngineer(Integer isEngineer) {
        this.isEngineer = isEngineer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<WorkOrderStatusQueueType> getWorkOrderStatusQueueTypeList1() {
        return workOrderStatusQueueTypeList1;
    }

    public void setWorkOrderStatusQueueTypeList1(List<WorkOrderStatusQueueType> workOrderStatusQueueTypeList1) {
        this.workOrderStatusQueueTypeList1 = workOrderStatusQueueTypeList1;
    }

    public List<WorkOrder> getWorkOrderList() {
        return workOrderList;
    }

    public void setWorkOrderList(List<WorkOrder> workOrderList) {
        this.workOrderList = workOrderList;
    }

    public List<WorkOrder> getWorkOrderList1() {
        return workOrderList1;
    }

    public void setWorkOrderList1(List<WorkOrder> workOrderList1) {
        this.workOrderList1 = workOrderList1;
    }

    public List<WorkOrder> getWorkOrderList2() {
        return workOrderList2;
    }

    public void setWorkOrderList2(List<WorkOrder> workOrderList2) {
        this.workOrderList2 = workOrderList2;
    }

    public List<WorkOrder> getWorkOrderList3() {
        return workOrderList3;
    }

    public void setWorkOrderList3(List<WorkOrder> workOrderList3) {
        this.workOrderList3 = workOrderList3;
    }

    public List<EscalationSettings> getEscalationSettingsList() {
        return escalationSettingsList;
    }

    public void setEscalationSettingsList(List<EscalationSettings> escalationSettingsList) {
        this.escalationSettingsList = escalationSettingsList;
    }

    public List<EscalationSettings> getEscalationSettingsList1() {
        return escalationSettingsList1;
    }

    public void setEscalationSettingsList1(List<EscalationSettings> escalationSettingsList1) {
        this.escalationSettingsList1 = escalationSettingsList1;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public List<Users> getUsersList1() {
        return usersList1;
    }

    public void setUsersList1(List<Users> usersList1) {
        this.usersList1 = usersList1;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<WorkOrderStatus> getWorkOrderStatusList() {
        return workOrderStatusList;
    }

    public void setWorkOrderStatusList(List<WorkOrderStatus> workOrderStatusList) {
        this.workOrderStatusList = workOrderStatusList;
    }

    public List<WorkOrderStatus> getWorkOrderStatusList1() {
        return workOrderStatusList1;
    }

    public void setWorkOrderStatusList1(List<WorkOrderStatus> workOrderStatusList1) {
        this.workOrderStatusList1 = workOrderStatusList1;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

}
