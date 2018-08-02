package cn.com.jnpc.service;

import cn.com.jnpc.dao.AttachmentDao;
import cn.com.jnpc.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/7/24.
 */
@Service
public class AttachmentService {
    @Autowired
    private AttachmentDao attachmentDao;

    public List<Attachment> findByRecordid(String recordid) {
        return attachmentDao.findByRecordid(recordid);
    }

    public void save(Attachment attachment) {
        attachmentDao.save(attachment);
    }
}
