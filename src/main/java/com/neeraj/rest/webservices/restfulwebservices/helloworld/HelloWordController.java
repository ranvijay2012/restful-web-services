package com.neeraj.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

	// using String return
	@GetMapping(path = "/hello-world")
	public String helloWord() {
		return "Hello World";
	}

	// using Bean return
	@GetMapping(path = "/hello-world-bean")
	public HelloWordBean helloWordBean() {
		return new HelloWordBean("Hello World");
	}

	// using Path vaiable return
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWordBean helloWordPathVariable(@PathVariable String name) {
		return new HelloWordBean(String.format("Hello World, %s", name));
	}

}
