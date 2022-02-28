<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false"%>
<html>
<head>
<title>To Do App</title>
</head>
<body>
	<header>
<style>
.split {
  height: 100%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 0;
  overflow-x: hidden;
  padding-top: 20px;
}

.left {
  left: 0;
}

.right {
  right: 0;
}

.centered {
  position: absolute;
}

.centered2 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

</style>
	</header>

	<div class="split left">
		<div class="centered">
			<div><h3>To Do App</h3> 
				<form action="sort" method="get">
				<select name="sortByType">
					<c:forEach items="${sortByTypes}" var="type">
						<option value="${type}">${type}</option>
					</c:forEach>
				</select>
				<button type="submit">Sort</button>
				<form>
			</div>
			<table>
					<tr>
						<th>Task Name</th>
						<th>Task Timing(to)</th>
						<th>Task Timing(from)</th>
						<th>Task Date(to)</th>
                        <th>Operations</th>
					</tr>
				<tbody>					
					<c:forEach var="task" items="${taskList}" >
						<tr>
							<td><c:out value="${task.taskName}" /></td>
                            <td><c:out value="${task.fromTime}" /></td>
                            <td><c:out value="${task.toTime}" /></td>
                            <td><c:out value="${task.date}" /></td>					

							<td><a href="<%=request.getContextPath()%>/updatetask?id=<c:out value='${task.taskId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/delete?id=<c:out value='${task.taskId}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<div class="split right">
		<div class="centered2">	 
			<a href="<%=request.getContextPath()%>/add>">Add Task</a> 
		</div>
	</div>

</body>
</html>