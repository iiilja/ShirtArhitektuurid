/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ShirtDAO;
import db.ShirtValidation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Shirt;
import model.ShirtForm;
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
        logger.error("ShirtServlet.init(): mind loodi");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null) {
            int foo = 0;
            try {
                foo = Integer.parseInt(id);
            } catch (NumberFormatException e) {
				//logger.error("ShirtServlet.doGet(): wrong data: "+ id);

            }

            if (foo == 0 || db.findById(foo).getId() == 0) {
                logger.error("ShirtServlet.doGet(): wrong id: " + id);
                response.sendRedirect("error.jsp");
            } else {
                Shirt shirt = db.findById(foo);
                System.out.println(shirt);
                request.setAttribute("shirt", shirt.toForm());
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

        String size = request.getParameter("size");
        String desc = request.getParameter("description");
        String cost = request.getParameter("cost");
        String id = request.getParameter("id");
        System.out.println("Got id = "+id+" size"+size+" cost"+cost+" desc = "+desc);
        Map<String, String> errorList = new HashMap<>();
        errorList = ShirtValidation.vld(id, cost, size);

        ShirtForm form = new ShirtForm(id, cost, size, desc);
        
        if (errorList.isEmpty()) {
            db.update(form.toShirt());
        } else {
            logger.error("ShirtServlet.doPost(): save failed");
        }
        request.setAttribute("shirt", form);

        request.setAttribute("formError", errorList);

        request.getRequestDispatcher("shirt.jsp").forward(request, response);

    }

}
