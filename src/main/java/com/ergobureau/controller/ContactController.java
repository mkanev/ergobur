package com.ergobureau.controller;

import com.ergobureau.rest.Opportunity;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping("/contact")
public class ContactController extends BasePage {

  private static final Logger log = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private JavaMailSenderImpl javaMailSender;
  @Autowired
  private SimpleMailMessage mailMessage;

  @RequestMapping(method = RequestMethod.POST)
  public
  @ResponseBody
  String createOpportunity(@RequestBody Opportunity opportunity) {
    if (opportunity == null) {
      throw new IllegalArgumentException("Ошибка в работе скрипта!");
    }
    log.info("Получен запрос: {}", opportunity);

    SimpleMailMessage msg = new SimpleMailMessage(this.mailMessage);
    String email = opportunity.getEmail();
    msg.setTo(email);
    msg.setText("Спасибо за интерес к нашей компании. Мы свяжемся с вами в ближайшее время.");
    try {
      this.javaMailSender.send(msg);
      log.info("Письмо отправлено {}", email);
    } catch (MailException ex) {
      log.error("Невозможно отправить письмо", ex);
    }

    msg = new SimpleMailMessage(this.mailMessage);
    msg.setTo(msg.getFrom());
    msg.setText("Получено сообщение от " + opportunity.getName() + " <" + email + ">:\n" + opportunity.getMessage());
    try {
      this.javaMailSender.send(msg);
      log.info("Письмо отправлено {}", email);
    } catch (MailException ex) {
      log.error("Невозможно отправить письмо", ex);
    }
    return StringUtils.EMPTY;
  }
}
