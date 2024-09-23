package com.controllers;

import com.model.Admin;
import com.model.UnderWriter;

public class Constants {
	
	
	public static final String DERBY_SYS_PREALOCATOR = "derby.language.sequence.preallocator";
	public static final String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	public static final String DB_URL = "jdbc:derby:D:\\Users\\2726157\\VehicleInsuranceDB;create=true";
	
	public static final String ADMIN_DB_NAME = "ADMIN_TABLE";
	public static final String UW_DB_NAME = "UNDERWRITER";
	public static final String INS_DB_NAME = "INSURANCE";
	
	public static final String ADMIN_NAME = "USERNAME";
	public static final String ADMIN_PASSWORD = "PASSWORD";
	
	public static final String UW_ID = "UNDERWRITERID";
	public static final String UW_NAME = "USERNAME";
	public static final String UW_DOB = "DOB";
	public static final String UW_DOJ = "DOJ";
	public static final String UW_PASSWORD = "PASSWORD";
	
	public static final String INS_POLICYNO = "POLICYNO";
	public static final String INS_VEHICLENO = "VEHICLENO";
	public static final String INS_VEHICLETYPE = "VEHICLETYPE";
	public static final String INS_CUSTOMERNAME = "CUSTOMERNAME";
	public static final String INS_ENGINENO = "ENGINENO";
	public static final String INS_CHASSISNO = "CHASSISNO";
	public static final String INS_PHONENO = "PHONENO";
	public static final String INS_TYPE = "TYPE";
	public static final String INS_PREMIUMAMT = "PREMIUMAMT";
	public static final String INS_FROMDATE = "FROMDATE";
	public static final String INS_TODATE = "TODATE";
	public static final String INS_UW_ID = "UNDERWRITERID";
	

}
