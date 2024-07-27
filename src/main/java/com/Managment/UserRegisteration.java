package com.Managment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class UserRegisteration extends HttpServlet {
	private static final String QUERY = "insert into user (name, email, mobile, dob, city, gender) values (?,?,?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String name = req.getParameter("userName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root",
					"khan");
			PreparedStatement ps = connection.prepareStatement(QUERY);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			ps.setString(5, city);
			ps.setString(6, gender);

			int count = ps.executeUpdate();
			if (count == 1) {
				out.println("<html><head><title>User Registration</title>");
				out.println("<style>");
				out.println(
						"body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
				out.println(
						".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; }");
				out.println("h1 { color: #4CAF50; font-size: 24px; }");
				out.println(
						"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; }");
				out.println("a:hover { background-color: #45a049; }");
				out.println(
						"button { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; border: none; cursor: pointer; font-size: 16px; }");
				out.println("button:hover { background-color: #45a049; }");
				out.println("</style>");
				out.println("</head><body>");
				out.println("<div class='container'><h1>User Registered Successfully</h1>");
				out.println("<a href='home.html'>Home</a>");
				out.println("<button onclick=\"window.location.href='showdata'\">User List</button>");
				out.println("</div></body></html>");
			} else {
				out.println("<html><head><title>User Registration</title>");
				out.println("<style>");
				out.println(
						"body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
				out.println(
						".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; }");
				out.println("h1 { color: #f44336; font-size: 24px; }");
				out.println(
						"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; }");
				out.println("a:hover { background-color: #e53935; }");
				out.println(
						"button { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; border: none; cursor: pointer; font-size: 16px; }");
				out.println("button:hover { background-color: #e53935; }");
				out.println("</style>");
				out.println("</head><body>");
				out.println("<div class='container'><h1>User Not Registered</h1>");
				out.println("<a href='home.html'>Home</a>");
				out.println("<button onclick=\"window.location.href='showdata'\">User List</button>");
				out.println("</div></body></html>");
			}
		} catch (Exception e) {
			out.println("<html><head><title>Error</title>");
			out.println("<style>");
			out.println(
					"body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
			out.println(
					".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; }");
			out.println("h1 { color: #f44336; font-size: 24px; }");
			out.println("</style>");
			out.println("</head><body>");
			out.println("<div class='container'><h1>" + e.getMessage() + "</h1></div>");
			out.println("</body></html>");
			e.printStackTrace();
		}
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
