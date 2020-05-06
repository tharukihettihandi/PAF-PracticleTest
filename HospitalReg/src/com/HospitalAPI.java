package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HospitalReg;

/**
 * Servlet implementation class HospitalAPI
 */
@WebServlet("/HospitalAPI")
public class HospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HospitalReg HosObj = new HospitalReg();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HospitalAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// NOT USED
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = HosObj.insertHos(request.getParameter("hosRegNo"), 
				request.getParameter("hosName"),
				request.getParameter("hosAddress"),
				request.getParameter("hosPhone"),
				request.getParameter("hosEmail"),
				request.getParameter("Departments"));
		        
		response.getWriter().write(output);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = HosObj.updateHos(paras.get("hidhosIDSave").toString(),
				paras.get("hosRegNo").toString(),
				paras.get("hosName").toString(), 
				paras.get("hosAddress").toString(),
				paras.get("hosPhone").toString(),
				paras.get("hosEmail").toString(),
				paras.get("Departments").toString());
		      
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = HosObj.deleteHos(paras.get("hosID").toString());
	  
		response.getWriter().write(output);
	}


	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}

		catch (Exception e) {

		}
		return map;
	}
}
