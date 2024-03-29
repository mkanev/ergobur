package com.ergobureau;

import com.ergobureau.configuration.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebStaticApplication extends SpringBootServletInitializer {

  private final Logger log = LoggerFactory.getLogger(SampleWebStaticApplication.class);

  @Autowired
  private Environment env;

  @PostConstruct
  public void initApplication() throws IOException {
    if (env.getActiveProfiles().length == 0) {
      log.warn("No Spring profile configured, running with default configuration");
    } else {
      log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
    }
  }

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(SampleWebStaticApplication.class);
    app.setShowBanner(false);

    SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

    // Check if the selected profile has been set as argument.
    // if not the development profile will be added
    addDefaultProfile(app, source);

    app.run(args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.profiles(addDefaultProfile())
        .showBanner(false).sources(SampleWebStaticApplication.class);
  }

  /**
   * Set a default profile if it has not been set. <p/> <p> Please use -Dspring.profiles.active=dev </p>
   */
  private String addDefaultProfile() {
    String profile = System.getProperty("spring.profiles.active");
    if (profile != null) {
      log.info("Running with Spring profile(s) : {}", profile);
      return profile;
    }

    log.warn("No Spring profile configured, running with default configuration");
    return Constants.SPRING_PROFILE_DEVELOPMENT;
  }

  /**
   * Set a default profile if it has not been set
   */
  private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
    if (!source.containsProperty("spring.profiles.active")) {
      app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
    }
  }

}
