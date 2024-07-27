package com.Managment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class UserList extends HttpServlet {
	private static final String QUERY = "select * from user";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root",
					"khan");
			PreparedStatement ps = connection.prepareStatement(QUERY);
			ResultSet rs = ps.executeQuery();
			out.println("<html><head><title>User List</title>");
			out.println("<style>");
			out.println(
					"body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
			out.println(
					".container { max-width: 900px; margin: auto; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; animation: fadeIn 1s ease-in-out; }");
			out.println(
					"h1 { text-align: center; color: #333; margin-bottom: 20px; font-size: 24px; animation: fadeInDown 1s ease-in-out; }");
			out.println(
					"table { width: 100%; border-collapse: collapse; margin-bottom: 20px; animation: fadeInUp 1s ease-in-out; }");
			out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
			out.println("th { background-color: #4CAF50; color: white; }");
			out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
			out.println(
					"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s, transform 0.3s; }");
			out.println("a:hover { background-color: #45a049; transform: scale(1.05); }");
			out.println(
					".action-link { padding: 5px 10px; margin: 0 5px; background-color: #007bff; color: white; border-radius: 5px; text-decoration: none; transition: background-color 0.3s, transform 0.3s; }");
			out.println(".action-link:hover { background-color: #0056b3; transform: scale(1.05); }");
			out.println(".action-delete { background-color: #dc3545; }");
			out.println(".action-delete:hover { background-color: #c82333; }");
			out.println("@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }");
			out.println(
					"@keyframes fadeInDown { from { opacity: 0; transform: translateY(-20px); } to { opacity: 1; transform: translateY(0); } }");
			out.println(
					"@keyframes fadeInUp { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }");
			out.println("</style></head><body>");
			out.println("<div class='container'>");
			out.println("<h1>User List</h1>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Email</th>");
			out.println("<th>Mobile</th>");
			out.println("<th>DOB</th>");
			out.println("<th>City</th>");
			out.println("<th>Gender</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td>");
				out.println("<td>" + rs.getString(5) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td>" + rs.getString(7) + "</td>");
				out.println("<td><a class='action-link' href='editurl?id=" + rs.getInt(1) + "'>Edit</a></td>");
				out.println("<td><a class='action-link action-delete' href='deleteurl?id=" + rs.getInt(1)
						+ "'>Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href='home.html'>Home</a>");
			out.println("</div></body></html>");
		} catch (Exception e) {
			out.println("<html><head><title>Error</title>");
			out.println("<style>");
			out.println(
					"body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
			out.println(
					".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; animation: fadeIn 1s ease-in-out; }");
			out.println("h1 { color: #f44336; font-size: 24px; animation: fadeInDown 1s ease-in-out; }");
			out.println(
					"a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s, transform 0.3s; }");
			out.println("a:hover { background-color: #e53935; transform: scale(1.05); }");
			out.println("</style>");
			out.println("</head><body>");
			out.println("<div class='container'><h1>" + e.getMessage() + "</h1>");
			out.println("<a href='home.html'>Home</a>");
			out.println("</div></body></html>");
			e.printStackTrace();
		}
		out.close();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
