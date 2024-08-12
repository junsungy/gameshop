package com.shinhan.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class CustDTO {
	private String username;
	private String pw;
	private int credit;
	private int is_admin;
}
