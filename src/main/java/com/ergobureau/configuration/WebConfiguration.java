package com.ergobureau.configuration;

import com.ergobureau.web.filter.CachingHttpHeadersFilter;
import com.ergobureau.web.filter.gzip.GZipServletFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter implements ServletContextInitializer {

  private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

  @Autowired
  private Environment env;

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
    EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
    initCachingHttpHeadersFilter(servletContext, disps);
    initGzipFilter(servletContext, disps);
    log.info("Web application fully configured");
  }

  /**
   * Initializes the cachig HTTP Headers Filter.
   */
  private void initCachingHttpHeadersFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
    log.debug("Registering Cachig HTTP Headers Filter");
    FilterRegistration.Dynamic cachingHttpHeadersFilter = servletContext.addFilter("cachingHttpHeadersFilter", new CachingHttpHeadersFilter());
    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/images/*");
    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/fonts/*");
    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/js/*");
    cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/css/*");
    cachingHttpHeadersFilter.setAsyncSupported(true);
  }

  /**
   * Initializes the GZip filter.
   */
  private void initGzipFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
    log.debug("Registering GZip Filter");
    FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("gzipFilter", new GZipServletFilter());
    Map<String, String> parameters = new HashMap<>();
    compressingFilter.setInitParameters(parameters);
    compressingFilter.addMappingForUrlPatterns(disps, true, "*.css");
    compressingFilter.addMappingForUrlPatterns(disps, true, "*.json");
    compressingFilter.addMappingForUrlPatterns(disps, true, "*.html");
    compressingFilter.addMappingForUrlPatterns(disps, true, "*.js");
    compressingFilter.addMappingForUrlPatterns(disps, true, "/");
    compressingFilter.addMappingForUrlPatterns(disps, true, "/app/rest/*");
    compressingFilter.addMappingForUrlPatterns(disps, true, "/metrics/*");
    compressingFilter.setAsyncSupported(true);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }
}
