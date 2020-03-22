package Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

import ApplicationContext.SqlServiceContext;

@Import(value=SqlServiceContext.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSqlService {
	
}
