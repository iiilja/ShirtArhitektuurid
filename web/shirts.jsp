<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.Shirt"%> 
<jsp:useBean id="shirt" scope="request" type="model.Shirt[]"/>
<html>
<head>
<script src="static/js/shirt.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shirts</title>
</head>
<body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<a href='s'>servlet</a> | <a href='http://imbi.ld.ttu.ee/tomcat_webapp_logs/t093817_Shirt/log.txt'>log</a> <br>
Shitrs list: 
<br>

<% 
      String id = "" ;
      String type = "" ;
      String count = "" ;
      String desc = "";
      out.println("<table border=1 cellpadding=2 cellspacing=1>");
      out.println("<tr bgcolor='#cccccc'><td><STRONG> id&nbsp;</td><td><STRONG>Size&nbsp;</td><td><STRONG>Shirt cost&nbsp;</td><td><STRONG>description&nbsp;</td><td>link</td></tr><tr></tr>");

      try
      {
         for (int n = 0; n < shirt.length ; n++)
              {    
                id = Integer.toString(shirt[n].getId()) ;
                type = shirt[n].getSize() ;
                count = Integer.toString(shirt[n].getCost()) ;
                desc = shirt[n].getDescription();
                out.println("<TR BGCOLOR='#FFFFFF' ><TD  nowrap>");
                //out.println("&nbsp;"+ id + "&nbsp;</TD><TD>"+ type +  "&nbsp;</TD><TD>"+ count + "&nbsp");
                out.println("&nbsp;"+ id + "&nbsp;</TD><TD>"+ type + "&nbsp;</TD><TD>"+ count + "&nbsp;</TD><TD>"+"<a href='javascript:showDescription("+id+")' target='_self'>Description</a>"+"&nbsp");             
                out.println("</TD><TD align='top' nowrap><a HREF='s?id=" + id + "' TARGET='_self'><b><u>change</u><b></a></TD></TR>");
              }
     }
    catch(Exception ex)
      { 
      out.println("Some error" + ex.getMessage());

       }
         
         out.println("</table>");
         out.println("</form>");
	
 %>
 
 <br>
 <br>
 <div ID="ajax_response">
</div>
<div ID="description_form" style="visibility:hidden;">
<form name=description_form>
<TABLE BGCOLOR='#cccccc'>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#eeeeee' COLSPAN=2>Description</TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>Shirt id</td><td BGCOLOR='#cccccc'><input type=text name=shirt_id size=4 disabled id='id'></TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>Description</td><td BGCOLOR='#cccccc'><textarea name=description cols=25 rows=5 id="desc"></textarea></TD></tr>
<TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap COLSPAN=2><input type="button" value="HIDE" onClick="hide_description_form()"></TD></tr>
</TABLE>
</form>
</div>








</body>
</html>