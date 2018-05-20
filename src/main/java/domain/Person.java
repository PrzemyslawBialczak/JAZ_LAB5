package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="person.all", query="SELECT p FROM Person p"),
	@NamedQuery(name="person.id",query="FROM Person p WHERE p.id=:personId")
})
public class Person {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private List<Car> cars =new ArrayList<Car>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@XmlTransient
	@OneToMany(mappedBy="person")
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
