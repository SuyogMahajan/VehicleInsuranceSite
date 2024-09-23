package com.model;

import java.sql.Date;

public class Insurance {

	private static int policyCnt = 1;
	
	private int underwriter_id;
	private int PolicyNo;
	private String VehicleNo;
	private String VehicleType;
	private String CustomerName;
	private int EngineNo;
	private int ChasisNo;
	private String Type;
	private long PhoneNo;
	private double PremiumAmnt;
	private Date FromDate;
	private Date ToDate;
	
	public Insurance(int underwriter_id, String vehicleNo, String vehicleType, String customerName, int engineNo, int chasisNo, String type, Long phoneNo, Date fromDate) {
		this(underwriter_id, policyCnt, vehicleNo, vehicleType, customerName, engineNo, chasisNo, type, phoneNo, type.equals("Full Insurance") ? 20000 : 10000, fromDate, Date.valueOf(fromDate.toLocalDate().plusYears(1)));
		policyCnt++;
	}
			
	public Insurance(int underwriter_id,int insuranceId, String vehicleNo, String vehicleType, String customerName, int engineNo,
			
			int chasisNo, String type, Long phoneNo,double premiumAmnt ,Date fromDate, Date toDate) {
			super(); 
			this.underwriter_id = underwriter_id;
			VehicleNo = vehicleNo;
			VehicleType = vehicleType;
			CustomerName = customerName;
			EngineNo = engineNo;
			ChasisNo = chasisNo;
			Type = type;
			PhoneNo = phoneNo;
			PremiumAmnt = premiumAmnt;
			FromDate = fromDate;
			ToDate = toDate;
			PolicyNo = insuranceId;
			
		}
	

	public Insurance(Insurance t) {
		this.underwriter_id = t.getUnderwriter_id();
		this.VehicleNo = t.getVehicleNo();
		this.VehicleType = t.getVehicleType();
		CustomerName = t.getCustomerName();
		EngineNo = t.getEngineNo();
		ChasisNo = t.getChasisNo();
		Type = t.getType();
		PhoneNo = t.getPhoneNo();
		PremiumAmnt = t.getPremiumAmnt();
		FromDate = t.getFromDate();
		ToDate = t.getToDate();
		PolicyNo = Insurance.policyCnt++;
	}

	public int getUnderwriter_id() {
		return underwriter_id;
	}



	public void setUnderwriter_id(int underwriter_id) {
		this.underwriter_id = underwriter_id;
	}



	public int getPolicyNo() {
		return PolicyNo;
	}



	public void setPolicyNo(int policyNo) {
		PolicyNo = policyNo;
	}



	public String getVehicleNo() {
		return VehicleNo;
	}



	public void setVehicleNo(String vehicleNo) {
		VehicleNo = vehicleNo;
	}



	public String getVehicleType() {
		return VehicleType;
	}



	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}



	public String getCustomerName() {
		return CustomerName;
	}



	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}



	public int getEngineNo() {
		return EngineNo;
	}



	public void setEngineNo(int engineNo) {
		EngineNo = engineNo;
	}



	public int getChasisNo() {
		return ChasisNo;
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}



	public void setChasisNo(int chasisNo) {
		ChasisNo = chasisNo;
	}



	public long getPhoneNo() {
		return PhoneNo;
	}

	
	public void setPhoneNo(int phoneNo) {
		PhoneNo = phoneNo;
	}



	public double getPremiumAmnt() {
		return PremiumAmnt;
	}



	public void setPremiumAmnt(double premiumAmnt) {
		PremiumAmnt = premiumAmnt;
	}



	public Date getFromDate() {
		return FromDate;
	}



	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}



	public Date getToDate() {
		return ToDate;
	}



	public void setToDate(Date toDate) {
		ToDate = toDate;
	}

	@Override
	public String toString() {
		return "Insurance [underwriter_id=" + underwriter_id + ", PolicyNo=" + PolicyNo + ", VehicleNo=" + VehicleNo
				+ ", VehicleType=" + VehicleType + ", CustomerName=" + CustomerName + ", EngineNo=" + EngineNo
				+ ", ChasisNo=" + ChasisNo + ", Type=" + Type + ", PhoneNo=" + PhoneNo + ", PremiumAmnt=" + PremiumAmnt
				+ ", FromDate=" + FromDate + ", ToDate=" + ToDate + "]\n";
	}


		
	
}
