package com.yingchun.tsys.assignment.config;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class PortConfiguration {
    // Logger instance
    private static final Logger log = LoggerFactory.getLogger(PortConfiguration.class);
 
    @Value("${server.http-port:0}")
    private Integer serverHttpPort;
 
    public class TomcatWebServerHttpPortCustomizer
            implements WebServerFactoryCustomizer {
    	
		@Override
		public void customize(WebServerFactory factory) {
            log.info("serverHttpPort property configured as {}", serverHttpPort);
            
            if (serverHttpPort > 0) {
                log.info("Configuring Http Port to {}", serverHttpPort);
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setPort(serverHttpPort);
                ((TomcatServletWebServerFactory) factory).addAdditionalTomcatConnectors(connector);
            }
		}
    }
 
    @Bean
    public WebServerFactoryCustomizer containerCustomizer() {
        return new TomcatWebServerHttpPortCustomizer();
    }
}
