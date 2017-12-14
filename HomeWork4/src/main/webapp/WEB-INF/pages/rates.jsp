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
</head>

<body>

<div class="container">
	<div class="row">
		<div class="span8 offset2">
			<h1>Rates</h1>
			<form:form method="post" action="/api/rate/add" commandName="rate" class="form-horizontal">
			<div class="control-group">
				<form:label cssClass="control-label" path="convertFrom">Source Currency Code:</form:label>
				<div class="controls">
					<form:select path="convertFrom">
						<form:option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
						<<form:options items="${currencies}" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="convertTo">Destination Currency Code:</form:label>
				<div class="controls">
                    <form:select path="convertTo">
                        <form:option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
                        <<form:options items="${currencies}" />
                    </form:select>
				
				</div>
			</div>
			<div class="control-group">
				<form:label cssClass="control-label" path="exchangeRate">Exchange Rate:</form:label>
				<div class="controls">
					<form:input path="exchangeRate"/>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Add Rate" class="btn"/>
					</form:form>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<input type="button" class="pat_btn_click" style="width : 10%;" onclick="location.href='/welcome'" value="Back" >
				</div>
			</div>


			<c:if test="${!empty rates}">
				<h3>Rates</h3>
				<table class="table table-bordered table-striped">
					<thead>
					<tr>
						<th>Source Currency</th>
						<th>Destination Currency</th>
						<th>Exchange Rate</th>
						<th>&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${rates}" var="rate">
						<tr>
							<td> ${rate.convertFrom}  </td>
							<td> ${rate.convertTo} </td>
							<td> ${rate.exchangeRate} </td>
							<td>
								<form action="/api/rate/delete/${rate.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>

</body>
</html>