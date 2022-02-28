<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>To Do List</title>
</head>

</head>
<body>
 <header>
 </header>
 <div>
  <div>
   <div>
    <c:if test="${task != null}">
     <form action="update" method="post">
    </c:if>
    <c:if test="${task == null}">
     <form action="insert" method="post">
    </c:if>

    <caption>
     <h2>
      <c:if test="${task != null}">UPDATE TASK</c:if>
      <c:if test="${task == null}">ADD TASK</c:if>
     </h2>
    </caption>

    <c:if test="${task != null}">
     <input type="hidden" name="id" value="<c:out value='${task.taskId}' />" />
    </c:if>

    <fieldset>
     <label>Task Name</label> <input type="text" value="<c:out value='${task.taskName}' />" name="taskName" required="required" minlength="5">
    </fieldset>

    
    <fieldset>
        <label>Task Timing(to)</label> <input type="time" name="toTime" value='${task.toTime}' >
    </fieldset>

    <fieldset>
        <label>Task Timing(from)</label> <input type="time" name="fromTime" value='${task.fromTime}'>
    </fieldset>
    
    <fieldset>
     <label>Task Date(to)</label> <input type="date" value="<c:out value='${task.date}' />" name="date" required="required">
    </fieldset>

     <button type="submit"><c:if test="${task != null}">UPDATE</c:if>
        <c:if test="${task == null}">ADD</c:if></button>
    </form>
   </div>
  </div>
 </div>

</body>
</html>