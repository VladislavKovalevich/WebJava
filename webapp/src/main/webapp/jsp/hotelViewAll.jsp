<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Hotel" %>
<%@ page import = "org.labs.wt.tour.model.HotelRank" %>
<%@ page import = "org.labs.wt.tour.model.FoodType" %>
<%@ page import = "org.labs.wt.tour.model.Region" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>

<% List<Hotel> list = null; %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Hotel hotel = new Hotel();
    hotel.setId(Long.parseLong(request.getParameter("hotelID")));
    hotel.setHotelName(request.getParameter("hotelName"));
    hotel.setRegion(new Region());
    hotel.getRegion().setId(Long.parseLong(request.getParameter("regionID")));
    hotel.setRank(HotelRank.valueOf(request.getParameter("rank")));
    hotel.setMaxFoodType(FoodType.valueOf(request.getParameter("foodType")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getHotelService().addHotel(hotel);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getHotelService().addHotel(hotel);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getHotelService().addHotel(hotel);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Hotel hotel = new Hotel();
    hotel.setId(Long.parseLong(request.getParameter("hotelID")));
    hotel.setHotelName(request.getParameter("hotelName"));
    hotel.setRegion(new Region());
    hotel.getRegion().setId(Long.parseLong(request.getParameter("regionID")));
    hotel.setRank(HotelRank.valueOf(request.getParameter("rank")));
    hotel.setMaxFoodType(FoodType.valueOf(request.getParameter("foodType")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getHotelService().updateHotel(hotel);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getHotelService().updateHotel(hotel);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getHotelService().updateHotel(hotel);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long hotelID = Long.parseLong(request.getParameter("hotelID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getHotelService().deleteHotel(hotelID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getHotelService().deleteHotel(hotelID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getHotelService().deleteHotel(hotelID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getHotelService().getHotels();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getHotelService().getHotels();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getHotelService().getHotels();
    }
}
%>

<script language="JavaScript">
    <!--
    function callHotelItemClick(id, name, region, rank, food) {
        document.serviceForm.hotelID.value = id;
        document.serviceForm.hotelName.value = name;
        document.serviceForm.regionID.value = region;
        document.serviceForm.rank.value = rank;
        document.serviceForm.foodType.value = food;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Hotel hotel : list) { %>
  <li onclick="callHotelItemClick('<%=hotel.getId()%>', '<%=hotel.getHotelName()%>', '<%=hotel.getRegion().getId()%>', '<%=hotel.getRank().name()%>', '<%=hotel.getMaxFoodType().name()%>')">
    <%="id:" + hotel.getId() + "; name: " + hotel.getHotelName() + "; region: " + hotel.getRegion().getId() + "; rank: " + hotel.getRank().name() + "; food: " + hotel.getMaxFoodType().name()%>
  </li>
  <% }%>
</ul>
<% }%>
