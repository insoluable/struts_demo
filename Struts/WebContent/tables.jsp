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

p, label {	font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	line-height: 15.4px;}
h2 {	font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 16px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 15.4px;}
h3 {	font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
	font-size: 14px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 15.4px;}
	
</style>
</head>
<h2>Select Database Instance</h2>
<body>
	<s:form method="post" action="listtables">

		<!-- TODO put dbs, owners in properties file -->
		<s:select label="Database" name="db" headerKey="-1"
			headerValue="Select Database" list="#{'xe':'xe'}" value="db"
			required="true" />

		<s:select label="Table Owner" name="owner" headerKey="-1"
			headerValue="Select Owner"
			list="#{'MAEW':'MAEW','KADAI':'KADAI'  }" value="owner"
			required="true" />


		<s:submit align="left"></s:submit>

	</s:form>
	
	<c:if test="${!empty recordList}">
	<h3>Tables</h3>
		<table class="list">
			<tr>
				<th align="left">Owner</th>
				<th align="left">Table Name</th>
				<th align="left">Last Analyzed</th>
				<th align="left">Comments</th>
			</tr>
			<c:forEach items="${recordList}" var="tab">
				<tr>
					<td>${tab.owner}</td>
					<td><a href="listcolumns/${tab.owner}/${tab.tableName}">${tab.tableName}</a></td>
					<td>${tab.lastAnalyzed}</td>
					<td>${tab.comments}</td>
				</tr>
			</c:forEach>

		</table>
	</c:if>
</body>
</html>
