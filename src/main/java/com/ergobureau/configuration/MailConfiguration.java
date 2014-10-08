package com.ergobureau.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Configuration
public class MailConfiguration extends EnvironmentAwareConfiguration {

  private final Logger log = LoggerFactory.getLogger(MailConfiguration.class);

  public MailConfiguration() {
    super(Constants.PROP_BLOCK_MAIL);
  }

  @Bean
  public JavaMailSenderImpl javaMailSender() {
    log.debug("Configuring mail server");
    String host = propertyResolver.getProperty(Constants.PROP_MAIL_HOST, Constants.PROP_MAIL_HOST_DEFAULT_VALUE);
    int port = propertyResolver.getProperty(Constants.PROP_MAIL_PORT, Integer.class, Constants.PROP_MAIL_PORT_DEFAULT_VALUE);
    String user = propertyResolver.getProperty(Constants.PROP_MAIL_USER);
    String password = propertyResolver.getProperty(Constants.PROP_MAIL_PASSWORD);
    Boolean tls = propertyResolver.getProperty(Constants.PROP_MAIL_TLS, Boolean.class, false);
    Boolean auth = propertyResolver.getProperty(Constants.PROP_MAIL_AUTH, Boolean.class, false);
    Boolean quitwait = propertyResolver.getProperty(Constants.PROP_MAIL_QUITWAIT, Boolean.class, false);

    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost(host);
    sender.setPort(port);
    sender.setUsername(user);
    sender.setPassword(password);

    Properties sendProperties = new Properties();
    sendProperties.setProperty(Constants.PROP_JAVAMAIL_SMTP_AUTH, auth.toString());
    sendProperties.setProperty(Constants.PROP_JAVAMAIL_STARTTLS, tls.toString());
    sendProperties.setProperty(Constants.PROP_JAVAMAIL_QUITWAIT, quitwait.toString());
    sender.setJavaMailProperties(sendProperties);
    return sender;
  }

  @Bean
  public SimpleMailMessage emailMessageTemplate() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("office@ergobureau.com");
    mailMessage.setSubject("Мы получили ваше сообщение");
    return mailMessage;
  }
}
