package com.talan.rsa.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 500)
public class HttpSessionConfig {
	//Spring Boot automatically creates a RedisConnectionFactory that connects Spring Session to a Redis Server on localhost so no nedd to create that here
	//just configure the connection in app.props
}
