package com.neeraj.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import com.neeraj.rest.webservices.restfulwebservices.data.UserDaoService;
import com.neeraj.rest.webservices.restfulwebservices.dto.HelloBean;
import com.neeraj.rest.webservices.restfulwebservices.dto.User;
import com.neeraj.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class AppController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/hello")
    public String helloWord() {
        return "Hello World";
    }

    /**
     * @param locale
     * @return
     */
    @GetMapping(path = "/hello-i18")
    public String helloWordI181(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    /**
     * @return
     */
    @GetMapping(path = "/hello-world-i18")
    public String helloWordI18() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    /**
     * @return
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloBean helloWordBean() {
        return new HelloBean("Hello World Bean");
    }

    /**
     * @param name
     * @return
     */
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloBean helloWordPathVariable(@PathVariable String name) {
        return new HelloBean(String.format("Hello World, %s", name));
    }

    /**
     * @return
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.findAll();

    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("Id - "+id);
        }
        return user;
    }

//	@GetMapping("/users/{id}")
//	public Resource<User> retrieveUser(@PathVariable int id) {
//		User user = service.findOne(id);
//		if(user == null) {
//			throw new UserNotFoundException("Id - "+id);
//		}
////		HATEOAS
////		retriveAllUsers
////		"link" - "all-users", SERVER_PATH + "users"
//		Resource<User> resource = new Resource<User>(user);
//		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).retriveAllUsers());
//		resource.add(builder.withRel("all-users"));
//		return resource;
//
//	}

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("Id - " + id);
        }
    }


}
