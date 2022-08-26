package com.jerry.ers.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns = {"/home/","/login","/register","/reimbursement/*", "/users/*" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(FrontController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/ErsProject1/", "");

		logger.info("User trying to access endpoint: " + URI);

		switch (URI) {
		case "login":
			logger.info("User is logging in...");
			RequestHelper.processLogin(request, response);
			break;
		case "register":
			logger.info("User is registering for new Account...");
			RequestHelper.processRegistration(request, response);
			break;
		case "reimbursement/new":
			logger.info("User is trying to make a new reimbursement request...");
			RequestHelper.processCreateNewReim(request, response);
			break;
		default:
			logger.warn("No path present: " + URI);
			break;
		}
	}

}
