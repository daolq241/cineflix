package com.example.cineflix.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.cineflix.pojo.LoaiPhim;
import com.example.cineflix.pojo.Phim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping("")
	public ModelAndView home() {
		ModelAndView andView = new ModelAndView("home");

//		int soA = 10;
//		int soB = 20;
//		int soC = 7;
//		int soD = 30;
//		int soF = 99;
//		
//		int tong = tinhTong(soA, soB);
//		
//		andView.addObject("ketqua", tong);	
		
		String response = getDataTypeGet("http://localhost:8080/phim");
		System.out.println(response);
		
		// Chuyển data nhận từ api --> object (jackson)
		ObjectMapper mapper = new ObjectMapper();
		try {
			Phim[] phims = mapper.readValue(response, Phim[].class);
			andView.addObject("phims", phims);
//			for (Phim phim : phims) {
//				System.out.println(phim.getTen_phim());
//			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get data Danh Muc
		String responseCategory = getDataTypeGet("http://localhost:8080/loaiphim");
		ObjectMapper mapperCategory = new ObjectMapper();
		try {
			LoaiPhim[] loaiPhims  = mapperCategory.readValue(responseCategory, LoaiPhim[].class);
			andView.addObject("loaiPhims", loaiPhims);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return andView;
	}

	private String getDataTypeGet(String url) {
		StringBuilder responeData = new StringBuilder();

		// Khai báo sử dụng đường dẫn
		try {
			URL newUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) newUrl.openConnection();
			connection.setRequestMethod("GET");

//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// Lấy input stream (dữ liệu trả về từ URL) từ đường dẫn
			InputStream inputStream = connection.getInputStream();
			// Đọc dữ liệu từ input stream
			InputStreamReader reader = new InputStreamReader(inputStream);
			/*
			 * Tạo buffered để tạo bộ nhớ đệm (tăng tốc độ đọc), ví dụ youtube load từng
			 * phần của video, nếu mất mạng vẫn xem đc nội dung đã load, có mạng lại thì
			 * load thiếp
			 */
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			//Line đại diện cho từng dòng data đọc được
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				responeData.append(line);
			}
			
			bufferedReader.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responeData.toString();

	}

//	public int tinhTong(int soA, int soB) {
//		int tong = soA + soB;
//		return tong;
//	}
}
