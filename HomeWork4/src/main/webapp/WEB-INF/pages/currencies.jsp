<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta charset="utf-8">
	<title>Currency Exchange</title>

	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
	<link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="/webapp/css/main.css" rel="stylesheet" />
</head>

<body>

<div class="wrapper">
	<div class="welcome"> Currencies Information </div>
		<p></p>
	<div class="cur_body">
		<form:form method="post" action="/api/currency/add" commandName="currency" class="form-horizontal">
			<div class="control-group">
				<form:label cssClass="control-label" path="country">Country :</form:label>
				<div class="controls">
					<form:input path="country"/>
				</div>
			</div>
	        <p></p>
			<div class="control-group">
				<form:label cssClass="control-label" path="currencyCode">Currency Code:</form:label>
				<div class="controls">
					<form:input path="currencyCode"/>
				</div>
			</div>
	        <p></p>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Add Currency" class="pat_btn_click"/>
                    </form:form>
				</div>
			</div>
			<p></p>
			<div class="control-group">
				<div class="controls">
					<input type="button" class="pat_btn_click" style="width : 30%;" onclick="location.href='/welcome'" value="Back" >
				</div>
			</div>
		<p></p>
	</div>
	<div class="cur_tab_body">
			<c:if test="${!empty currencies}">
				<h2>Currencies</h2>
				<table class="table table-bordered table-striped" style="border: 1px">
					<thead>
					<tr>
						<th style="bor">Country</th>
						<th>Currency Code</th>
						<th>&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${currencies}" var="currency">
						<tr>
							<td> ${currency.country}  </td>
							<td> ${currency.currencyCode} </td>
							<td>
								<form action="/api/currency/delete/${currency.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:if>
	</div>
</div>

</body>
</html>