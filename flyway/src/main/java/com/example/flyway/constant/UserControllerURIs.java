package com.example.flyway.constant;

public final class UserControllerURIs {
	
	public static final String ROOT = "/user";
	
	public static final String FIND_BY_ID = ROOT + "/{id}";
	public static final String FIND_ALL = ROOT;
	public static final String CREATE = ROOT;
	public static final String UPDATE = ROOT + "/{id}";
	public static final String DELETE = ROOT + "/{id}";

}
