<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Transport" %>
<%@ page import = "org.labs.wt.tour.model.Tour" %>
<%@ page import = "org.labs.wt.tour.model.Country" %>
<%@ page import = "org.labs.wt.tour.model.TransportType" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>

<% List<Transport> list = null; %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Transport transport = new Transport();
    transport.setId(Long.parseLong(request.getParameter("transportID")));
    transport.setTour(new Tour());
    transport.getTour().setId(Long.parseLong(request.getParameter("tourID")));
    transport.setType(TransportType.valueOf(request.getParameter("transportType")));
    transport.setFrom(new Country());
    transport.getFrom().setId(Long.parseLong(request.getParameter("fromID")));
    transport.setTo(new Country());
    transport.getTo().setId(Long.parseLong(request.getParameter("toID")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTransportService().addTransport(transport);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTransportService().addTransport(transport);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTransportService().addTransport(transport);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Transport transport = new Transport();
    transport.setId(Long.parseLong(request.getParameter("transportID")));
    transport.setTour(new Tour());
    transport.getTour().setId(Long.parseLong(request.getParameter("tourID")));
    transport.setType(TransportType.valueOf(request.getParameter("transportType")));
    transport.setFrom(new Country());
    transport.getFrom().setId(Long.parseLong(request.getParameter("fromID")));
    transport.setTo(new Country());
    transport.getTo().setId(Long.parseLong(request.getParameter("toID")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTransportService().updateTransport(transport);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTransportService().updateTransport(transport);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTransportService().updateTransport(transport);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long transportID = Long.parseLong(request.getParameter("transportID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTransportService().deleteTransport(transportID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTransportService().deleteTransport(transportID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTransportService().deleteTransport(transportID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getTransportService().getTransports();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getTransportService().getTransports();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getTransportService().getTransports();
    }
}
%>

<script language="JavaScript">
    <!--
    function callTransportItemClick(id, tour, type, countryFrom, countryTo) {
        document.serviceForm.transportID.value = id;
        document.serviceForm.tourID.value = tour;
        document.serviceForm.transportType.value = type;
        document.serviceForm.fromID.value = countryFrom;
        document.serviceForm.toID.value = countryTo;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Transport transport : list) { %>
  <li onclick="callTransportItemClick('<%=transport.getId()%>', '<%=transport.getTour().getId()%>', '<%=transport.getType().name()%>', '<%=transport.getFrom().getId()%>', '<%=transport.getTo().getId()%>')">
    <%="id:" + transport.getId() + "; tourID: " + transport.getTour().getId() + "; type: " + transport.getType().name() + "; countryFromID: " + transport.getFrom().getId() + "; countryToID: " + transport.getTo().getId()%>
  </li>
  <% }%>
</ul>
<% }%>
