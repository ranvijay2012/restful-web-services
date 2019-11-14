package com.neeraj.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

	@Autowired
	private MessageSource messageSource;

	// using String return
	@GetMapping(path = "/hello-world")
	public String helloWord() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-i18")
	public String helloWordI18(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
//	@GetMapping(path = "/hello-world-i18")
//	public String helloWordI18() {
//		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
//		//return "Good morning";
//	}

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
