/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ShirtDAO;
import db.validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CarForm;
import model.Shirt;
import org.apache.log4j.Logger;

/**
 *
 * @author ilja
 */
public class ShirtServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ShirtDAO db = new ShirtDAO();
    Logger logger = Logger.getLogger("controller.ShirtServlet");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShirtServlet() {
        super();
        init();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    public void init() {
        db.DB();
        logger.info("CarServlet.init(): mind loodi");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null) {
            int foo = 0;
            try {
                foo = Integer.parseInt(id);
            } catch (NumberFormatException e) {
				//logger.error("CarServlet.doGet(): wrong data: "+ id);

            }

            if (db.findById(foo).getId() == 0) {
                logger.error("CarServlet.doGet(): wrong id: " + id);
                response.sendRedirect("error.jsp");
            } else {
                Shirt shirt = db.findById(foo);
                System.out.println(shirt);
                request.setAttribute("shirt", shirt);
                request.getRequestDispatcher("shirt.jsp").forward(request, response);
            }

        } else {
            Shirt[] shirts = db.findAll();
            request.setAttribute("shirt", shirts);
            request.getRequestDispatcher("shirts.jsp").forward(request, response);

        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
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
        if (errorList.isEmpty()) {
            db.update(c);
        } else {
            logger.error("CarServlet.doPost(): save failed");
        }

        //request.setAttribute("car", db.findById(Integer.parseInt(id)));
        request.setAttribute("car", c);

        request.setAttribute("formError", errorList);

        request.getRequestDispatcher("shirt.jsp").forward(request, response);

    }

}
