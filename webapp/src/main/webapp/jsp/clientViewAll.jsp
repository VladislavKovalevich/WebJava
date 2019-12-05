<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Client" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>

<% List<Client> list = null; %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Client client = new Client();
    client.setId(Long.parseLong(request.getParameter("clientID")));
    client.setName(request.getParameter("name"));
    client.setSirname(request.getParameter("sirname"));
    client.setAddress(request.getParameter("address"));
    client.setPhone(request.getParameter("phone"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getClientService().addClient(client);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getClientService().addClient(client);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getClientService().addClient(client);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Client client = new Client();
    client.setId(Long.parseLong(request.getParameter("clientID")));
    client.setName(request.getParameter("name"));
    client.setSirname(request.getParameter("sirname"));
    client.setAddress(request.getParameter("address"));
    client.setPhone(request.getParameter("phone"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getClientService().updateClient(client);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getClientService().updateClient(client);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getClientService().updateClient(client);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long clientID = Long.parseLong(request.getParameter("clientID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getClientService().deleteClient(clientID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getClientService().deleteClient(clientID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getClientService().deleteClient(clientID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getClientService().getClients();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getClientService().getClients();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getClientService().getClients();
    }
}
%>

<script language="JavaScript">
    <!--
    function callClientItemClick(id, name, sirname, address, phone) {
        document.serviceForm.clientID.value = id;
        document.serviceForm.name.value = name;
        document.serviceForm.sirname.value = sirname;
        document.serviceForm.address.value = address;
        document.serviceForm.phone.value = phone;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Client client : list) { %>
  <li onclick="callClientItemClick('<%=client.getId()%>', '<%=client.getName()%>', '<%=client.getSirname()%>', '<%=client.getAddress()%>', '<%=client.getPhone()%>')">
    <%="id:" + client.getId() + "; name: " + client.getName() + "; sirname: " + client.getSirname() + "; address: " + client.getAddress() + "; phone: " + client.getPhone()%>
  </li>
  <% }%>
</ul>
<% }%>
