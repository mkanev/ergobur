package com.ergobureau.configuration;

import com.ergobureau.common.ConfigUtils;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class EnvironmentAwareConfiguration implements EnvironmentAware {

  private final String propertyBlockPrefix;
  protected RelaxedPropertyResolver propertyResolver;

  public EnvironmentAwareConfiguration(String propertyBlockPrefix) {
    this.propertyBlockPrefix = propertyBlockPrefix;
  }

  @Override
  public void setEnvironment(Environment environment) {
    propertyResolver = new RelaxedPropertyResolver(environment, ConfigUtils.getApplicationPropertyBlockPrefix(propertyBlockPrefix));
  }
}
