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

@WebServlet("/editurl")
public class UserEditForm extends HttpServlet {
    private static final String QUERY = "select * from user where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "khan");
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<html><head><title>User Edit Form</title>");
                out.println("<style>");
                out.println(
                        "body { font-family: 'Arial', sans-serif; background-color: #e9ecef; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; font-variant: small-caps; }");
                out.println(
                        ".container { max-width: 700px; width: 100%; padding: 40px; background: #ffffff; border-radius: 12px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15); border: 1px solid #e0e0e0; animation: fadeIn 1s ease-in-out; }");
                out.println(
                        "h1 { text-align: center; color: #333; margin-bottom: 30px; font-size: 32px; font-weight: bold; }");
                out.println(
                        "table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }");
                out.println("td { padding: 15px; border: 1px solid #e0e0e0; text-align: left; font-variant:small-caps; }");
                out.println("td:nth-child(even) { background-color: #f7f7f7; }");
                out.println(
                        "input[type='text'], input[type='email'], input[type='date'] { width: calc(100% - 22px); padding: 12px; border: 1px solid #ced4da;font-variant:small-caps; border-radius: 6px; box-sizing: border-box; font-size: 16px; }");
                out.println(
                        "input[type='submit'], button { background-color: #007bff;font-variant:small-caps; color: white; padding: 12px 20px; border: none; border-radius: 6px; font-size: 16px; cursor: pointer; transition: background-color 0.3s; margin-right: 10px; }");
                out.println(
                        "input[type='submit']:hover, button:hover { background-color: #0056b3; }");
                out.println(
                        "a { display: inline-block; padding: 12px 24px; background-color: #28a745; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; transition: background-color 0.3s; }");
                out.println("a:hover { background-color: #218838; }");
                out.println(
                        ".error { color: #dc3545; font-size: 18px; font-weight: bold; text-align: center; }");
                out.println(
                        "@media (max-width: 768px) { .container { padding: 20px; } input[type='text'], input[type='email'], input[type='date'], button { width: 100%; } }");
                out.println("@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }");
                out.println("</style></head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Edit User Details</h1>");
                out.println("<form action='edit?id=" + id + "' method='post'>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<td>Name</td>");
                out.println("<td><input type='text' name='name' value='" + rs.getString("name") + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Email</td>");
                out.println("<td><input type='email' name='email' value='" + rs.getString("email") + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Mobile</td>");
                out.println("<td><input type='text' name='mobile' value='" + rs.getString("mobile") + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>DOB</td>");
                out.println("<td><input type='date' name='dob' value='" + rs.getString("dob") + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>City</td>");
                out.println("<td><input type='text' name='city' value='" + rs.getString("city") + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Gender</td>");
                out.println("<td><input type='radio' name='gender' value='Male'" + ("Male".equals(rs.getString("gender")) ? " checked" : "") + "> Male");
                out.println("<input type='radio' name='gender' value='Female'" + ("Female".equals(rs.getString("gender")) ? " checked" : "") + "> Female");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit'>Update</button>");
                out.println("<button type='reset'>Cancel</button>");
                out.println("</form>");
                out.println("<a href='showdata'>Back to User List</a>");
                out.println("</div></body></html>");
            } else {
                out.println("<html><head><title>No Data Found</title>");
                out.println("<style>");
                out.println(
                        "body { font-family: 'Arial', sans-serif; background-color: #e9ecef; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; font-variant: small-caps; }");
                out.println(
                        ".container { max-width: 500px; width: 100%; padding: 30px; background: #ffffff; border-radius: 12px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15); border: 1px solid #e0e0e0; text-align: center; animation: fadeIn 1s ease-in-out; }");
                out.println("h1 { color: #dc3545; font-size: 24px; font-weight: bold; }");
                out.println(
                        "a { display: inline-block; padding: 12px 24px; background-color: #dc3545; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; transition: background-color 0.3s; }");
                out.println("a:hover { background-color: #c82333; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='container'><h1>No data found for ID " + id + "</h1>");
                out.println("<a href='home.html'>Back to Home</a>");
                out.println("</div></body></html>");
            }
        } catch (Exception e) {
            out.println("<html><head><title>Error</title>");
            out.println("<style>");
            out.println(
                    "body { font-family: 'Arial', sans-serif; background-color: #e9ecef; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; font-variant: small-caps; }");
            out.println(
                    ".container { max-width: 500px; width: 100%; padding: 30px; background: #ffffff; border-radius: 12px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15); border: 1px solid #e0e0e0; text-align: center; animation: fadeIn 1s ease-in-out; }");
            out.println("h1 { color: #dc3545; font-size: 24px; font-weight: bold; }");
            out.println(
                    "a { display: inline-block; padding: 12px 24px; background-color: #dc3545; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; transition: background-color 0.3s; }");
            out.println("a:hover { background-color: #c82333; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'><h1>" + e.getMessage() + "</h1>");
            out.println("<a href='home.html'>Back to Home</a>");
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
