package com.ergobureau.common;

import com.ergobureau.configuration.Constants;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class ConfigUtils {

  private ConfigUtils() {
  }

  public static String getApplicationPropertyBlockPrefix(String block) {
    return getPropertyBlockPrefix(Constants.PROP_PREFIX_APPLICATION, block);
  }

  public static String getSpringPropertyBlockPrefix(String block) {
    return getPropertyBlockPrefix(Constants.PROP_PREFIX_SPRING, block);
  }

  public static String getWebPropertyBlockPrefix(String block) {
    return getPropertyBlockPrefix(Constants.PROP_PREFIX_WEB, block);
  }

  private static String getPropertyBlockPrefix(String parent, String block) {
    return StringUtils.join(new String[]{parent, block}, ".") + ".";
  }
}
