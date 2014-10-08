package com.ergobureau.controller;

import com.ergobureau.common.ConfigUtils;
import com.ergobureau.configuration.Constants;

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

  @Autowired
  private Environment env;

  private String trackingCode;
  private String cdnUrl;

  @Override
  public void afterPropertiesSet() throws Exception {
    initData();
  }

  private void initData() {
    initAnalytics();
    initCdn();
  }

  private void initAnalytics() {
    RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, ConfigUtils.getApplicationPropertyBlockPrefix(Constants.PROP_BLOCK_ANALYTICS));
    trackingCode = propertyResolver.getProperty(Constants.PROP_TACKING_CODE);
  }

  private void initCdn() {
    RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, ConfigUtils.getWebPropertyBlockPrefix(Constants.PROP_BLOCK_CDN));
    String host = propertyResolver.getProperty(Constants.PROP_CDN_HOST, Constants.PROP_CDN_HOST_DEFAULT_VALUE);
    String port = propertyResolver.getProperty(Constants.PROP_CDN_PORT);
    String scheme = propertyResolver.getProperty(Constants.PROP_CDN_SCHEME);
    cdnUrl = (StringUtils.isNotBlank(scheme) ? scheme + ":" : StringUtils.EMPTY) + "//" + host + (StringUtils.isNotBlank(port) ? ":" + port : StringUtils.EMPTY);
  }

  @ModelAttribute("gaTrackingCode")
  public String getAnalyticsTrackingCode() {
    return trackingCode;
  }

  @ModelAttribute("cdnUrl")
  public String getCdnUrl() {
    return cdnUrl;
  }
}
