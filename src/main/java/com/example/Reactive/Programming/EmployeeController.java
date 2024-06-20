package com.example.Reactive.Programming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository erepo;
	@RequestMapping("/test")
	public String test()
	{
		return "this is test for reactive programming";
	}
	@PostMapping("/save")
	public Mono<Employee> save(@RequestBody Employee cmp)
	{
		//Employee cmp=new Employee();
		return erepo.save(cmp);
	  
	
		}
	@GetMapping(value="/all",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee>allData()
	{
		Flux<Employee> flux=erepo.findAll().log();
		return flux;
	}
	@GetMapping(value="/id/{id}",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	Mono<Employee>byid(@PathVariable int id)
	{
		return erepo.findById(id);
	}
	@GetMapping(value="/name/{name}",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Employee>byname(@PathVariable String name)
	{
		return erepo.findByName(name);
	}
	@DeleteMapping("del/{id}")
    public Mono<Void> del(@PathVariable("id") int id){
        return erepo.deleteById(id);
	}


