package com.example.demo.vo;

import lombok.Data;

@Data
public class product {
	private Integer id;
	private String productno;
	private String productname;
	private Integer price;
	public product(String productno, String productname, Integer price) {
		super();
		this.productno = productno;
		this.productname = productname;
		this.price = price;
	}
	public product() {
		super();
		// TODO Auto-generated constructor stub
	}
}