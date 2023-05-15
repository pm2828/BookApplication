package test;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/displaybooks")
public class DisplayBooks extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con=null;
	@Override
	public void init() throws ServletException {
	try {	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8", "root", "sql123");
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Statement stmt=null;
	ResultSet rs=null;
	PrintWriter pw=resp.getWriter();
	String query="select * from book_data";
	try {
	stmt=con.createStatement();
	rs=stmt.executeQuery(query);
	pw.print("<html>");
	pw.print("<head>");
	pw.print("<style>");
	
	pw.print("table,td, tr { border: 1px solid white; }");
	pw.print("</style>");
	pw.print("</head>");
	
	pw.print("<body style=\"background-image: url('https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80'); background-repeat: no-repeat; background-size: cover; background-position: center; background-color: #f5f5f5;>");
	pw.print("<div style=\"text-align:center; background-color: #efefef; background-image: url('https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80');\">");
	pw.print("<table id=\"mytable\" border='3' style=\"margin:0 auto; width:100%; height:100%; text-align:center; font-size: 48px;  text-shadow: 1px 1px 2px black; color: white; font-weight:bold   border: 5px solid #d3d3d3;\">");
	pw.print("<tr>");
	pw.print("<th>BookID</th>");
	pw.print("<th>BookName</th>");
	pw.print("<th>BookPrice</th>");
	pw.print("<th>BookAuthor</th>");
	pw.print("</tr>");
	while(rs.next()) {
		pw.print("<tr>");
		pw.print("<td>"+rs.getInt(1)+"</td>");
		pw.print("<td>"+rs.getString(2)+"</td>");
		pw.print("<td>"+rs.getDouble(3)+"</td>");
		pw.print("<td>"+rs.getString(4)+"</td>");
		pw.print("</tr>");

	}
	pw.print("</table>");
	pw.print("</div>");
	pw.print("</body>");
	pw.print("</html>");
	}catch(SQLException e) {
		e.printStackTrace();
	}
}
}