package dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;


public interface ProductDAO<T, ID extends Serializable> extends JpaRepository<Product, Long> {
	
	Product save(Product product);
	
	Product findById(long id);
	
}
