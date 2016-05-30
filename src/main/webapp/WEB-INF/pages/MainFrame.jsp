
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Список студентов</title>
</head>

<body>
<p style="text-align: left">
  <button>Рус</button>
  <button>Eng</button> </p>
<form action="${pageContext.request.contextPath}/main" method="POST">
  <table>
    <tr>
      <td>Год:<input type="text" name="year" value="${form.year}"/><br/></td>
      <td>Список групп:
        <select name="groupId">
          <c:forEach var="group" items="${groupList}">
            <c:choose>
              <c:when test="${group.groupId==form.groupId}">
                <option value="${group.groupId}" selected><c:out value="${group.nameGroup}"/></option>
              </c:when>
              <c:otherwise>
                <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="getList" value="Обновить"/></td>
    </tr>
  </table>

  <p/>
  <b>Список студентов для выбранных параметров:</b>
    <br/>
    <table>
      <tr>
        <th> </th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
      </tr>
      <c:forEach var="student" items="${studentList}">
        <tr>
          <td><input type="radio" name="studentId" value="${student.studentId}"></td>
          <td><c:out value="${student.surName}"/></td>
          <td><c:out value="${student.firstName}"/></td>
          <td><c:out value="${student.patronymic}"/></td>
        </tr>
      </c:forEach>
    </table>

    <table>
      <tr>
        <td><input type="submit" value="Add" name="Add"/></td>
        <td><input type="submit" value="Edit" name="Edit"/></td>
        <td><input type="submit" value="Delete" name="Delete"/></td>
      </tr>
    </table>

    <p/>
    <b>Переместить студентов в группу</b>
      <br/>
      <table>
        <tr>
          <td>Год:<input type="text" name="newYear" value="${form.year}"/><br/></td>
          <td>Список групп:
            <select name="newGroupId">
              <c:forEach var="group" items="${groupList}">
                <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
              </c:forEach>
            </select>7
          </td>
          <td><input type="submit" name="MoveGroup" value="Переместить"/></td>
        </tr>
      </table>
</form>
</body>
</html>
