
<% if((request.getParameter("serviceName") != null) && (request.getParameter("serviceAction") != null)) { %>

    <% if(request.getParameter("serviceName").equals("country")) { %>
        <%@ include file="countryViewAll.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("region")) { %>
        <%@ include file="regionViewAll.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("hotel")) { %>
        <%@ include file="hotelViewAll.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("client")) { %>
        <%@ include file="clientViewAll.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("tour")) { %>
        <%@ include file="tourViewAll.jsp" %>
    <% } %>

    <% if(request.getParameter("serviceName").equals("transport")) { %>
        <%@ include file="transportViewAll.jsp" %>
    <% } %>

<% }%>