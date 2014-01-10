<html>
<head>
  <title>Test Velocity</title>
</head>
<body>
  <table>
  	<tr>
	  <td>String</td>
	  <td>Date</td>    
	</tr>
	<#list tmTypes as tmType>
	<tr>
	  <td>${tmType.strType}</td>
	  <td>${tmType.dateType?string('yyyy-MM-dd')}</td>    
	</tr>
    </#list>       
  <table>
</body>
<html>
