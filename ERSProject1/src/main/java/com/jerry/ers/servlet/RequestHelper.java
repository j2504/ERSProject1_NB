package com.jerry.ers.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.ers.dao.ReimbursementDAOImplementation;
import com.jerry.ers.dao.UserDAOImplementation;
import com.jerry.ers.models.UserRoles;
import com.jerry.ers.models.Users;
import com.jerry.ers.services.ReimbursementService;
import com.jerry.ers.services.ReimbursementServiceImplementation;
import com.jerry.ers.services.UserService;
import com.jerry.ers.services.UserServiceImplementation;

/**
 * Servlet implementation class RequestHelper
 */
@WebServlet("/RequestHelper")
public class RequestHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RequestHelper.class);

	private static UserService userService = new UserServiceImplementation(new UserDAOImplementation());
	private static ReimbursementService reimService = new ReimbursementServiceImplementation(
			new ReimbursementDAOImplementation());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("In RequestHelper - processLogin() started");

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		logger.info("Request body for login is: " + body);

		String[] info = body.replaceAll("\\{", "").replaceAll("\"", "").replaceAll("}", "").split(",");
		List<String> values = new ArrayList<>();

		for (String pair : info) {
			logger.info("Original body K/V pair: " + pair.trim());
			String valOnly = pair.substring(pair.indexOf(":") + 1).trim();
			logger.info("Going into values arraylist --> " + valOnly);
			values.add(valOnly);
		}

		logger.info("User information extracted is: " + values.toString());

		// make my temp user for before the service call
		// NOTE: this step is not needed if you do not need the full Java object

		// make the service method call
		boolean isLoggedIn = userService.login(values.get(0), values.get(1));

		PrintWriter pw = response.getWriter();
		ObjectMapper om = new ObjectMapper();

		// create the response
		if (isLoggedIn == true) {
			response.setContentType("application/json");
			response.setStatus(200);

			// now that we have a successfully logged in user, we must keep track on their
			// session requests
			// therefore we will be adding a HTTP cookie as a response header

			// This cookie can then be used with future, subquent requests as it will hold
			// the user's information within its
			// header info
			Users target = userService.getUserByUsername(values.get(0));
			response.addCookie(new Cookie("Current-User", target.getUserName()));

			// adding JSON to response
			String json = target.getUserName() + " was successfully able to log into application.";
			om.writeValueAsString(json);
			pw.print(json);
			response.setStatus(200);
			logger.info("Login successful - returning cookie in response");

		} else {
			response.setStatus(401); // UNAUTHORIZED STATUS CODE = 401
			pw.println("Username and/or password didn't match what was on file. Please try again.");
		}
		logger.info("In RequestHelper - processLogin() ended");
	}

	@SuppressWarnings("deprecation")
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		logger.info("In RequestHelper - processRegistration() started");
		int id = 0;
		UserRoles ur;
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		logger.info("Request body for registration is: " + body);

		// Q: How to split body string up into different info (name, job title,
		// hiredate)

		String[] info = body.replaceAll("\\{", "").replaceAll("\"", "").replaceAll("}", "").split(",");
		List<String> values = new ArrayList<>();

		// each element in the info array, I can calling them pair in this for loop
		// in the loop, appending/adding a string to the values arraylist
		// in that string, we trim the string to look only for the value from the
		// request body

		// ex. You enter the following URL in Postman:
		// http://localhost:9001/HelloServlets/register
		// body would have this format: { "name": "Sam Rose", "jobTitle": "Chef",
		// "hiredate": "2019-11-18"}
		// info[]: ["name: bob", "jobTitle: worker", "hiredate: 2022-08-18"]
		// ex. name=bob
		// result: values["name:bob", etc.]

		for (String pair : info) {
			logger.info("Original body K/V pair: " + pair.trim());
			String valOnly = pair.substring(pair.indexOf(":") + 1).trim();
			logger.info("Going into values arraylist --> " + valOnly);
			values.add(valOnly); // here, I trimmed each string in the body to be just displaying the value
			// aka removed the extra characters and key from the string. Then added it to
			// the values arraylist
		}

		// 3. put that information into a temporary User object before making the
		// service method call
		logger.info("User information extracted is: " + values.toString());

		// a. set the content type of my response to return to the browser
		response.setContentType("application/json");

		// b. here is where we make the service method call
		String username = values.get(0);
		String password = values.get(1);
		String firstName = values.get(2);
		String lastName = values.get(3);
		String email = values.get(4);
		if(!password.equalsIgnoreCase("admin")) {
		 ur = new UserRoles("Employee");
		}else {
			ur = new UserRoles("ADMIN");
		}
		Users target = new Users(username, password, firstName, lastName, email,ur);
		logger.info("Target user: " + target);

		// 4. do the service method call
		id = userService.newAccount(target);

		// convert our response into JSON using Jackson Databind
		PrintWriter pw = response.getWriter();

		// 5. write the response that is returning to the client
		if (id != 0) {
			target.setUser_id(id);

			// this comes from Jackson Databind
			ObjectMapper om = new ObjectMapper();

			// now converted our User object into a JSON string that will be added to the
			// response
			String json = om.writeValueAsString(target);

			// adding JSON to response
			pw.println(json);

			response.setStatus(200);
			logger.info("New user info: " + target);
		} else {
			// if userId is 0, that means that request was successful but no new resource
			// was made! (status code of 204)
			response.setStatus(204, "Failed to add account in RequestHelper");
			pw.println("Sorry, system failure. Please try again later.");
		}

		logger.info("In RequestHelper - processRegistration() ended");

	}

	public static void processCreateNewReim(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		logger.info("In RequestHelper - processCreateNewAccount() started");
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();
		logger.info("In RequestHelper - processCreateNewAccount() ended");

	}

}
