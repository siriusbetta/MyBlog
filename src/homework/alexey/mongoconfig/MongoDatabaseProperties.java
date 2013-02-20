package homework.alexey.mongoconfig;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.util.Properties;

public class MongoDatabaseProperties{
	
	private String host;
	private String databaseName;
	private String defaultCollection;
	
	public MongoDatabaseProperties(){
		Properties properties = new Properties();
		
		URL url = this.getClass().getClassLoader().getResource("mongodatabase.properties");
		try {
			properties.load(new FileReader(url.getFile()));
			this.setHost(properties.getProperty("host"));
			this.setDatabaseName(properties.getProperty("databaseName"));
			this.setDefaultCollection(properties.getProperty("defaultCollection"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getDefaultCollection() {
		return defaultCollection;
	}
	public void setDefaultCollection(String defaultCollection) {
		this.defaultCollection = defaultCollection;
	}
	

}
