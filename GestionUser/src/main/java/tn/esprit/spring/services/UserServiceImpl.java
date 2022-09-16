package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.IUserRepository;
import tn.esprit.spring.response.ResponseMessage;
import tn.esprit.spring.util.SendMail;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository ur;
	
	@Autowired
	IUserService us;
	
	@Autowired
	@Lazy
	PasswordEncoder encoder;
	
	@Autowired
	private SendMail sendMail;

	@Override
	public void forgotpass(String emailuser) {
		// TODO Auto-generated method stub
		User d = ur.findByEmailUser(emailuser);
		/* com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21655717442"), new PhoneNumber("+19286429132"),
		         "Votre commande a été confirmée").create();*/
        final String username = "pidevusers@gmail.com";
        final String password = "Sofiene97*";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pidevusers@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailuser)
            );
            message.setSubject("Rest Your Password");
            message.setText("Welcom To Consomi Tounsi \n " 
            		+"Dear Client \n"
                    + "Please follow the following link : \n" + "http://localhost:4200/home/forgot/updatepassword");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    

		
	}
	
	
	
	
	@Override
	public ResponseEntity<?> addUser(User user) {
		
		
		List<CharacterRule> rules = Arrays.asList(
				  // at least one upper-case character
				  new CharacterRule(EnglishCharacterData.UpperCase, 1),

				  // at least one lower-case character
				  new CharacterRule(EnglishCharacterData.LowerCase, 1),

				  // at least one digit character
				  new CharacterRule(EnglishCharacterData.Digit, 1));

				PasswordGenerator generator = new PasswordGenerator();

		   PasswordGenerator gen = new PasswordGenerator();
		 
		
	


		    String password = gen.generatePassword(10, rules);
	
		user.setPassword(encoder.encode(password));
		
		user.setRole(Role.CLIENT);
		
		
		ur.save(user);
		
		
		String subject = "Compte";
		
		String message = "Bonjour "+ user.getFullName()+ "\n"
				+ "Votre username = "+ user.getUsername() +"\n"
						+ "Mot de passe = " + password;
		
		sendMail.send(user.getEmailUser(), subject, message);
		return ResponseEntity.ok(new ResponseMessage("user added Succefully"));
	}
	
	
	


	
	@Override
	public User updateUser( User user) throws Exception {
	
		return ur.save(user);
	}

	@Override
	public boolean deleteUser(int idUser) {
		if (ur.existsById(idUser)){
			ur.deleteById(idUser);
			return true;
		}else
		return false;
	}

	@Override
	public User retrieveUserById(int idUser) {
		return ur.findById(idUser).get();
	}



	     

	    @Override
	    public void updatePassword(String username, String newPassword) {
	    	User u = ur.findByUsername(username);
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        u.setPassword(encodedPassword);
	         
	    
	        ur.save(u);
	    }



	

	@Override
	public List<User> findAllClient() {
		
		return ur.findByRole(Role.CLIENT);
	}


	@Autowired EmailService emailService;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public User retrieveUserByUsername(String username) {
		// TODO Auto-generated method stub
		return ur.findByUsername(username);
	}

	@Override
	public User findUserByUserName(String username) {
		User u; 
		u= ur.findByUsername(username);
		return u;
	}
	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		user.setVerified(false);
		return ur.save(user);
	}
	@Override
	public void validateUser(int idu) {
     User u=ur.findById(idu).orElse(null);
		
				u.setVerified(true);
				ur.save(u);
				emailService.sendSimpleMessage(u.getEmailUser(), "Verification mail", "Votre compte a été verifié");
			
		}
	
	@Override
	public List<User> findUsers() {
		
		return (List<User>) ur.findAll();
	}
	
}
