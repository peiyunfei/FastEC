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
 * ËÑË÷
 * 
 * @×÷Õß ÅáÔÆ·É
 *
 */
@WebServlet("/GoodsDetailServlet")
public class GoodsDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goods detail servlet doPost");
		Integer.parseInt(request.getParameter("goods_id"));
		OutputStream out = response.getOutputStream();
		String path = getServletContext().getRealPath("/") + "file/goods_detail_data_1.json";
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
