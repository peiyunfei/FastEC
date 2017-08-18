package com.pyf.ec.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getSession().getId());
		Part part = request.getPart("file");
		System.out.println(part.getSize());
		// String username = request.getParameter("username");
		// String userage = request.getParameter("userage");
		// System.out.println("username=" + username + ",userage=" + userage);
		String dir = getServletContext().getRealPath("image");
		File file = new File(dir, "avatar.jpg");
		FileOutputStream fos = new FileOutputStream(file);
		InputStream inputStream = part.getInputStream();
		int len = -1;
		byte[] buff = new byte[1024];
		while ((len = inputStream.read(buff)) != -1) {
			fos.write(buff, 0, len);
		}
		fos.close();
		inputStream.close();
	}

}
