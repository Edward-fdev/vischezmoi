package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String INSERT_OR_EDIT = "/user.jsp";
	private static final String LIST_USER = "/listuser.jsp";
	private UserDAO dao;

	public UserServlet() {
		dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        String forward="";
        String action = req.getParameter("action");
 
//        if (action.equalsIgnoreCase("delete")){
//            String userId = req.getParameter("userId");
//            dao.deleteUser(userId);
//            forward = LIST_USER;
//            req.setAttribute("users", dao.getAllUsers());    
//        } 
//        else
//        	if (action.equalsIgnoreCase("edit")){
//            forward = INSERT_OR_EDIT;
//            String userId = req.getParameter("userId");
//            User user = dao.getUserById(userId);
//            req.setAttribute("user", user);
//        } 
//        else if (action.equalsIgnoreCase("listUser")){
//            forward = LIST_USER;
//            req.setAttribute("users", dao.getAllUsers());
//        } 
//        else {
            forward = INSERT_OR_EDIT;
//        }
 
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		   User user = new User();
	        user.setPseudo(req.getParameter("pseudo"));
	        user.setPassword(req.getParameter("password"));
	        try {
	            Date reg = new SimpleDateFormat("yyyy/MM/dd").parse(req.getParameter("dob"));
	            System.out.println("rrrrrrrrrrr"+ reg);
	            user.setDateInscription(reg);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        user.setLogin(req.getParameter("login"));
	        String userid = req.getParameter("pseudo");
//	        if(userid == null || userid.isEmpty())
//	        {
//	            dao.addUser(user);
//	        }
//	        else
//	        {
	            user.setPseudo(userid);
	            dao.checkUser(user);
//	        }
	        RequestDispatcher view = req.getRequestDispatcher(LIST_USER);
	        req.setAttribute("users", dao.getAllUsers());
	        view.forward(req, resp);
		super.doPost(req, resp);
	}

}
