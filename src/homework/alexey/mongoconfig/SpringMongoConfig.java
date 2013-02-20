package homework.alexey.mongoconfig;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class SpringMongoConfig {

	MongoDatabaseProperties properties;
	
	@Bean
	@ModelAttribute("property")
	public MongoDatabaseProperties getProperties(){
		properties = new MongoDatabaseProperties();
		
		System.out.println(properties.getHost());
		return null;
		
		
	}
	
	
	public @Bean Mongo mongo() throws IOException, MongoException{
		return new Mongo(properties.getHost());
	}
	
	public @Bean MongoTemplate mongoTemplate(@ModelAttribute("property") MongoDatabaseProperties property) throws IOException, MongoException {
		return new MongoTemplate(mongo(),properties.getDatabaseName(), 
				properties.getDefaultCollection());
	}

}
