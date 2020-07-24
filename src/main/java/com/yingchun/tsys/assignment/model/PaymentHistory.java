package com.yingchun.tsys.assignment.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="paymentHistory")
@Table(name = "paymentHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
    Integer paymentHistoryId;
	@JsonProperty("customerId")
	Integer customerId;
	@JsonProperty("year")
	Integer year;
	@JsonProperty("month")
	Integer month;
	@JsonProperty("amount")
	Float amount;
	String comments;
	String action;
    Date lastUpdatedStamp;
    Date lastUpdatedTxStamp;
    Date createdStamp;
    Date createdTxStamp;
}
