package com.tmf.store.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_url")
public class ItemURL {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_url_id")
	private long itemUrlId;
	
	@Column(name = "url",columnDefinition = "TEXT")
	private String url;
	
	public ItemURL() {
		super();
	}

	public long getItemUrlId() {
		return itemUrlId;
	}

	public void setItemUrlId(long itemUrlId) {
		this.itemUrlId = itemUrlId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
