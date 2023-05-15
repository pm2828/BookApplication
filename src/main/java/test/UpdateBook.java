package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updatebook")
public class UpdateBook extends HttpServlet {
	int id;
	double price;
	
	Connection con=null;
	@Override
	public void init() throws ServletException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","sql123");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id1=req.getParameter("bookid");
		try {
		id =Integer.parseInt(id1);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		String name=req.getParameter("bookname");
		try {
		 price=Double.parseDouble(req.getParameter("price"));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		String author=req.getParameter("author");
		PreparedStatement pstmt=null;
		
		String query="update book_data set bookname=?,bookprice=?,author=? where bookid=? ";
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setString(3, author);
			pstmt.setInt(4, id);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+"Book Updated</h1>");
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
