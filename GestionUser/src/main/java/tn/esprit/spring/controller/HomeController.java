package tn.esprit.spring.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Documentation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Likee;
import tn.esprit.spring.entity.Order;
import tn.esprit.spring.entity.ResponseData;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.recommendation;
import tn.esprit.spring.services.BestPracticeService;
import tn.esprit.spring.services.CommentaireService;
import tn.esprit.spring.services.DocumentationService;
import tn.esprit.spring.services.EmailService;
import tn.esprit.spring.services.EventService;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.OrderService;
import tn.esprit.spring.services.RecommendationService;


@RestController
@Api(tags = "Home")
@CrossOrigin(origins="http://localhost:4200")

public class HomeController {
@Autowired
IUserService userService;
@Autowired 
BestPracticeService bestPracticeService;
@Autowired
EventService eventService;
@Autowired
CommentaireService commentaireService;
@Autowired
OrderService orderService;
@Autowired
RecommendationService recommendationService;
@Autowired 
DocumentationService documentationService;
//EmailValidator emailValidator;

//@PostMapping("/registration")
//public String createNewUser( @RequestBody User user) 
//	{
//	String msg="";
//	
////	boolean isValidEmail= emailValidator.test(user.getEmail());
//	User userExists = userService.findUserByUserName(user.getUsername());
//	if (userExists != null) 	
//		{
//	msg="There is already a user registered with the user name provided";
//		}
////	if(!isValidEmail) {
////		throw new IllegalStateException("email not valid");
////	}
//	else
//		{
//	userService.saveUser(user);
//	msg="OK"; 
//		}
//	return msg; 
//	}
@Autowired 
AuthenticationManager authenticationManager;
@Autowired
IUserService iUserService;
//@Autowired
//JwtTokenUtil jwtTokenUtil;
@PostMapping("/validateUser/{idu}")
public void validateUsers(@PathVariable int idu) {
	iUserService.validateUser(idu);
}
@GetMapping("/users")
public List<User> users(){
	return userService.findUsers();
}
//@PostMapping("/authenticate")
//public JwtResponse createAuthenticationToken(@RequestBody JwtRequest user) throws Exception {
//
//	Authentication auth = authenticate(user.getUsername(), user.getPassword());
//	final User userDetails = (User) iUserService.findUserByUserName(user.getUsername());
//	final String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities());
//	return new JwtResponse(token, userDetails);
//}
//
//private ResponseEntity<?> authenticate(String username, String password ) throws Exception {
//	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//	SecurityContextHolder.getContext().setAuthentication(authentication);
//    String jwt = jwtTokenUtil.generateToken(authentication.getName(),authentication.getAuthorities());
//    
//    UserDetails userDetails = (UserDetails) authentication.getPrincipal();    
//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());
//
//    return ResponseEntity.ok(new JwtResponse(jwt, 
//                         userDetails.getAuthorities(), 
//                         userDetails.getUsername(), 
//                         userDetails.getEmail(), 
//                         roles));
//}
//private Authentication authenticate(String username, String password ) throws Exception {
//	try {
//		Authentication auth = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		return auth;
//	} catch (InternalAuthenticationServiceException e) {
//		throw new InternalAuthenticationServiceException("INVALID_USERNAME", e);
//	}
//
//	catch (DisabledException e) {
//		throw new DisabledException("USER_DISABLED", e);
//	}
//
//	catch (BadCredentialsException e) {
//		throw new BadCredentialsException("INVALID_CREDENTIALS", e);
//	}
//}
@PostMapping("/addbp")
public void addbestPractice(@RequestBody BestPractice bp) {
	bestPracticeService.addBestPractice(bp);
}
@PostMapping("/adde")
public void addEvent(@RequestBody Event e) {
	eventService.addEvent(e);
}
@PostMapping("/addd")
public void addDocumentation(@RequestBody Documentation d) {
	documentationService.addDocumentation(d);
}
@PostMapping("/addc/{ide}")
public void addCommentaire(@RequestBody Commentaire c, @PathVariable int ide) {
	commentaireService.addCommentairetoEvent(c, ide);
}
@GetMapping("/searchb/{keyword}")
public List<BestPractice> searchBestPractice (@PathVariable String keyword)
{return bestPracticeService.search(keyword);
}
@GetMapping("/searche/{keyword}")
public List<Event> searchEvent (@PathVariable String keyword)
{
	return eventService.search(keyword);
}
@GetMapping("/searchd/{keyword}")
public List<Documentation> searchDocumentation (@PathVariable String keyword)
{
	return documentationService.search(keyword);
}
@PostMapping("/upload")
public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception{
	Documentation doc=null;
	String downloadURL ="";
	
	doc = documentationService.saveDocumentation(file);
	String r =Integer.toString(doc.getIdDoc());
	downloadURL =ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(r).toUriString();
	return new ResponseData(doc.getTitre(),downloadURL,file.getContentType(),file.getSize());
}
/******/
@GetMapping("/download/{fileId}")
public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int fileId) throws Exception
{
  Documentation doc = null;
  doc=documentationService.getDocumentation(fileId);
  return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDescription())).header(HttpHeaders.CONTENT_DISPOSITION, "documentation;filename="+doc.getTitre()).body(new ByteArrayResource(doc.getData()));
  
}
/****/

@PostMapping("like/{idc}")
public void like(@PathVariable int idc,@RequestBody Likee like){
	commentaireService.like(idc, like);
	}
@GetMapping("/event/{ide}")
public Event event(@PathVariable int ide) {
	return eventService.visitEvent(ide);
}
@GetMapping("/events")
public List<Event> events(){
	return eventService.Events();
	}
@GetMapping("/documentations")
public List<Documentation> documentations(){
	return documentationService.Documentations();
	}
@GetMapping("/commentaires/{ide}")
public List<Commentaire> commentaires(@PathVariable int ide){
	return commentaireService.commentaires(ide);
	}
@Autowired
private EmailService emailService;
 
// Sending a simple Email
@PostMapping("/sendMail")
public void sendMail( String to,  String subject,  String text)
{
   emailService.sendSimpleMessage(to, subject, text);
}
@DeleteMapping("/deleteb/{idb}")
public void deletebestPractice(@PathVariable int idb){
bestPracticeService.deleteBestPractice(idb);	
}
@DeleteMapping("/deletec/{idc}")
public void deleteCommentaire(@PathVariable int idc){
commentaireService.deleteCommentaire(idc);	
}
@DeleteMapping("/deleted/{idd}")
public void deleteDocumentation(@PathVariable int idd){
documentationService.deleteDocumentation(idd);	
} 
@DeleteMapping("/deletee/{ide}")
public void deleteEvent(@PathVariable int ide){
eventService.deleteEvent(ide);	
} 
@PutMapping("/editb/idb")
public void editBestPractice(@PathVariable int idb,@RequestBody BestPractice bp){
bestPracticeService.EditBestPractice(bp, idb);
}
@PutMapping("/editc/idc")
public void editCommentaire(@PathVariable int idc,@RequestBody Commentaire c){
	commentaireService.EditCommentaire(c, idc);
}
@PostMapping("/validateEvent/{ide}")
public void validateEvent(@PathVariable int ide){
	eventService.validateEvent(ide);
	}
@GetMapping("/bestpractices")
public List<BestPractice> bestpractices(){
	return bestPracticeService.BestPractices();
	}
@GetMapping("/bestpractice/{idb}")
public BestPractice bestpractice(@PathVariable int idb) {
	return bestPracticeService.visitBestPractice(idb);
}
@PutMapping("/edite/{ide}")
public void editEvent(@PathVariable int ide,@RequestBody Event e){
	eventService.EditEvent(e, ide);
}
@PutMapping("/editd/idd")
public void editDocumentation(@PathVariable int idd,@RequestBody Documentation d){
	documentationService.EditDocumentation(d, idd);
}

/////////////////////////////
/////////////////////////////
/////////////////////////////

@PostMapping("/addorder")
public void addOrder(@RequestBody Order o) {
	orderService.addOrder(o);
}

@PutMapping("/cancelorder/{ido}")
public void cancelOrder(@PathVariable int ido){
	orderService.cancelOrder(ido);
	}


@PutMapping("/approveorder/{ido}")
public void approveOrder(@PathVariable int ido){
	orderService.approveOrder(ido);
	}

@DeleteMapping("/deleteorder/{ido}")
public void deleteOrder(@PathVariable int ido){
	orderService.deleteOrder(ido);	
} 

@GetMapping("/myorders/{idu}")
public List<Order> myorders(@PathVariable int idu){
	return orderService.myOrders(idu);
	}

@GetMapping("/order/{idorder}")
public Order order(@PathVariable int idorder) {
	return orderService.visitOrder(idorder);
}

@GetMapping("/orders")
public List<Order> orders(){
	return orderService.Orders();
	}

@GetMapping("/searchorder/{keyword}")
public List<Order> searchOrder (@PathVariable String keyword)
{
	return orderService.search(keyword);
}

@GetMapping("/searchorder2/{id}/{keyword}")
public List<Order> searchOrder (@PathVariable int id,@PathVariable String keyword)
{
	return orderService.search2(id,keyword);
}
//////////////////////////////////

@PostMapping("/addrecommendation")
public void addRecommendation(@RequestBody recommendation r) {
	recommendationService.addRecommendation(r);
}

@DeleteMapping("/deleterecommendation/{idr}")
public void deleteRecommendation(@PathVariable int idr){
	recommendationService.deleteRecommendation(idr);	
}

@GetMapping("/recommendations")
public List<recommendation> recommendations(){
	return recommendationService.Recommendations();
	}

@GetMapping("/searchrecom/{keyword}")
public List<recommendation> search (@PathVariable String keyword)
{
	return recommendationService.search(keyword);
}

}

