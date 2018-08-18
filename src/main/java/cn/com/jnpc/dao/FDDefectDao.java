package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FDDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/19.
 */
public interface FDDefectDao extends JpaRepository<FDDefect,String >{


}
