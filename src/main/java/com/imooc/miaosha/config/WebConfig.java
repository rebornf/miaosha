package com.imooc.miaosha.config;

import java.util.List;

import com.imooc.miaosha.access.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




/**
* @Description:  SpringMVC中会带有很多参数，比如request ,response,model等等，其实都是通过addArgumentResolvers，框架会回调方法
 * 并给他们进行赋值 ，所以我们只需要遍历方法的参数，看看有没有这个类型的参数，如果有，设置上就好了
* @Param:
* @return:
* @Author: fty
* @Date:
*/
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Autowired
	UserArgumentResolver userArgumentResolver;

	@Autowired
	AccessInterceptor accessInterceptor;

	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	@Override   //把拦截器注入进来
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessInterceptor);
	}


}
