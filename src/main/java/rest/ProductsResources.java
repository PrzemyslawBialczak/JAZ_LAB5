package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Product;

@Path("/products")
@Stateless
public class ProductsResources {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product product) {
		em.persist(product);
		return Response.ok(product.getId()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Product result = productById(id);
		
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p){
		Product result = productById(id);
		
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setPrice(p.getPrice());
		result.setCategory(p.getCategory());
		
		em.persist(result);
		return Response.ok().build();
	}
	

	
	private Product productById(int id) {
		return em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
	}

}
