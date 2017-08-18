package com.pyf.ec.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单列表
 * 
 * @作者 裴云飞
 *
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("order list servlet doPost");
		String type = request.getParameter("type");
		String path = "";
		switch (type) {
		case "all":
			path = getServletContext().getRealPath("/") 
			+ "file/order_list.json";
			break;
		case "pay":
			path = getServletContext().getRealPath("/") 
			+ "file/order_list_pay.json";
			break;
		case "receive":
			path = getServletContext().getRealPath("/") 
			+ "file/order_list_receive.json";
			break;
		case "evaluate":
			path = getServletContext().getRealPath("/") 
			+ "file/order_list_evaluate.json";
			break;
		case "after_market":
			path = getServletContext().getRealPath("/") 
			+ "file/order_list_after_market.json";
			break;
		default:
			break;
		}
		OutputStream out = response.getOutputStream();
		File file = new File(path);
		FileInputStream stream = new FileInputStream(file);
		int count = -1;
		byte[] buffer = new byte[1024];
		while ((count = stream.read(buffer)) != -1) {
			out.write(buffer, 0, count);
			out.flush();
		}
		stream.close();
		out.close();
	}
}
