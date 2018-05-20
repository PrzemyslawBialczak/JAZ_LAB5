package rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Product;

@Path("/search")
public class SearchResources {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Path("/price/{rangeFrom}/{rangeTo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByPrice(@PathParam("rangeFrom") int rangeFrom, @PathParam("rangeTo") int rangeTo) {
		List<Product> resultByPrice = new ArrayList<Product>();
		
		for (Product p : getAll()) {
			
			double priceOfProduct = p.getPrice();
			
			if (priceOfProduct >= rangeFrom && priceOfProduct <= rangeTo) {
				resultByPrice.add(p);
			}
		}
		
		return resultByPrice;
	}
	
	@GET
	@Path("/name/{searchByName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByName(@PathParam("searchByName") String searchByName) {
		List<Product> resultByName = new ArrayList<Product>();
		
		for (Product p : getAll()) {
			if (p.getName().contains(searchByName)) {
				resultByName.add(p);
			}
		}
		
		return resultByName;
	}
	
	@GET
	@Path("/category/{searchByCategory}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByCategory(@PathParam("searchByCategory") String searchByCategory) {
		List<Product> resultByCategory = new ArrayList<Product>();
		
		for (Product p : getAll()) {
			if (p.getCategory().equals(searchByCategory)) {
				resultByCategory.add(p);
			}
		}
		
		return resultByCategory;
	}
	
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
}
