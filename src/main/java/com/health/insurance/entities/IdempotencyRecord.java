package com.health.insurance.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="aig.idempotency_keys")
public class IdempotencyRecord {
	
	 @Id
	 private String id;
	 private String requestHash;
	 @Lob
	 private String responseBody;
	 private LocalDateTime createdAt = LocalDateTime.now();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRequestHash() {
		return requestHash;
	}
	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public IdempotencyRecord(String id, String requestHash, String responseBody, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.requestHash = requestHash;
		this.responseBody = responseBody;
		this.createdAt = createdAt;
	}
	public IdempotencyRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "IdempotencyRecord [id=" + id + ", requestHash=" + requestHash + ", responseBody=" + responseBody
				+ ", createdAt=" + createdAt + "]";
	}

}
