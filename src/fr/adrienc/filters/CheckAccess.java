package fr.adrienc.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.adrienc.model.beans.User;
import fr.adrienc.model.daos.DAOFactory;

/**
 * Servlet Filter implementation class CheckAccess
 */
@WebFilter(
		filterName="/CheckAccess",
		value="/*",
		dispatcherTypes=DispatcherType.REQUEST)
public class CheckAccess implements Filter {
	
	public static boolean userAllowed;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
//		pseudo = fConfig.getInitParameter("pseudo");
//		pwd = fConfig.getInitParameter("pwd");
	}
	
	@Override
	public void destroy() {}

	private Integer checkValidUser(String pseudoClient, String pwdClient){
		String query = "SELECT * FROM user u WHERE u.pseudo='" + pseudoClient + "'" ;
		int val = 0;
		Connection cnx = null;
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            System.out.println(cnx.toString());
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next() ) {
            	//Country country = new Country(result.getString(4));
            	System.out.println(result.getString(3));
            	System.out.println(pwdClient);
                if (pwdClient.equals(result.getString(3))){                	
                	val = result.getInt(1); ;
                }
            }           
        } catch (Exception e){
        	e.printStackTrace();
        } finally {
        	try{
        		if(null != cnx &&!cnx.isClosed()){
        			cnx.close();
        		}
        	}catch(SQLException e){
        		e.printStackTrace();
        	}
        }		

		return val;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(!CheckAccess.userAllowed){
						
			String pseudoClient = request.getParameter("pseudo");
			String pwdClient = request.getParameter("pwd");
			if (null != pseudoClient && null != pwdClient){
				System.out.println("okkkkkk");
//					if (pseudoClient.equals(this.pseudo) && pwdClient.equals(this.pwd)){
//						CheckAccess.userAllowed = true;
//					}
				int id_user = checkValidUser(pseudoClient, pwdClient);
				if (0 != id_user){
					DAOFactory daofactory = DAOFactory.getInstance();
					CheckAccess.userAllowed = true;
					HttpSession session = ((HttpServletRequest)request).getSession(true);
					User user = daofactory.getUserDAO().find(id_user);
					session.setAttribute("user", user); 
					System.out.println("réussi");
					
				}else{
					System.out.println("échoué");
				}
			}
			
		}
		// pass the request along the filter chain
		if(!CheckAccess.userAllowed){
			request.getRequestDispatcher("/WEB-INF/checkaccess.jsp").forward(request, response);
		}else{
			if(((HttpServletRequest) request).getRequestURI().contains("deconnect")){
				CheckAccess.userAllowed = false;
				request.getRequestDispatcher("/WEB-INF/checkaccess.jsp").forward(request, response);
				
			}else{
				chain.doFilter(request, response);
			}
		}	
	}
}
