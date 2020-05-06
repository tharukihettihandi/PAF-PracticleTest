<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.HospitalReg"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="ISO-8859-1">

<title>Hospital management</title>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">

				<h1 class="m-3" style="text-align: center">Hospital Management</h1>

				<form id="formHospital" name="formHospital" method="post" action="Index.jsp">
				Registration Number: 
				<input id="hosRegNo" name="hosRegNo"type="text" class="form-control form-control-sm">
			    
			    <br>Name: 
			    <input id="hosName" name="hosName" type="text"class="form-control form-control-sm"> 
				
					  
				 <br>Address:
				 <input id="hosAddress" name="hosAddress" type="text"class="form-control form-control-sm"> 
			
	        	
				 <br>Phone:
				 <input id="hosPhone" name="hosPhone" type="text"  class="form-control form-control-sm"> 
				
				
				 
				<br>Email: 
				<input id="hosEmail" name="hosEmail" type="text"class="form-control form-control-sm"> 
				
				
				<br>
				<br>Departments: 
				<input id="Departments" name="Departments" type="text"class="form-control form-control-sm"> 
			
				<br>
			   
			   <input id="btnSave" name="btnSave" type="button"value="Save" class="btn btn-primary"> 
			   <input type="hidden"id="hidhosIDSave" name="hidhosIDSave" value="">
			    
			    
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divHospitalGrid">
				<%
					HospitalReg hosreg = new HospitalReg();
				    out.print(hosreg.readHos());
				%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

