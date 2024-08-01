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

@WebServlet("/edit")
public class UserEdit extends HttpServlet {
    private static final String QUERY = "update user set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "khan");
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setInt(7, id);

            int count = ps.executeUpdate();
            out.println("<html><head><title>User Edit Status</title>");
            out.println("<style>");
            out.println("body { font-family: 'Arial', sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; font-variant: small-caps; }");
            out.println(".container { max-width: 600px; width: 100%; padding: 30px; background: #ffffff; border-radius: 12px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border: 1px solid #e0e0e0; text-align: center; }");
            out.println("h1 { font-size: 28px; margin-bottom: 20px; color: #333; }");
            out.println(".message { font-size: 18px; margin-bottom: 20px; }");
            out.println(".success { color: #4CAF50; }");
            out.println(".error { color: #f44336; }");
            out.println(".btn { display: inline-block; margin-top: 20px; padding: 12px 24px; background: #007bff; color: white; text-decoration: none; border-radius: 6px; transition: background 0.3s ease; font-size: 16px; }");
            out.println(".btn:hover { background: #0056b3; }");
            out.println(".icon { font-size: 60px; margin-bottom: 15px; }");
            out.println(".success-icon { color: #4CAF50; }");
            out.println(".error-icon { color: #f44336; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'>");
            if (count == 1) {
                out.println("<div class='icon success-icon'>&#10004;</div>");
                out.println("<h1 class='success'>User Updated Successfully</h1>");
            } else {
                out.println("<div class='icon error-icon'>&#10060;</div>");
                out.println("<h1 class='error'>User Not Updated</h1>");
            }
            out.println("<a href='showdata' class='btn'>User List</a>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><head><title>Error</title>");
            out.println("<style>");
            out.println("body { font-family: 'Arial', sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; font-variant: small-caps; }");
            out.println(".container { max-width: 600px; width: 100%; padding: 30px; background: #ffffff; border-radius: 12px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border: 1px solid #e0e0e0; text-align: center; }");
            out.println("h1 { font-size: 28px; color: #f44336; }");
            out.println(".btn { display: inline-block; margin-top: 20px; padding: 12px 24px; background: #007bff; color: white; text-decoration: none; border-radius: 6px; transition: background 0.3s ease; font-size: 16px; }");
            out.println(".btn:hover { background: #0056b3; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'><h1>" + e.getMessage() + "</h1>");
            out.println("<a href='showdata' class='btn'>Go Back</a>");
            out.println("</div>");
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
