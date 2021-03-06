package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.how2java.tmall.mapper.OrderMapper;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderExample;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.UserService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	UserService userService;
	@Autowired
	OrderItemService orderItemService;
	public void add(Order c) {
		orderMapper.insert(c);
		
	}

	@Override
	public void delete(int id) {
		orderMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void update(Order c) {
		orderMapper.updateByPrimaryKeySelective(c);
		
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list() {
		OrderExample example = new OrderExample();
		example.setOrderByClause("id desc");
		List <Order> result = orderMapper.selectByExample(example);
		setUser(result);
		return result;
	}
	public void setUser (List<Order> os){
		for (Order o : os){
			setUser(o);
		}
	}
	public void setUser(Order o){
		int uid = o.getUid();
		User u = userService.get(uid);
		o.setUser(u);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public float add(Order c,List<OrderItem> ois) {
		float total = 0;
		add(c);
		if (false)
			throw new RuntimeException();
		for (com.how2java.tmall.pojo.OrderItem oi: ois){
			oi.setOid(c.getId());
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice()*oi.getNumber();
		}
		return total;
	}

	@Override
	public List list(int uid, String excludedStatus) {
		// TODO Auto-generated method stub
		OrderExample example = new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
		example.setOrderByClause("id desc");
		
		return orderMapper.selectByExample(example);
	}
	
	
}
