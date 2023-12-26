package com.example.demo.vo;

import lombok.Data;

@Data
public class porder {
	private Integer id;
	private String name;
	private Integer A;
	private Integer B;
	private Integer C;
	
	public porder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public porder(String name, Integer a, Integer b, Integer c) {
		super();
		this.name = name;
		A = a;
		B = b;
		C = c;
	}

	
	
	
}
