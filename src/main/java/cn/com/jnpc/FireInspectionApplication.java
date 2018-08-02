package cn.com.jnpc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class FireInspectionApplication extends SpringBootServletInitializer{
	/**
	 * 部署到tomcat时需要继承SpringBootServletInitializer，并重写configure方法
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FireInspectionApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FireInspectionApplication.class, args);
	}

	/**
	 * 注册DruidServlet
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		return servletRegistrationBean;
	}
	/**
	 * 注册DruidFilter拦截
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean duridFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<String, String>();
		//设置忽略请求
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	/**
	 * 配置DataSource
	 * @return
	 * @throws SQLException
	 */
	@Bean
	public DataSource druidDataSource() throws SQLException {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("123456");
		druidDataSource.setUrl("jdbc:mysql://localhost:3306/fire_inspection");
		druidDataSource.setMaxActive(100);
		druidDataSource.setFilters("stat,wall");
		druidDataSource.setInitialSize(10);
		return druidDataSource;
	}
}
