package cn.com.jnpc.dao;

import cn.com.jnpc.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/24.
 */
public interface AttachmentDao extends JpaRepository<Attachment,String>{
    @Query("select a from Attachment a where a.recordid=?1")
    List<Attachment> findByRecordid(String recordid);


}
