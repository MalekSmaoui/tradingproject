package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Order;


public interface OrderService {
	public void addOrder(Order o);
	public void cancelOrder(int ido);
	public void approveOrder(Order or,int ido);
	public void deleteOrder(int ido);
	public List<Order> myOrders(int id);
	public Order visitOrder(int idorder);
	public List<Order> Orders();
	public List<Order> search(String keyword);
	public List<Order> search2(int id ,String keyword);
}
