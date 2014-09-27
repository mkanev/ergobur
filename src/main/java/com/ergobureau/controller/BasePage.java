package com.ergobureau.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class BasePage implements InitializingBean {

  private static final String PROP_PREFIX_APPLICATION = "application";
  private static final String PROP_PREFIX_ANALYTICS = "analytics";
  private static final String PROP_TACKING_CODE = "code";

  @Autowired
  private Environment env;

  private RelaxedPropertyResolver propertyResolver;
  private String trackingCode;

  @Override
  public void afterPropertiesSet() throws Exception {
    this.propertyResolver = new RelaxedPropertyResolver(env, PROP_PREFIX_APPLICATION);
    initData();
  }

  private void initData() {
    initAnalytics();
  }

  private void initAnalytics() {
    String prefix = StringUtils.join(new String[]{PROP_PREFIX_APPLICATION, PROP_PREFIX_ANALYTICS}, ".") + ".";
    RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, prefix);
    trackingCode = propertyResolver.getProperty(PROP_TACKING_CODE);
  }

  @ModelAttribute("gaTrackingCode")
  public String analyticsTrackingCode() {
    return trackingCode;
  }
}
