<html>
    <head> </head>
    <body>
    	<% if(request.getUserPrincipal() != null) { %>
        	<jsp:forward page="protected/ownAccount.xhtml" />
        <% } else {%>
        	<jsp:forward page="public/register.xhtml" />
        <% } %>
    </body>
</html>