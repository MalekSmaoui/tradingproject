package tn.esprit.spring.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.response.Response;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.IUserRepository;




@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserRestController {

	@Autowired
	IUserRepository ur;
	
	@Autowired
	IUserService us;
	
	@Autowired
	ServletContext context;
	
	// http://localhost:9091/SpringMVC/servlet/add-user
	//@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PostMapping("/add-user")
	
	public ResponseEntity<?> addUser(@RequestBody User user) {
		return us.addUser(user);
	
	}
	

	// http://localhost:9091/SpringMVC/servlet/delete-user/{user-id}
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	@DeleteMapping("/delete-user/{idUser}")
	@ResponseBody
	public void deleteUser(@PathVariable("idUser") int userId) {
	us.deleteUser(userId);
	}
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	// http://localhost:9091/SpringMVC/servlet/update-user
	@PutMapping("/update-user")
	@ResponseBody
	public User updateUser(@RequestBody User user) throws Exception {
	return us.updateUser(user);
	}
	

	
	@GetMapping("/all-client")

	public List<User> getClients() {
	List<User> users = us.findAllClient();
	return users;
	}
	
			
	// http://localhost:9091/SpringMVC/servlet/retrieve-user-by-id/{user-id}
	@GetMapping("/retrieve-user-by-id/{user-id}")
	@ResponseBody
	public User retrieveUserById(@PathVariable("user-id") int userId) {
	return us.retrieveUserById(userId);
	}
	
	

	
	@GetMapping("/sendme/{emailUser}")
	public void forgotpass(@PathVariable ("emailUser") String emailUser){
		us.forgotpass(emailUser);
	}
	
	@PutMapping("/updatepassword/{username}/{password}")
	void updatePassword(@PathVariable ("username") String username, @PathVariable ("password") String newPassword){
		us.updatePassword(username, newPassword);
	}
	
	

}
