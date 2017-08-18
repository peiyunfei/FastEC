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
 * Ê×Ò³
 * 
 * @×÷Õß ÅáÔÆ·É
 *
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("index servlet doPost");
		String path = "";
		OutputStream out = response.getOutputStream();
		String pageIndex = request.getParameter("pageIndex");
		if (pageIndex != null) {
			int parseInt = Integer.parseInt(pageIndex);
			if (parseInt > 1) {
				path = getServletContext().getRealPath("/") + "file/index_2_data.json";
			}
		} else {
			path = getServletContext().getRealPath("/") + "file/index_data.json";
		}
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
