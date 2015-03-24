package controller;

import java.io.IOException;
import java.io.PrintWriter;




import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.Car;
import model.CarForm;
import db.CarDAO;
import db.validator;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/s")
public class CarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private CarDAO db = new CarDAO();
    Logger logger = Logger.getLogger("controller.CarServlet");
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init(){
    	db.DB();
    	logger.error("CarServlet.init(): mind loodi");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		if(id != null){
			int foo = 0;
			try{
				foo = Integer.parseInt(id);
			}catch(NumberFormatException e){
				//logger.error("CarServlet.doGet(): wrong data: "+ id);
				
			}
			
			
			
			if(db.findById(foo).getId()==0){
				logger.error("CarServlet.doGet(): wrong id: "+ id);
				response.sendRedirect("error.jsp");
			}else{
				CarForm cf = CarForm.getCF(db.findById(foo));
				
				request.setAttribute("car", cf);
				request.getRequestDispatcher("car.jsp").forward(request, response);
			}
			
		}else {
			Car[] car = db.findAll();
			request.setAttribute("car", car);
			request.getRequestDispatcher("cars.jsp").forward(request, response);
			
			
		}
		
		
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String type = request.getParameter("car_type");
		String desc = request.getParameter("description");
		String count = request.getParameter("count");
		String id = request.getParameter("id");
		Map<String, String> errorList = new HashMap<>();
		errorList = validator.vld(count, type);
		
		CarForm c = new CarForm(); 
		c.setId(id);
		c.setType(type);
		c.setCount(count);
		c.setDesc(desc);
		if(errorList.isEmpty()){
			db.update(c);	
		}else {
			logger.error("CarServlet.doPost(): save failed");
		}
		
		
		
		//request.setAttribute("car", db.findById(Integer.parseInt(id)));
		request.setAttribute("car", c);
		
		request.setAttribute("formError", errorList);
		
	
		request.getRequestDispatcher("car.jsp").forward(request, response);
		
		
		
		
		
	}

}
