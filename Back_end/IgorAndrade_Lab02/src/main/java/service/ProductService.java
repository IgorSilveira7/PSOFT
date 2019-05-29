package service;

import org.springframework.stereotype.Service;

import dao.ProductDAO;
import model.Product;

@Service
public class ProductService {

	private final ProductDAO productDAO;
	
	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public Product create(Product product) {
		return productDAO.save(product);
	}
	
	public Product update(Product productToUpdate){
		Product product = this.productDAO.findById(productToUpdate.getId());
		
		if (product == null) {
			//exceção
		}
		
		return this.productDAO.save(productToUpdate);
	}
	
	public void delete(long id) {
		this.productDAO.deleteById(id);
	}
	
	public Product findById(long id) {
		return this.productDAO.findById(id);
	}
	
}
