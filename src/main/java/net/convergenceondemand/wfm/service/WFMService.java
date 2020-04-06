/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.convergenceondemand.wfm.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.convergenceondemand.wfm.model.MeterRequestData;
import net.convergenceondemand.wfm.model.QueueType;
import net.convergenceondemand.wfm.model.WorkOrder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author adekanmbi
 */
@Component
@Service("accountService")
public class WFMService {

    @Autowired
    private DBAccessBean dbAccessBean;

    public Integer createAttachment(int workOrderId, String filename, String description, int createdBy) {
        EntityManager em = dbAccessBean.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            String token = RandomStringUtils.randomAlphanumeric(10).concat(String.valueOf(System.currentTimeMillis()));
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO work_order_attachments (token, owner_id, work_order_id, filename, description, create_time, created_by, is_active) ");
            sb.append("VALUES ('").append(token).append("', ").append(1).append(", (select w.id from work_order w where w.ticket_id = ").append(workOrderId).append(")");
            sb.append(", '").append(filename).append("', ").append("'Attached File'").append(", ").append("now()").append(", ").append(createdBy).append(", ").append(1).append(")");
            Query nativeQuery = em.createNativeQuery(sb.toString());
            int x = nativeQuery.executeUpdate();
            em.getTransaction().commit();
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getUniqueToken() {
        String token = RandomStringUtils.randomAlphanumeric(5).concat(String.valueOf(System.currentTimeMillis()));
        EntityManager em = dbAccessBean.getEmf().createEntityManager();
        try {
            List<String> o = em.createNativeQuery("select token from work_order_attachments where token=?1").
                    setParameter(1, token).
                    getResultList();
            if (o != null) {
                return getUniqueToken();
            } else {
                return token;
            }
        } finally {
            em.close();
        }
    }

    public List<MeterRequestData> getMeterRequestData(String startDate, String endDate) {
        EntityManager em = dbAccessBean.getEmf().createEntityManager();
        try {
            String sql = "select * from meter_request_data where submission_date between ?1 and ?2";
            Query query = em.createNativeQuery(sql, MeterRequestData.class);
            query.setParameter(1, startDate).setParameter(2, endDate);
            return (List<MeterRequestData>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WorkOrder findWorkOrderbyTicketId(int workOrderTicketId) {
        EntityManager em = dbAccessBean.getEmf().createEntityManager();
        try {
            List<WorkOrder> resultList = em.createNativeQuery("select * from work_order where ticket_id=?1 limit 1", WorkOrder.class).
                    setParameter(1, workOrderTicketId).
                    getResultList();
            return resultList.isEmpty() ? null : resultList.get(0);
        } finally {
            em.close();
        }
    }

    public QueueType getQueueTypeByQueueTypeId(Integer id) {
        EntityManager em = dbAccessBean.getEmf().createEntityManager();
        try {
            List<QueueType> resultList = em.createNativeQuery("select * from queue_type where id=?1 ", QueueType.class).
                    setParameter(1, id).
                    getResultList();

            return resultList.isEmpty() ? null : resultList.get(0);
        } finally {
            em.close();
        }
    }
}
