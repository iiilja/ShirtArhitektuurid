<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.Shirt"%> 
<jsp:useBean id="shirt" scope="request" type="model.Shirt"/>
<jsp:useBean id="formError" class="java.util.HashMap" scope="request"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Muuda Auto</title>
        </head>
        <body>


            <a href='s'>servlet</a> | <a href='http://imbi.ld.ttu.ee/tomcat_webapp_logs/t112528_car/log.txt'>log.txt</a> <br>

            <form action='s?action=save' method=POST>
            <%

                String id = shirt.getId() + "";
                String type = shirt.getSize();
                String count = shirt.getCost() + "";
                String description = shirt.getDescription();

                String countError = "";
                String typeError = "";

                if (formError.get("type") != null) {
                    typeError = (String) formError.get("type");

                }
                if (formError.get("count") != null) {
                    countError = (String) formError.get("count");
                }

                if (!formError.isEmpty()) {

                    out.println("<table><tr><td><b><font face=arial color=red>Prooviti salvestada - aga ei onnestunud!</b></td></tr></table>");
                }

                out.println("<table bgcolor='#000000' border=0 cellpadding=0 cellspacing=0><tr><td><table width=100% border=0 cellpadding=2 cellspacing=1>");
                out.println("<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>id:</td><td>&nbsp;" + id + "&nbsp;</TD></tr>");
                out.println("<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>tyyp:</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='" + type + "' name='car_type'></font></b>" + typeError + "</TD></tr>");
                out.println("<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>autode arv:</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='" + count + "' name='count'></font></b>" + countError + "</TD></tr>");
                out.println("<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>kirjeldus:</td><td>&nbsp;<b><font color='#0000ff'><textarea name=\"description\" cols=25 rows=6>" + description + "</textarea></font></b></TD></tr>");
                out.println("</table></td></tr></table>");


            %>

            <input type="submit" value="salvesta" >
            <input type="hidden" name="id" value="<%=id%>" />  




    </body>
</html>