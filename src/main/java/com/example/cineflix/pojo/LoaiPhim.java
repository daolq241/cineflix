package com.example.cineflix.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoaiPhim {
	private long id;
	
	@JsonProperty("tenLoai")
	private String tenLoai;
	
	@JsonProperty("hinh_anh")
	private String hinhAnh;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	
}
