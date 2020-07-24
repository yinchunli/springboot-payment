package com.yingchun.tsys.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {
 * @author yli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="h2customers")
@Table(name = "h2_customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@JsonProperty("name")
	CustomerName customerName;
	@JsonProperty("phone")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "customer_id", insertable = false, updatable = false)
	List<CustomerPhone> customerPhones=new ArrayList<CustomerPhone>();
	@JsonProperty("address")
	CustomerAddress customerAddress;
	@JsonProperty("email")
	String customerEmail;
}