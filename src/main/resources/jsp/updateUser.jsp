<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.DefaultCsrfToken"--%>
<%--
  Created by IntelliJ IDEA.
  User: eds2809
  Date: 11.02.2020
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update User page</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="2">
    <br>
    <br>
    <tr>
        <td>ID</td>
        <td>LOGIN</td>
        <td>PASS</td>
        <td>EMAIL</td>
        <td>ROLE</td>
    </tr>
    <tr>
        <form method="POST" action="${pageContext.request.contextPath}/admin/update">
            <td>
                <input type="text" name="id" value="${user.id}" hidden>
                ${user.id}
            </td>
            <td>
                <input type="text" name="login" value="${user.login}">
            </td>

            <td>
                <input type="text" name="pass" placeholder="новый пароль">
            </td>

            <td>
                <input type="text" name="email" value="${user.email}">
            </td>

            <td>
                <select name='role'>
                    <option value="${selected.role}" selected>${selected.role}</option>
                    <c:forEach items="${roles}" var="role">
                        <c:if test="${role.role != selected.role}">
                            <option value="${role.role}">${role.role}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>

            <td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">update</button>
            </td>

        </form>
    </tr>
</table>

</body>
</html>