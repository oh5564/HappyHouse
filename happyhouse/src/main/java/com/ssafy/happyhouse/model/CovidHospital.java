package com.ssafy.happyhouse.model;

public class CovidHospital {
	//private int hospitalId; 
	private String hospitalName; //병원이름 - yadmNm
	private String phone; //전화번호 - telno
	private String address; //주소 - addr
	private float lat; //세계지구 x 좌표 - XPosWgs84
	private float lng; //세계지구 y 좌표 - YPosWgs84
	private String isRat; //신속항원검사 가능여부 - ratPsblYn
	private String isClinic; // 호흡기 전담 클리닉 여부 - rprtWorpClicFndtTgtYn
	private String isPcr; //pcr 가능여부 - pcrPsblYn 
	private String sigungu; //시군구명 - sgguNm
	
	
	//------Constructor-----------------------------------------------
	
	public CovidHospital() {}

	/**
	 * @param hospitalName: 병원이름 - yadmNm
	 * @param address: 주소 - addr
	 * @param phone: 전화번호 - telno
	 * @param lat: 세계지구 x 좌표 - XPosWgs84
	 * @param lng: 세계지구 y 좌표 - YPosWgs84
	 * @param isRat: 신속항원검사 가능여부 - ratPsblYn
	 * @param isClinic: 호흡기 전담 클리닉 여부 - rprtWorpClicFndtTgtYn
	 * @param isPcr: pcr 가능여부 - pcrPsblYn 
	 */
	public CovidHospital(String hospitalName, String phone,  String address, float lat, float lng, String isRat,
			String isClinic, String isPcr, String sigungu) {
		super();
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.isRat = isRat;
		this.isClinic = isClinic;
		this.isPcr = isPcr;
		this.sigungu = sigungu;
	}
	
	//------Getter & Setter-----------------------------------------------

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the lat
	 */
	public float getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(float lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public float getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(float lng) {
		this.lng = lng;
	}

	/**
	 * @return the isRat
	 */
	public String getIsRat() {
		return isRat;
	}

	/**
	 * @param isRat the isRat to set
	 */
	public void setIsRat(String isRat) {
		this.isRat = isRat;
	}

	/**
	 * @return the isClinic
	 */
	public String getIsClinic() {
		return isClinic;
	}

	/**
	 * @param isClinic the isClinic to set
	 */
	public void setIsClinic(String isClinic) {
		this.isClinic = isClinic;
	}

	/**
	 * @return the isPcr
	 */
	public String getIsPcr() {
		return isPcr;
	}

	/**
	 * @param isPcr the isPcr to set
	 */
	public void setIsPcr(String isPcr) {
		this.isPcr = isPcr;
	}
	

	/**
	 * @return the sigungu
	 */
	public String getSigungu() {
		return sigungu;
	}

	/**
	 * @param sigungu the sigungu to set
	 */
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	//-------toString------------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(hospitalName);
		builder.append(String.format("%15s", ""));
		builder.append(phone);
		builder.append(String.format("%10s", ""));
		builder.append(address);
		builder.append(String.format("%10s", ""));
		builder.append(lat);
		builder.append(String.format("%5s", ""));
		builder.append(lng);
		builder.append(String.format("%5s", ""));
		builder.append(isRat);
		builder.append(String.format("%5s", ""));
		builder.append(isClinic);
		builder.append(String.format("%5s", ""));
		builder.append(isPcr);
		builder.append(String.format("%5s", ""));
		builder.append(sigungu);
		return builder.toString();
	}
	
	
}
