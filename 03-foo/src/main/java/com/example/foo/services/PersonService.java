package com.example.foo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.foo.model.Person;

@Service
public class PersonService {
	private final AtomicLong counter =  new AtomicLong();
	
	public Person create(Person person) {
		
		
		
		return person;
	}
	
	public Person update(Person person) {
		
		
		
		return person;
	}
	public void delete(String id) {
		
		
	}
	
	public Person findById(String id) {
		
		Person person =  new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("João");
		person.setLastName("da Silva");
		person.setGender("M");
		person.setAddress("Rua a");
		
		return person;
	}
	
	public List<Person> findAll() {
		
		List<Person> persons =  new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = new Person();
			person.setId(counter.incrementAndGet());
			person.setFirstName("João");
			person.setLastName("da Silva");
			person.setGender("M");
			person.setAddress("Rua a");
			persons.add(person);
		}
		
		
		
		return persons;
	}
}
