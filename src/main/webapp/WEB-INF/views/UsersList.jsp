<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Users List</title>
</head>
<body>
<header>
    <h1>UsersList</h1>
</header>
<main>
    <table class="table table-striped">
       <thead>
         <tr>
           <th scope="col">ID</th>
             <th scope="col">FIRST NAME</th>
             <th scope="col">LAST NAME</th>
             <th scope="col">EMAIL</th>
         </tr>
       </thead>
        <Tbody>
        <c:forEach items="${usersVue}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.fistname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>
                    <a href="deleteUser?id=${user.id}">
                        Delete
                    </a>
                </td>
            </tr>

        </c:forEach>
        </Tbody>
    </table>

</main>
<footer>
    <a href="createUser">User Creation</a>
</footer>
</body>
</html>


]]>