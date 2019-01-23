package com.product.core;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 
 * @author cyf
 * @date 2018/10/29 下午8:53:08
 */
@Configuration
public class MybatisConfig {

	@Resource
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("mappers/*.xml"));
		return bean.getObject();
	}

	// 注意此类的加载必须要在MybatisConfig之后在加载
	@Configuration
	@AutoConfigureAfter(MybatisConfig.class)
	public static class MyBatisMapperScannerConfig {

		@Bean
		public MapperScannerConfigurer mapperScannerConfig() {
			MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
			mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
			// 扫描mapper位置
			mapperScannerConfigurer.setBasePackage("com.product.entity.*.*");
			return mapperScannerConfigurer;
		}

	}

	/**
	 * 配置mybatis的分页插件pageHelper
	 * 
	 * @return
	 */
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		// 指定为MySQL数据库
		properties.setProperty("helperDialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

}