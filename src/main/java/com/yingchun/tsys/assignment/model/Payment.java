package com.yingchun.tsys.assignment.model;

import java.io.Serializable;
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
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="payment")
@Table(name = "payment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements Serializable{

	private static final long serialVersionUID = 13232L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	Integer paymentId;
	@JsonProperty("customerId")
	Integer customerId;
	@JsonProperty("year")
	Integer year;
	@JsonProperty("month")
	Integer month;
	@JsonProperty("amount")
	Float amount;	
	Integer paymentTypeId;
	Integer paymentMethodTypeId;
	Integer paymentMethodId;
	Integer paymentGatewayResponseId;
	Integer paymentPreferenceId;
	Integer partyIdFrom;
	Integer partyIdTo;
	Integer roleTypeIdTo;
	Integer statusId;
	Date effectiveDate;
	String comments;
    Date lastUpdatedStamp;
    Date lastUpdatedTxStamp;
    Date createdStamp;
    Date createdTxStamp;
}