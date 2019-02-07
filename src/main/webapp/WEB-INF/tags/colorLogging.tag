<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="color" type="java.lang.String" required="false" %>
<%-- required는 파라미터 필수유무를 판단 default는 false --%>
<%@ attribute name="size" type="java.lang.Integer" %>
<%-- size만큼 =을 출력한다 --%>
<font color="${color }">
<c:forEach begin="1" end ="${size }">=</c:forEach>
<br>
</font>