package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.shoppingbackend.dao.CategoryDAO;
import com.online.shoppingbackend.dto.Category;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = "/allproduct")
	public ModelAndView allproducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Product");
		mv.addObject("userClickAllproducts", true);
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContactus", true);
		return mv;
	}

	/*
	 * method to load all products
	 * */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllproducts", true);
		return mv;
	}
	
	/*
	 * method to load category wise product
	 * */
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showAllCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		//categoryDAO to fetch single category
		
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		
		//passing the single category object
		mv.addObject("category",category);
		
		mv.addObject("userClickCategoryproducts", true);
		return mv;
	}
	
	
	
	
	

}
