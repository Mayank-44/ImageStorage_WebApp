<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.mysql.jdbc.Blob"%>
<%@ page import="com.nagarro.tables.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nagarro.service.*"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div style="background: white">
		<%
			//response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");

			String uname = (String) request.getSession().getAttribute("uname");
			if (uname == null) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		%>
		
		<div style="margin-right: 20px; margin-left: 20px; margin-top:20px;">
			<div class="alert alert-dark" role="alert">
				<div style="display: table; margin: 0 auto">
					<strong>Image Management Utility</strong>
				</div>
			</div>
		</div>
		

		<div style="margin-top: 50px;"></div>
		<div style="margin-right: 20px; margin-left: 20px;">
			<form action="UploadServlet" method="post"
				enctype="multipart/form-data">
				<div class="input-group">
					<div class="input-group-prepend">
						<input type="submit" class="btn btn-dark" value="Upload">
					</div>
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="inputGroupFile01"
							aria-describedby="inputGroupFileAddon01" name="file"> <label
							class="custom-file-label btn btn-dark" for="inputGroupFile01">
							Please select an image file to upload (Max Size 1 MB)</label>
					</div>
				</div>
			</form>
		</div>
		<div style="margin-top: 50px; background: #58d68d"></div>
		<%
			List<Images> images = (List<Images>) request.getAttribute("ImageList");
		%>
		<div>
			<div
				style="margin-right: 20px; margin-left: 20px; background: #e8f8f5">
				<table class="table">
					<thead>
						<tr style="background: #58d68d">
							<th scope="col">S.No</th>
							<th scope="col" style="padding-left:200px;">Name</th>
							<th scope="col" style="padding-left:20px;">Size</th>
							<th scope="col" style="padding-left:125px;">Preview</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
							int j = 0;
							for (Images image : images) {
								j++;
								String base64Image = Base64.getEncoder().encodeToString(image.getPreview());
								int idd = image.getId();
						%>

						<td><%out.println(j+".");%></td>
						<td><%=image.getImageName()%></td>
						<div class="card">
							<td><%=(float) (image.getSize() / 1024)%>
								<%
									out.println(" KB");
								%></td>
							<%
								out.println("<td><img src='data:image/jpg;base64," + base64Image + "' width=300 height='200'/></td>");
							%>
							</div>
							<td>
						
						<form action="EditForm.jsp" method="post"
							style="margin-top: 10px;">
							<button type="submit" data-toggle="tooltip" title="Edit"
								class="btn btn-secondary" name="id11" value=<%=image.getId()%>>
								<img src="wrench.png" alt="Edit" height="45" width="45">
							</button>
						</form>
						<br>
						<br>
						<form action="deleteImage" method="post">
							<button type="submit" data-toggle="tooltip" title="Delete"
								class="btn btn-secondary" name="id2" value=<%=image.getId()%>>
								<img src="cancel.png" alt="Delete" height="45" width="45">
							</button>
						</form>
						</td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
		</div>

		<div style="margin-right: 20px; margin-left: 20px;">
			<div class="alert alert-warning" role="alert">
				<div style="display: table; margin: 0 auto">
					<%
						int totalSize = (Integer) request.getAttribute("totalsize");
						out.println("<a>Total storage used</a>" + " " + (float) (totalSize / 1024) + " KB");
					%>
				</div>
			</div>
		</div>
		<div style="padding-top:20px;">
			<div>
				<form action="logout" method="post">
					<input type="submit" style="display: table; margin: 0 auto"
						class="btn btn-dark btn-lg " value="Logout">
				</form>
			</div>
		</div>

		<div style="margin-top: 50px;"></div>
	</div>
</body>
</html>