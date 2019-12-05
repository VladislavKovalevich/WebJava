<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Country" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>

<% List<Country> list = null; %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Country country = new Country();
    country.setId(Long.parseLong(request.getParameter("countryID")));
    country.setCountryName(request.getParameter("countryName"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getCountryService().addCountry(country);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getCountryService().addCountry(country);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getCountryService().addCountry(country);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Country country = new Country();
    country.setId(Long.parseLong(request.getParameter("countryID")));
    country.setCountryName(request.getParameter("countryName"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getCountryService().updateCountry(country);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getCountryService().updateCountry(country);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getCountryService().updateCountry(country);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long countryID = Long.parseLong(request.getParameter("countryID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getCountryService().deleteCountry(countryID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getCountryService().deleteCountry(countryID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getCountryService().deleteCountry(countryID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getCountryService().getCountries();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getCountryService().getCountries();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getCountryService().getCountries();
    }
}
%>

<script language="JavaScript">
    <!--
    function callCountryItemClick(id, name) {
        document.serviceForm.countryID.value = id;
        document.serviceForm.countryName.value = name;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Country country : list) { %>
  <li onclick="callCountryItemClick('<%=country.getId()%>', '<%=country.getCountryName()%>')"><%="id:" + country.getId() + "; name: " + country.getCountryName()%></li>
  <% }%>
</ul>
<% }%>
