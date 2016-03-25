<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Data Dictionary</title>
<style>
table.list {
	border-collapse: collapse;
	width: 80%;
	
}

table.list td {
	border: 1px solid gray;
	padding: 5px;
	font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	line-height: 15.4px;
}

table.list th {

	border: 1px solid gray;
	padding: 5px;
    font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 12px;
	font-variant: normal;
	font-weight: bold;
	line-height: 15.4px;

}

p {	font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	line-height: 15.4px;}
	
</style>
</head>
<body>
	
	<c:if test="${!empty columnList}">
	
	<p>Columns for table <s:property value="owner"/>.<s:property value="tableName"/></p>
		<table class="list">
			<tr>
				<th align="left">Column Name</th>
				<th align="left">Data Type</th>
				<th align="left">Length</th>
				<th align="left">Precision</th>
				<th align="left">Scale</th>
				<th align="left">Comment</th>
			</tr>
			<c:forEach items="${columnList}" var="col">
				<tr>
					<td>${col.columnName}</td>
					<td>${col.dataType}</td>
					<td>${col.dataLength}</td>
					<td>${col.dataPrecision}</td>
					<td>${col.dataScale}</td>
					<td>${col.comments}</td>

				</tr>
			</c:forEach>

		</table>
	</c:if>
</body>
</html>