<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>

body{
  background: rgba(0,0,0,0.9);
}
form{
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -100px;
  margin-left: -250px;
  width: 500px;
  height: 200px;
  border: 4px dashed #fff;
}
form p{
  width: 100%;
  height: 100%;
  text-align: center;
  line-height: 170px;
  color: #ffffff;
  font-family: Arial;
}
form input{
  position: absolute;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  outline: none;
  opacity: 0;
}
form button{
  margin: 0;
  color: #fff;
  background: #16a085;
  border: none;
  width: 508px;
  height: 35px;
  margin-top: -20px;
  margin-left: -4px;
  border-radius: 4px;
  border-bottom: 4px solid #117A60;
  transition: all .2s ease;
  outline: none;
}
form button:hover{
  background: #149174;
	color: #0C5645;
}
form button:active{
  border:0;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<%
		String uname = (String)request.getSession().getAttribute("uname"); 
		if(uname == null)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	%>
	<!--
Select a file to upload: <br/>
      <form action = "UpdateServlet" method = "post" enctype = "multipart/form-data">
    	 <input type="hidden" value = <=Integer.parseInt(request.getParameter("id11")) %> name="id">
         <input type = "file" name = "file" size = "50"/><br/>
         <input type = "submit" value = "Upload File"/>
      </form>
-->

<form action="UpdateServlet" method="POST" enctype = "multipart/form-data">
	<input type="hidden" value = <%=Integer.parseInt(request.getParameter("id11")) %> name="id">
 	 <input type="file" name = "file" size = "50">
  <p>Drag your files here or click in this area.</p>
  <button type="submit">Upload</button>
</form>

</body>
</html>