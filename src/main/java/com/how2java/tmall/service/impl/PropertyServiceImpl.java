package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyExample;
import com.how2java.tmall.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	PropertyMapper propertyMapper;
	
	public void add(Property c) {
		// TODO Auto-generated method stub
		propertyMapper.insert(c);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		propertyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Property c) {
		// TODO Auto-generated method stub
		propertyMapper.updateByPrimaryKey(c);
	}

	@Override
	public Property get(int id) {
		// TODO Auto-generated method stub
		return propertyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List list(int cid) {
		// TODO Auto-generated method stub
		PropertyExample example = new PropertyExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		return propertyMapper.selectByExample(example);
	}

}
