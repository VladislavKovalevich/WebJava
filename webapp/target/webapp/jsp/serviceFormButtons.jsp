<% if(request.getParameter("serviceName") != null) { %>

<br/>
<script language="JavaScript">
    <!--
    function callAction(action, name) {
        document.serviceFormButtons.serviceName.value = name;
        document.serviceFormButtons.serviceAction.value = action;
        serviceFormButtons.submit();
    }
    // -->
</script>
<form name="serviceFormButtons" method="POST">
    <input type="hidden" name="serviceName">
    <input type="hidden" name="serviceAction">
    <input type="button" value="View All" onclick="callAction('viewAll', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Insert" onclick="callAction('insert', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Update" onclick="callAction('update', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Delete" onclick="callAction('delete', '<%=request.getParameter("serviceName")%>')">
</form>

<% } %>
