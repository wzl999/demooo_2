package com.zhiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zhiyou.dao.UserDAO;
import com.zhiyou.pojo.User;
import com.zhiyou.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDAO userDAO;
	
	@CacheEvict(value="userSelect",allEntries=true)
	public void add(User user) {
		userDAO.add(user);
	}
	// 用来标记需要清除的缓存的方法，以及指定需要清除的缓存	allEntries：代表是否清除缓存中的所有元素
	@CacheEvict(value="userSelect",allEntries=true)
	public void delete(int id) {
		userDAO.delete(id);
	}
	@CacheEvict(value="userSelect",allEntries=true)
	public void update(User user) {
		userDAO.update(user);
	}
	
	
	// 指定下面这个方法需要使用缓存，使用缓存的名字叫userSelect，缓存的key为传入的page的值
	@Cacheable(value="userSelect",key="#page")
	public List<User> queryAll(int page) {
		PageHelper.startPage(page, 5);
		return userDAO.queryAll();
	}
	
	public User queryById(int id) {
		return userDAO.queryById(id);
	}

	
}
