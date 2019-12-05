<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Tour" %>
<%@ page import = "org.labs.wt.tour.model.Client" %>
<%@ page import = "org.labs.wt.tour.model.Hotel" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<% List<Tour> list = null; %>

<% SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Tour tour = new Tour();
    tour.setId(Long.parseLong(request.getParameter("tourID")));
    tour.setClient(new Client());
    tour.getClient().setId(Long.parseLong(request.getParameter("clientID")));
    tour.setHotel(new Hotel());
    tour.getHotel().setId(Long.parseLong(request.getParameter("hotelID")));

    try {
        tour.setFrom(dateFormat.parse(request.getParameter("dateFrom")));
        tour.setTo(dateFormat.parse(request.getParameter("dateTo")));
    } catch (Exception ex) {
    }
    tour.setPersonCount(Integer.parseInt(request.getParameter("persons")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTourService().addTour(tour);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTourService().addTour(tour);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTourService().addTour(tour);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Tour tour = new Tour();
    tour.setId(Long.parseLong(request.getParameter("tourID")));
    tour.setClient(new Client());
    tour.getClient().setId(Long.parseLong(request.getParameter("clientID")));
    tour.setHotel(new Hotel());
    tour.getHotel().setId(Long.parseLong(request.getParameter("hotelID")));

    try {
        tour.setFrom(dateFormat.parse(request.getParameter("dateFrom")));
        tour.setTo(dateFormat.parse(request.getParameter("dateTo")));
    } catch (Exception ex) {
    }
    tour.setPersonCount(Integer.parseInt(request.getParameter("persons")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTourService().updateTour(tour);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTourService().updateTour(tour);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTourService().updateTour(tour);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long tourID = Long.parseLong(request.getParameter("tourID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getTourService().deleteTour(tourID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getTourService().deleteTour(tourID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getTourService().deleteTour(tourID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getTourService().getTours();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getTourService().getTours();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getTourService().getTours();
    }
}
%>

<script language="JavaScript">
    <!--
    function callTourItemClick(id, client, hotel, dateFrom, dateTo, persons) {
        document.serviceForm.tourID.value = id;
        document.serviceForm.clientID.value = client;
        document.serviceForm.hotelID.value = hotel;
        document.serviceForm.dateFrom.value = dateFrom;
        document.serviceForm.dateTo.value = dateTo;
        document.serviceForm.persons.value = persons;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Tour tour : list) { %>
  <li onclick="callTourItemClick('<%=tour.getId()%>', '<%=tour.getClient().getId()%>', '<%=tour.getHotel().getId()%>', '<%=dateFormat.format(tour.getFrom())%>', '<%=dateFormat.format(tour.getTo())%>', '<%=tour.getPersonCount()%>')">
    <%="id:" + tour.getId() + "; clientID: " + tour.getClient().getId() + "; hotelID: " + tour.getHotel().getId() + "; fromDate: " + tour.getFrom().toString() + "; toDate: " + tour.getTo().toString() + "; persons: " + tour.getPersonCount()%>
  </li>
  <% }%>
</ul>
<% }%>
