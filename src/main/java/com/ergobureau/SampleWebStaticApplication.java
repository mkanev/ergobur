/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ergobureau;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.ergobureau.controller"})
public class SampleWebStaticApplication extends SpringBootServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(SampleWebStaticApplication.class);

  private static final String PROPERTY_NAME_MAIL_HOST = "mail.host";
  private static final String PROPERTY_NAME_MAIL_PORT = "mail.port";
  private static final String PROPERTY_NAME_MAIL_PROTOCOL = "mail.protocol";
  private static final String PROPERTY_NAME_MAIL_USERNAME = "mail.username";
  private static final String PROPERTY_NAME_MAIL_PASSWORD = "mail.password";

  @Resource
  private Environment environment;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SampleWebStaticApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SampleWebStaticApplication.class);
  }

  @Bean
  public MailSender mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(environment.getProperty(PROPERTY_NAME_MAIL_HOST));
    mailSender.setPort(environment.getProperty(PROPERTY_NAME_MAIL_PORT, Integer.class));
//    mailSender.setProtocol(environment.getProperty(PROPERTY_NAME_MAIL_PROTOCOL));
    mailSender.setUsername(environment.getProperty(PROPERTY_NAME_MAIL_USERNAME));
    mailSender.setPassword(environment.getProperty(PROPERTY_NAME_MAIL_PASSWORD));
    mailSender.setDefaultEncoding("utf-8");
    try {
      Properties properties = new Properties();
      properties.load((new ClassPathResource("javamail.properties")).getInputStream());
      mailSender.setJavaMailProperties(properties);
    } catch (IOException ioEx) {
      LOG.warn("Нет настроек JavaMail");
    }
    return mailSender;
  }

  @Bean
  public SimpleMailMessage emailMessageTemplate() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("office@ergobureau.com");
    mailMessage.setSubject("Мы получили ваше сообщение");
    return mailMessage;
  }
}
