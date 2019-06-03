package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dao.ProductDAO;
import exception.ProductNotFoundException;
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
	
	public Product partialUpdate(@RequestBody Product product) throws ProductNotFoundException {
		Product p = this.productDAO.findById(product.getId());
		
		if(p == null) {
			throw new ProductNotFoundException("Produto não encontrado");
			
		}
		
		if(product.getName() != null) {
			p.setName(product.getName());
		}
		
		if(product.getDescription() != null) {
			p.setDescription(product.getDescription());
		}
		
		if(product.getPrice() > 0) {
			p.setPrice(product.getPrice());
		}
		
		return this.productDAO.save(p);
	}
	
	public List<Product> getAll(){
		return this.productDAO.findAll();
	}
	
	public void delete(long id) {
		this.productDAO.deleteById(id);
	}
	
	public Product findById(long id) {
		return this.productDAO.findById(id);
	}
	
}
