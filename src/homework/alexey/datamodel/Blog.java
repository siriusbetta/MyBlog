package homework.alexey.datamodel;


import java.util.Date;
import java.util.List;
import java.util.Random;



public class Blog {
	
	private String title;
	private String author;
	private String body;
	private String permalink;
	private List<String> tags;
	private Date date;
	private List<Comments> comments;
	
	public Blog(){
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		System.out.println("Data in class:");
		System.out.println(date);
		this.date = date;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
	public String generatwPermalink(){
		String permalink = "";
		Random rnd = new Random();
		int cnt = 0;
		while(cnt<=20){
			int out = rnd.nextInt(123);
			if((out >= 65 && out <= 90) || (out >= 97 && out <= 122)){
				permalink = permalink + (char)out;
				cnt++;	
			}
		}
		return permalink;
	}
}
