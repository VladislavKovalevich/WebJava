<% if(request.getParameter("serviceName") != null) { %>

<br/>
<script language="JavaScript">
    <!--
    function callAction(action, name) {
        document.serviceForm.serviceName.value = name;
        document.serviceForm.serviceAction.value = action;
        document.serviceForm.serviceType.value = document.serviceFormButtons.serviceType.value;
        document.serviceForm.submit();
    }
    // -->
</script>
<form name="serviceFormButtons" method="POST">
    <input type="button" value="View All" onclick="callAction('viewAll', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Insert" onclick="callAction('insert', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Update" onclick="callAction('update', '<%=request.getParameter("serviceName")%>')">
    <input type="button" value="Delete" onclick="callAction('delete', '<%=request.getParameter("serviceName")%>')">
    <select name="serviceType">
      <option value="file" <%=("file".equals(request.getParameter("serviceType")) ? "selected" : "")%>>File</option>
      <option value="xml" <%=("xml".equals(request.getParameter("serviceType")) ? "selected" : "")%>>XML</option>
      <option value="db" <%=("db".equals(request.getParameter("serviceType")) ? "selected" : "")%>>DB</option>
    </select>
</form>

<% } %>
