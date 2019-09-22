package com.neeraj.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

	@GetMapping(path = "/hello-world")
	public String helloWord() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWordBean helloWordBean() {
		return new HelloWordBean("Hello World");
	}

}
