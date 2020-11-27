package kr.or.ksmart37.ksmart.mybatis.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Bean 생성
@Configuration
@MapperScan(basePackages = "kr.or.ksmart37.ksmart.mybatis.mapper", sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class MybatisConfig {
	
	/*@Autowired
	sqlSessionFactory mybatisSqlsessionFactory;
	아래와 같은 식이다.
	*/
	
	//컨테이너란 (ApplicationContext)> Bean을 담는 그릇 빈팩토리, 애플리케이션 컨텍스트 여기서 객체가 생성, 소멸, 주입된다..
	@Bean(name = "mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//최상위클래스는 Exception (예외처리 최상위 IOException 보다 상위이다.)
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:/mapper/**/*.xml"));
		//dto패키지 경로 가져오기
		sqlSessionFactoryBean.setTypeAliasesPackage("kr.or.ksmart37.ksmart.mybatis.dto");
		return sqlSessionFactoryBean.getObject();
		//sqlSessionFactoryBean 주소를 얻게된다.
	}
}
