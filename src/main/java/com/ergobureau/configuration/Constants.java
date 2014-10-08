package com.ergobureau.configuration;

/**
 * Application constants.
 */
public final class Constants {

  /* System */
  public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
  public static final String SPRING_PROFILE_PRODUCTION = "prod";
  public static final String SYSTEM_ACCOUNT = "system";
  public static final String PROP_PREFIX_SPRING = "spring";

  /* Application */
  public static final String PROP_PREFIX_APPLICATION = "application";

  /* Analytics */
  public static final String PROP_BLOCK_ANALYTICS = "analytics";
  public static final String PROP_TACKING_CODE = "code";

  /* Mail*/
  public static final String PROP_BLOCK_MAIL = "mail";
  public static final String PROP_MAIL_HOST = "host";
  public static final String PROP_MAIL_HOST_DEFAULT_VALUE = "127.0.0.1";
  public static final String PROP_MAIL_PORT = "port";
  public static final int PROP_MAIL_PORT_DEFAULT_VALUE = 587;
  public static final String PROP_MAIL_USER = "user";
  public static final String PROP_MAIL_PASSWORD = "password";
  public static final String PROP_MAIL_TLS = "tls";
  public static final String PROP_MAIL_AUTH = "auth";
  public static final String PROP_MAIL_QUITWAIT = "quitwait";
  public static final String PROP_JAVAMAIL_SMTP_AUTH = "mail.smtp.auth";
  public static final String PROP_JAVAMAIL_STARTTLS = "mail.smtp.starttls.enable";
  public static final String PROP_JAVAMAIL_QUITWAIT = "mail.smtp.quitwait";

  /* Web */
  public static final String PROP_PREFIX_WEB = "web";

  /* CDN */
  public static final String PROP_BLOCK_CDN = "cdn";
  public static final String PROP_CDN_HOST = "host";
  public static final String PROP_CDN_HOST_DEFAULT_VALUE = "localhost";
  public static final String PROP_CDN_PORT = "port";
  public static final String PROP_CDN_SCHEME = "scheme";

  private Constants() {
  }

}
