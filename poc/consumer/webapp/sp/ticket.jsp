<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dk.itst.oiosaml.sp.UserAssertionHolder"%>
<%@page import="dk.itst.oiosaml.sp.service.util.Utils"%>
<%@page import="dk.itst.oiosaml.sp.util.AttributeUtil"%>
<%@page import="dk.itst.oiosaml.sp.UserAssertion"%>
<%@page import="dk.itst.oiosaml.sp.UserAttribute"%>
<jsp:include page="/head.jsp" />


<h1>STS Ticket request</h1>

<h2>EPR</h2>
<pre>
<%= Utils.beautifyAndHtmlXML((String)request.getAttribute("epr"), "&nbsp;&nbsp;&nbsp;&nbsp;") %>
</pre>

<h2>Token</h2>
<pre>
<%= Utils.beautifyAndHtmlXML((String)request.getAttribute("message"), "&nbsp;&nbsp;&nbsp;&nbsp;") %>
</pre>


<a href="request">Perform token WS request</a>

  </body>
</html>
