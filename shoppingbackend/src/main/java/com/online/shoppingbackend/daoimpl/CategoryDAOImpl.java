package com.online.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.online.shoppingbackend.dao.CategoryDAO;
import com.online.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	/*
	 * 
	 * Getting category List
	 * 
	 * */
	@Override
	public List<Category> list() {
		
		String selectActiveCategory = "FROM Category WHERE active=:active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		// TODO Auto-generated method stub
		return query.getResultList();
	}

	/*
	 * Getting single category based on id 
	 * */
	@Override
	public Category get(int id) {
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	
	}

	/*
	 * Adding single category to the database
	 * */
	@Override
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * Update the single category
	 * 
	 * */
	@Override
	public boolean update(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 1.delete category. delete means setting is_active field as false
	 * 
	 * */
	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
