package registrationservlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationDemo extends HttpServlet {

	Connection connection=null;
	
	public void init() throws ServletException {
		connection=getConnection();
		System.out.println(connection);
	}
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
		
		try {
			
			PrintWriter out=resp.getWriter();
			out.print("<html>\r\n"
					+ "<head></head>\r\n"
					+ "<body>\r\n"
					+ "<center>\r\n"
					+ "<h1>Register here</h1>\r\n"
					+ "<form action=\"./register\" method=\"post\">\r\n"
					+ "<input type=\"text\" placeholder=\"username here\" name=\"username\"><br></br>\r\n"
					+ "<input type=\"text\" placeholder=\"email here\" name=\"email\"><br></br>\r\n"
					+ "<input type=\"password\" placeholder=\"password here\" name=\"password\"><br></br>\r\n"
					+ "<button>Register</button>\r\n"
					+ "</form>\r\n"
					+ "</center>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		
		String query="insert into Users values('"+username+"','"+password+"','"+email+"')";
		
		try {
			
			connection.createStatement().execute(query);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDemo","root","Sai@0015");
			return connection;
		}catch(Exception e) {
			System.out.println(e);
			return connection;
		}
		
	}

}
