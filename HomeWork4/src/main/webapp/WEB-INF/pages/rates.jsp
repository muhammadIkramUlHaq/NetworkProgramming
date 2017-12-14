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
	<div class="welcome"> Exchange Rates Information </div>
	<p></p>
	    <div class="cur_body">
			<form:form method="post" action="/api/rate/add" commandName="rate" class="form-horizontal">
			<div>
				<form:label cssClass="control-label" path="convertFrom">Source Currency Code:</form:label>
				<div >
					<form:select path="convertFrom" class="slt_item">
						<form:option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
						<<form:options items="${currencies}" />
					</form:select>
				</div>
			</div>
				<p></p>
			<div>
				<form:label cssClass="control-label" path="convertTo">Destination Currency Code:</form:label>
				<div>
                    <form:select path="convertTo" class="slt_item">
                        <form:option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
                        <<form:options items="${currencies}" />
                    </form:select>
				</div>
			</div>
				<p></p>
			<div class="control-group">
				<form:label cssClass="control-label" path="exchangeRate">Exchange Rate:</form:label>
				<div class="controls">
					<form:input path="exchangeRate"/>
				</div>
			</div>
				<p></p>
			<div class="control-group">
				<div class="controls">
					<input type="submit" value="Add Rate" class="pat_btn_click"/>
					</form:form>
				</div>
			</div>
				  <p></p>
			<div class="control-group">
				<div class="controls">
					<input type="button" class="pat_btn_click" onclick="location.href='/welcome'" value="Back" >
				</div>
			</div>
				<p></p>
		</div>

	    <div class="cur_tab_body">
			<c:if test="${!empty rates}">
				<h2 style="margin-top:10px"> Rates </h2>
				<table>
					<thead>
					<tr>
						<th class="tab_th">Source Currency</th>
						<th class="tab_th">Destination Currency</th>
						<th class="tab_th">Exchange Rate</th>
						<th>&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${rates}" var="rate">
						<tr>
							<td class="tab_td"> ${rate.convertFrom}  </td>
							<td class="tab_td"> ${rate.convertTo} </td>
							<td class="tab_td"> ${rate.exchangeRate} </td>
							<td class="tab_td">
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
</div>

</body>
</html>