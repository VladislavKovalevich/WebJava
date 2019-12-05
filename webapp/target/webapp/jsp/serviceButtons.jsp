<script language="JavaScript">
    <!--
    function service(name) {
        document.serviceSelect.serviceName.value = name;
        serviceSelect.submit();
    }
    // -->
</script>

<form name="serviceSelect" method="POST">
    <input type="hidden" name="serviceName">
    <input type="button" value="Country" onclick="service('country')">
    <input type="button" value="Region" onclick="service('region')">
    <input type="button" value="Hotel" onclick="service('hotel')">
    <input type="button" value="Client" onclick="service('client')">
    <input type="button" value="Tour" onclick="service('tour')">
    <input type="button" value="Transport" onclick="service('transport')">
</form>

<%
    if(request.getParameter("serviceName") != null) {
        out.println("<b>service: " + request.getParameter("serviceName") + "</b>");
    }
%>
<%
    if(request.getParameter("serviceAction") != null) {
        out.println("<b>action: " + request.getParameter("serviceAction") + "</b>");
    }
%>
