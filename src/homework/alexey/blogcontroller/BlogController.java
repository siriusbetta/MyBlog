package homework.alexey.blogcontroller;

import homework.alexey.datamodel.Blog;
import homework.alexey.datamodel.Comments;
import homework.alexey.datamodel.Registration;
import homework.alexey.datamodel.User;

import homework.alexey.mongoconfig.SpringMongoConfig;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Order;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BlogController<E> {
	//private List<User> userList = new ArrayList<User>();
	private List<Blog> blogList = new ArrayList<Blog>();
	HttpSession session;
	
	private ApplicationContext ctx = 
			new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		
		private MongoOperations mongoOps = 
			(MongoOperations)ctx.getBean("mongoTemplate");
		
	@RequestMapping("/blog/{link}.htm")
	public String showBlog(@PathVariable Integer link, ModelMap model, HttpServletRequest request){
		Query query = new Query();
		query.sort().on("date", Order.DESCENDING);
		query.skip(link).limit(10);
		blogList = mongoOps.find("posts", query, Blog.class);
		
		int cnt = mongoOps.getCollection(Blog.class).size();
		int beginItem;
		
		if (link == 0){
			beginItem = 0;
		} 
		
		if(link < 1000){
			beginItem = (link / 10);
			}
		else{
			beginItem = ((link / 10) - 10);
		}

		int endItem = beginItem + 1;
		if(endItem == (cnt / 10))
			endItem = 0;
		model.addAttribute("beginItem", beginItem);
		model.addAttribute("endItem", endItem);
		model.addAttribute("blogs", blogList);
		session = request.getSession();
		model.addAttribute("cnt", cnt);
		return "showBlog";
	}
	
	@RequestMapping("/tag/{tag}.htm")
	public String searchTags(@PathVariable String tag, ModelMap model, HttpServletRequest request){
		Query query = new Query(Criteria.where("tags").in(tag));
		
		query.sort().on("date", Order.DESCENDING);
		query.limit(10);
		blogList = mongoOps.find("posts", query, Blog.class);
		System.out.println(blogList.size());
		model.addAttribute("blogs", blogList);
		model.addAttribute("tag", tag);
		model.addAttribute("size", blogList.size());
		return "searchPage";
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView login(){
		User user = new User();
		ModelAndView model = new ModelAndView("aLogin");
		model.addObject("user", user);
		return model;
	}
	
	//check user and password in data, yes - redirect to block, no - return ajax query
	@RequestMapping(value = "/login.htm", method= RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user ,BindingResult result, HttpServletResponse response){
		
		Query query = new Query(Criteria.where("id").is(user.getId()).and("password").is(user.getPassword()));
		User loginUser = mongoOps.findOne("users", query, User.class);
		String redirectUrl; 
		if(loginUser != null){
			redirectUrl = "redirect:index.jsp";
			
				session.setAttribute("sesAtrrUserId", user.getId());
				session.setAttribute("sesAtrrPass", user.getPassword());
			
		}else{
			
			redirectUrl = "loginError";
		}
		return redirectUrl;
	}
	
	//show new user registration form for input and validation
	
	
	@RequestMapping(value = "/registration.htm", method = RequestMethod.GET)
	public String registrationForm(Map<String, Registration> model){
		Registration registration = new Registration();
		model.put("registration", registration);
		return "registrationForm";
	}
	
	@Autowired
	RegistrationValidation registrationValidation;
	public void setRegistrationValidation(RegistrationValidation registrationValidation){
		this.registrationValidation = registrationValidation;
	}
	
	@RequestMapping(value = "/registration.htm", method = RequestMethod.POST)
	public String validRegistrationForm(@Valid Registration registration, BindingResult result,
			HttpServletResponse respons){
		registrationValidation.validate(registration, result, mongoOps);
		if(result.hasErrors()){
			return "registrationForm";
		}
		User user = new User();
		user.setId(registration.getId());
		user.setPassword(registration.getPassword());
		mongoOps.save("users", user);
		session.setAttribute("sesAtrrUserId", registration.getId());
		session.setAttribute("sesAtrrPass", registration.getPassword());
		return "redirect:index.jsp";
	}
	
	@RequestMapping(value = "/posts/{permalink}.htm", method = RequestMethod.GET)
	public String getPost(@PathVariable String permalink, Map<String, Blog> blogMap, Map<String, Comments> commentsMap){
		Blog blog = mongoOps.findOne("posts", new Query(Criteria.where("permalink").is(permalink)), Blog.class);
		blogMap.put("blog", blog);
		Comments comment = new Comments();
		commentsMap.put("comment", comment);
		return "showPost";
	}
	
	@RequestMapping(value = "/addComment.htm")
	public String addComment(@ModelAttribute ("comment") Comments comment, ModelMap redirect){
		Map<String, String> commentMap = new HashMap<String, String>();
		redirect.put("permalink", comment.getPermalink());
		commentMap.put("author", comment.getAuthor());
		commentMap.put("email", comment.getEmail());
		commentMap.put("body", comment.getBody());
		Update update = new Update();
		update.push("comments", commentMap);
		mongoOps.updateFirst("posts", new Query(Criteria.where("permalink").is(comment.getPermalink())), update);
		//return "hello";
		return "redirect:/posts/"+comment.getPermalink()+".htm";
	}
	
	@RequestMapping(value = "/newpost.htm", method = RequestMethod.GET)
	public String callNewPostForm(Map<String, Blog> map){
		Blog blog = new Blog();
		
		blog.setDate(new Date());
		blog.setPermalink(blog.generatwPermalink());
		map.put("post", blog);
		return "newpost";
	}
	
	@RequestMapping(value = "/newpost.htm", method = RequestMethod.POST)
	public String addNewPost(@ModelAttribute("post") Blog post, BindingResult result){
		String Tags = (String)post.getTags().get(0);
		List<String> list = Arrays.asList(Tags.split(","));
		post.setTags(list);
		post.setDate(new Date());
		System.out.println("date from form: ");
		System.out.println();
		mongoOps.save("posts", post);
		return "redirect:index.jsp";
	}
	//clear session and cookies
	@RequestMapping("/logout.htm")
	public String logout(){
		session.invalidate();
		
		return "redirect:index.jsp";
	}
}
