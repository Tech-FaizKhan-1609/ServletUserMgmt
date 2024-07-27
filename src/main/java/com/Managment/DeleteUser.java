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

@WebServlet("/deleteurl")
public class DeleteUser extends HttpServlet {
    private static final String QUERY = "delete from user where id=?";

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

            int count = ps.executeUpdate();
            out.println("<html><head><title>Delete User</title>");
            out.println("<style>");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; }");
            out.println("h1 { font-size: 24px; margin-bottom: 20px; }");
            out.println(".success { color: #4CAF50; }");
            out.println(".error { color: #f44336; }");
            out.println(".message { font-size: 18px; margin-bottom: 20px; }");
            out.println(".btn { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #4CAF50; color: white; text-decoration: none; border-radius: 5px; transition: background 0.3s ease; }");
            out.println(".btn:hover { background: #45a049; }");
            out.println(".icon { font-size: 50px; margin-bottom: 10px; }");
            out.println(".success-icon { color: #4CAF50; }");
            out.println(".error-icon { color: #f44336; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'>");
            if (count == 1) {
                out.println("<div class='icon success-icon'>&#10004;</div>");
                out.println("<h1 class='success'>User Deleted Successfully</h1>");
            } else {
                out.println("<div class='icon error-icon'>&#10060;</div>");
                out.println("<h1 class='error'>User Not Deleted</h1>");
            }
            out.println("<a href='showdata' class='btn'>User List</a>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><head><title>Error</title>");
            out.println("<style>");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; margin: 0; padding: 20px; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".container { max-width: 500px; width: 100%; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); border: 1px solid #ddd; text-align: center; }");
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
