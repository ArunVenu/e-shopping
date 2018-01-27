package com.online.shoppingbackend.dao;

import java.util.List;

import com.online.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();

	Category get(int id);

}
