package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import exception.ProductNotFoundException;
import model.Product;
import service.ProductService;

@RestController
@RequestMapping({"/v1/products"})
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Product> findById(@PathVariable long id) {
		Product product = this.productService.findById(id);
		
		if (product == null) {
			throw new ProductNotFoundException("Product not found");
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product newProduct = this.productService.create(product);
		
		if (newProduct == null) {
			throw  new InternalError("Something went wrong");
		}
		
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<Product>> getAll() {
		try {
			return new ResponseEntity<>(this.productService.getAll(), HttpStatus.OK);
			
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}
	
	@PatchMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Product> PartialUpdate(@RequestBody Product product){		
		if(product != null) {
			return new ResponseEntity<>( this.productService.partialUpdate(product), HttpStatus.OK);
		} else {
			throw new InternalError("You did something wrong");
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> delete(@PathVariable long id) {
		try {
			this.productService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}
	
	@PutMapping(value = "/")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		try {
			Product updated = this.productService.update(product);
			return new ResponseEntity<Product>(updated, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}
	
}
