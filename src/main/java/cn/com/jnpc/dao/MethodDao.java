package cn.com.jnpc.dao;

import cn.com.jnpc.entity.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/25.
 */
public interface MethodDao extends JpaRepository<Method,String>{
    @Query("select distinct m.method from Method m")
    List<String> finddistinctmethod();
}
