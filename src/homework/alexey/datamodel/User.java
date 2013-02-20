package homework.alexey.datamodel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private String id = null;
	private String password = null;
	
	
	public User(){
		
	}
		public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = md5(password);
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	private static String md5(String password){
		String md5 = null;
		if(password == null) return null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5;
	}
	

}
