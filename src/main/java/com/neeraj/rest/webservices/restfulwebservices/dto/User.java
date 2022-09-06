package com.neeraj.rest.webservices.restfulwebservices.dto;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	private Integer id;
	@Size(min = 2, message = "size must be between 2 and 10")
	private String name;
	@Past
	private Date birthDate;
}
