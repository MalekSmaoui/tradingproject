package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Order;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.BestPracticeRepsoitory;
import tn.esprit.spring.repositories.IUserRepository;
import tn.esprit.spring.repositories.OrderRepository;



@Service
public class OrderServiceImpl implements OrderService{
@Autowired
OrderRepository orderRepository;
@Autowired
IUserRepository userRepository;
@Autowired 
IUserService userService;

	@Override
	public void addOrder(Order o) {
		o.setState("pending");
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User u= userService.retrieveUserByUsername(username);
        o.setCreator(u);
		orderRepository.save(o);
		
	}
	@Override
	public Order visitOrder(int idorder) {
		
		return orderRepository.findById(idorder).orElse(null);
	}
	@Override
	public List<Order> Orders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}


	@Override
	public void deleteOrder(int ido) {
		orderRepository.deleteorder(ido);
		
	}
	
	@Override
	public void cancelOrder(int ido) {
		Order o= orderRepository.findById(ido).orElse(null);
		o.setState("canceled");
		orderRepository.save(o);
	}
	
	@Override
	public void approveOrder(Order or,int ido) {
		
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
	    User u= userService.retrieveUserByUsername(username);
		Order o= orderRepository.findById(ido).orElse(null);
		o.setState("approved");	
		o.setCapital(or.getCapital());
		o.setPercentage(or.getPercentage());
	    o.setValidator(u);
	    orderRepository.save(o);
	   
	}

	@Override
	public List<Order> myOrders(int id) {
		// TODO Auto-generated method stub
		

		
	    return orderRepository.findAllByCreatorId(id);
	}

	@Override
	public List<Order> search(String keyword) {
		// TODO Auto-generated method stub
		return orderRepository.search(keyword);
	}
	
	@Override
	public List<Order> search2(int id,String keyword) {
		// TODO Auto-generated method stub
		return orderRepository.search2(id,keyword);
	}

}
