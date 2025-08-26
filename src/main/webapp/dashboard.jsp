<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
...
<h3>Xin chào, ${sessionScope.user.username}!</h3>

<%-- Chỉ hiển thị phần này nếu người dùng là admin --%>
<c:if test="${isAdmin}">
    <h2>Quản lý người dùng</h2>
    <table>
            <%-- Vòng lặp hiển thị tất cả user --%>
        <c:forEach items="${allUsers}" var="u">
            <tr>
                <td>${u.username}</td>
                <td>
                        <%-- Form để thêm/xóa role cho user này --%>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%-- Người dùng thường chỉ thấy thông tin của họ --%>
<c:if test="${!isAdmin}">
    <p>Đây là trang cá nhân của bạn.</p>
    <p>Email: ${sessionScope.user.email}</p>
</c:if>
...