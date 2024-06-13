package com.example.Reactive.Programming;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee,Integer> {

	Flux<Employee> findByName(String name);

}
