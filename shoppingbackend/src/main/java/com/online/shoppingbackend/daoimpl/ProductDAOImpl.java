package com.online.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.online.shoppingbackend.dao.ProductDAO;
import com.online.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * SINGLE
	 */
	@Override
	public Product get(int productId) {
		return sessionFactory.getCurrentSession().get(Product.class,
				Integer.valueOf(productId));
	}

	/*
	 * LIST
	 */
	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product", Product.class).getResultList();
	}

	/*
	 * INSERT
	 */
	@Override
	public boolean add(Product product) {

		try {
			sessionFactory
				.getCurrentSession()
					.persist(product);
			return true;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * update product
	 * */
	@Override
	public boolean update(Product product) {
		
		try{
			sessionFactory
				.getCurrentSession()
					.update(product);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	/*
	 * 1.delete the product
	 * 2.delete means setting product active as false
	 * 
	 * */
	@Override
	public boolean delete(Product product) {

		//seeting product active status is false
		product.setActive(false);
		
		try{
			sessionFactory
				.getCurrentSession()
					.update(product);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
				
	}

	/*
	 * Active Products
	 * */
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active=:active";
		return sessionFactory
					.getCurrentSession()
						.createQuery(selectActiveProducts,Product.class)
							.setParameter("active", true)
								.getResultList();
	}

	
	/*
	 * Active Products by category
	 * */
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String listActiveProductsByCategory = "FROM Product WHERE active=:active AND categoryId=:categoryId";
		return sessionFactory
					.getCurrentSession()
						.createQuery(listActiveProductsByCategory,Product.class)
							.setParameter("active", true)
								.setParameter("categoryId", categoryId)
									.getResultList();
	}

	/*
	 * Get Latest Active Products
	 * */

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String getLatestActiveProducts = "FROM Product WHERE active=:active ORDER BY id";
		return sessionFactory
					.getCurrentSession()
						.createQuery(getLatestActiveProducts, Product.class)
							.setParameter("active", true)
								.setFirstResult(0)
									.setMaxResults(count)
										.getResultList();
	}

}
