package registrationservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	Connection connection=null;
	public void init() throws ServletException{
		connection=getConnection();
		
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		
		
try {
			
			PrintWriter out=response.getWriter();
			out.print("<html>\r\n"
					+ "<head></head>\r\n"
					+ "<body>\r\n"
					+ "<center>\r\n"
					+ "<h1>Login here</h1>\r\n"
					+ "<form action=\"./login\" method=\"post\">\r\n"
					+ "<input type=\"text\" placeholder=\"username here\" name=\"username\"><br></br>\r\n"
					+ "<input type=\"text\" placeholder=\"email here\" name=\"email\"><br></br>\r\n"
					+ "<input type=\"password\" placeholder=\"password here\" name=\"password\"><br></br>\r\n"
					+ "<button>Login</button>\r\n"
					+ "</form>\r\n"
					+ "</center>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		
		String query="select password from Users where email='"+email+"'";
		PrintWriter out=response.getWriter();
		
		try {
			ResultSet rs=connection.createStatement().executeQuery(query);
			
			String passwordDB="";
			String username1="";
			while(rs.next()) {
				passwordDB=rs.getString("password");
				username1=rs.getString("username");
			}
			
			if(passwordDB.length()>0) {
				if(passwordDB.contentEquals(password)) {
					out.print("<h1>Login Success</h1>");
					out.print("<h1>Welcome :"+username+"</h1>");
					out.print("<a href='./inbox'>inbox</a>");
					out.print("<a  href='./outbox'>outbox</a>");
				}else {
					out.print("<h1>Login Failed</h1>");
				}
			}else {
				out.print("<h1>User Not Found </h1>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	private Connection getConnection() {
		Connection connection =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDemo","root","Sai@0015");
			return connection;
		}catch(Exception e) {
			return connection;
		}
	}
}
