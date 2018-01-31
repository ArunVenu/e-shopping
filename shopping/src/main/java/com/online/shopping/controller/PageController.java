package com.online.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.shopping.exception.ProductNotFoundException;
import com.online.shoppingbackend.dao.CategoryDAO;
import com.online.shoppingbackend.dao.ProductDAO;
import com.online.shoppingbackend.dto.Category;
import com.online.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info("Inside PageController index method -INFO");
		logger.debug("Inside PageController index method -DEBUG");
		
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
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

	/*
	 * @RequestMapping(value = "/allproduct") public ModelAndView allproducts()
	 * { ModelAndView mv = new ModelAndView("page"); mv.addObject("title",
	 * "All Product"); mv.addObject("userClickAllproducts", true); return mv; }
	 */

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContactus", true);
		return mv;
	}

	/*
	 * method to load all products
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("allproduct", productDAO.list());
		mv.addObject("userClickAllproducts", true);
		return mv;
	}

	/*
	 * method to load category wise product
	 */
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showAllCategoryProducts(@PathVariable("id") int id)  {
		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the category product object
		mv.addObject("category", category);

		// passing the list of product based on the category
		mv.addObject("productByCategory",
				productDAO.listActiveProductsByCategory(id));
		mv.addObject("userClickCategoryproducts", true);
		return mv;
	}

	/*
	 * view single product
	 */

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");

		// ProductDAO to fetch single Product
		Product product = productDAO.get(id);
		
		//To handle product not found exceptioin
		
		if(product ==null) throw new ProductNotFoundException();
			
		// update the viewed product count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		//---------------------------------
		mv.addObject("title", product.getName());

		mv.addObject("product", product);
		
		mv.addObject("userClickSingleproducts", true);
		return mv;
	}

}
