package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.UserMapper;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.pojo.UserExample;
import com.how2java.tmall.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	public void add(User c) {
		userMapper.insert(c);
	}

	@Override
	public void delete(int id) {
		userMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void update(User c) {
		userMapper.updateByPrimaryKeySelective(c);
		
	}

	@Override
	public User get(int id) {
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list() {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return userMapper.selectByExample(example);
	}

	@Override
	public boolean isExist(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List <User> result = userMapper.selectByExample(example);
		if (!result.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public User get(String name, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List <User> result = userMapper.selectByExample(example);
		if (result.isEmpty()){
			return null;
		}
		
		return result.get(0);
	}

}
