package com.ecom.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="person_table")
@Inheritance(strategy=InheritanceType.JOINED) //table per subclass
public class Person {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "personID", unique=true, nullable = false)
int personId;

@Column(name = "firstName")
String firstName;


@Column(name = "lastName")
String lastName;


public Person(){
	
}


public int getPersonId() {
	return personId;
}


public void setPersonId(int personId) {
	this.personId = personId;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}





}
