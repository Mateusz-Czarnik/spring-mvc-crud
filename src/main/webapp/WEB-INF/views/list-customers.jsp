<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>List customers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
    <div id="container">
        <div id="content">
            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                <input class="add-button" type="button" value="Add Customer">
            </security:authorize>
            <form:form action="search" method="POST">
                Search customer by name: <input type="text" name="name"/>

                <input type="submit" value="Search" class="add-button"/>
            </form:form>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                        <th>Action</th>
                    </security:authorize>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <c:url var="updateLink" value="/customer/update">
                        <c:param name="id" value="${customer.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="id" value="${customer.id}"/>
                    </c:url>

                    <tr>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                            <td>
                                <a href="${updateLink}">Update</a>
                                <security:authorize access="hasAnyRole('ADMIN')">
                                    |
                                    <a class="delete-button" href="${deleteLink}">Delete</a>
                                </security:authorize>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">
    <input type="submit" value="Logout" class="add-button"/>
</form:form>

</body>
</html>
