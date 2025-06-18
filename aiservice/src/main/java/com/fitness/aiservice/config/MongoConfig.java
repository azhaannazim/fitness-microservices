package com.fitness.aiservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
}

//@EnableMongoAuditing lets Spring Data MongoDB automatically populate certain fields in your documents — typically:
//@CreatedDate
//@LastModifiedDate
//@CreatedBy
//@LastModifiedBy

//
//@Configuration == Spring configuration class
//✅ Mainly used to instantiate and configure Spring Beans.
// ✅ Often used in combination with @Bean.
//This lets Spring manage modelMapper in its context.
//Then you can inject it somewhere with @Autowired.