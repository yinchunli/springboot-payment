package com.yingchun.tsys.assignment.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerName implements Serializable{
	@JsonProperty("first")
	private String firstName;
	@JsonProperty("middle")
	private String middleName;
	@JsonProperty("last")
	private String lastName;
}
