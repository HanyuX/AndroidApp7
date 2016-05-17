<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.xuehanyu.myapplication.backend.data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Result</title>
</head>
<body>
	<h1>Exercise Entries for your device:</h1>
	<table border="1">
	<tr>
	    <th>id</th><th>InputType</th><th>ActivityType</th><th>DateTime</th><th>Duration</th>
	    <th>Distance</th><th>AvgSpeed</th><th>Calories</th><th>Climb</th><th>HeartRate</th><th>Comment</th><th>Delete</th>
	</tr>
	<%
		ArrayList<DataItem> resultList = (ArrayList<DataItem>) request
					.getAttribute("result");
		if (resultList != null) {
			for (DataItem item : resultList) {
	%>
	<tr>
	    <td><%=item.Id%></td><td><%=item.InputType%></td>
		<td><%=item.ActivityType%></td><td><%=item.DateTime%></td>
		<td><%=item.Duration%></td><td><%=item.Distance%></td>
		<td><%=item.AvgSpeed%></td><td><%=item.Calories%></td>
		<td><%=item.Climb%></td><td><%=item.HeartRate%></td>
		<td><%=item.Comment%></td>
		<td><a href="/delete.do?ID=<%=item.Id%>">delete</a></td>
		</tr><%
 	}
 	}
 %>
 </table>

 	<form name="input" action="/add.do" method="post">
 		ID: <input type="text" name="id"> InputType: <input
 			type="text" name="inputtype"> ActivityType: <input type="text"
 			name="activitytype">
 		DateTime: <input type="text" name="datetime"> Duration: <input type="text" name="duration">
 		Distance: <input type="text" name="distance"> AvgSpeed: <input type="text" name="avgspeed">
 		Calories: <input type="text" name="calories"> Climb: <input type="text" name="climb">
 		HeartRate: <input type="text" name="heartrate"> Comment: <input type="text" name="comment">
 		<input type="submit" value="Add">
 	</form>
</body>
</html>