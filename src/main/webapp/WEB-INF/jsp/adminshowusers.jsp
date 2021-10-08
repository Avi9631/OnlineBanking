<%@page import="com.bank.beans.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Admin-Show all users</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {
	font: 400 15px Lato, sans-serif;
	line-height: 1.8;
	color: #818181;
}

.jumbotron {
	background-color: #f4511e;
	color: #fff;
	padding: 100px 25px;
	font-family: Montserrat, sans-serif;
}

.container-fluid {
	padding: 60px 50px;
}

.bg-grey {
	background-color: #f6f6f6;
}
</style>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Online Banking</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="/showall">SHOW ALL ACCOUNTS</a></li>
					<li><a href="profile.html">PROFILE</a></li>
					<li><a href="">LOGOUT</a></li>

				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron text-center">
		<h1>ONLINE BANKING</h1>
		<p>View All Accounts</p>
	</div>

	<div class="container-fluid">
		<form>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6 ">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search Account Number" id="txtSearch" />
						<div class="input-group-btn">
							<button class="btn btn-primary" type="submit">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<br> <br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Account Number</th>
					<th>User ID</th>
					<th>Email ID</th>
					<th>Name</th>
					<th>Account Type</th>
					<th>Account Balance</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<% List<User> users= (List<User>) request.getAttribute("allUser");
			for(User u: users){
			%>
				<tr>
					<td><%= u.getAccount().getAccno()%></td>
					<td><%= u.getId()%></td>
					<td><%= u.getEmail()%></td>
					<td><%= u.getName()%></td>
					<td><%= u.getAccount().getAccType()%></td>
					<td><%= u.getAccount().getBal()%></td>
					<td><a href="/show?id=<%=u.getId() %>" type="button" class="btn btn-info">View Details</a>
						&nbsp;&nbsp;
						<button onclick="if(confirm('Are you sure to close the account for <%=u.getName() %>')){window.location.href='/close?id=<%=u.getId() %>'}" type="button" class="btn btn-danger">Close
							Account</a></td>
				</tr>
				<%} %>
			</tbody>

		</table>
	</div>

	<footer class="container-fluid text-center">
		<a href="#myPage" title="To Top"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a>
		<p>Online Banking</p>
	</footer>

</body>

</html>