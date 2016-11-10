package com.modulus.uno.config

import javax.servlet.DispatcherType
import javax.servlet.ServletContext
import javax.servlet.ServletException

import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.boot.context.embedded.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource

import net.bull.javamelody.MonitoringFilter
import net.bull.javamelody.Parameter
import net.bull.javamelody.SessionListener

@Configuration
//@ImportResource("classpath:net/bull/javamelody/monitoring-spring.xml")
//@ImportResource("classpath:net/bull/javamelody/monitoring-spring-datasource.xml")
class JavaMelodyConfiguration implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    servletContext.addListener(new SessionListener())
  }

  @Bean
  public FilterRegistrationBean javaMelody() {
    final FilterRegistrationBean javaMelody = new FilterRegistrationBean()
    javaMelody.setFilter(new MonitoringFilter())
    javaMelody.setAsyncSupported(true)
    javaMelody.setName("javamelody")
    javaMelody.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC)
    javaMelody.addInitParameter(Parameter.LOG.getCode(), Boolean.toString(true))
    javaMelody.addUrlPatterns("/*")
    javaMelody
  }
}
