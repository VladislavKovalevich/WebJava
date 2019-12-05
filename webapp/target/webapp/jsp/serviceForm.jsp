<% if(request.getParameter("serviceName") != null) { %>

    <% if(request.getParameter("serviceName").equals("country")) { %>
        <%@ include file="countryForm.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("region")) { %>
        <%@ include file="regionForm.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("hotel")) { %>
        <%@ include file="hotelForm.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("client")) { %>
        <%@ include file="clientForm.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("tour")) { %>
        <%@ include file="tourForm.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("transport")) { %>
        <%@ include file="transportForm.jsp" %>
    <% } %>

<% } %>
