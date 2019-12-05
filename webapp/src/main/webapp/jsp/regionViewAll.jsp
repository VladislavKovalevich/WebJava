<%@ page import = "java.util.List" %>
<%@ page import = "java.lang.Long" %>
<%@ page import = "org.labs.wt.tour.model.Region" %>
<%@ page import = "org.labs.wt.tour.model.Country" %>
<%@ page import = "org.labs.wt.tour.control.TourServices" %>

<% List<Region> list = null; %>


<%
if(request.getParameter("serviceAction").equals("insert")) {
    Region region = new Region();
    region.setId(Long.parseLong(request.getParameter("regionID")));
    region.setRegionName(request.getParameter("regionName"));
    region.setCountry(new Country());
    region.getCountry().setId(Long.parseLong(request.getParameter("countryID")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getRegionService().addRegion(region);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getRegionService().addRegion(region);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getRegionService().addRegion(region);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("update")) {
    Region region = new Region();
    region.setId(Long.parseLong(request.getParameter("regionID")));
    region.setRegionName(request.getParameter("regionName"));
    region.setCountry(new Country());
    region.getCountry().setId(Long.parseLong(request.getParameter("countryID")));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getRegionService().updateRegion(region);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getRegionService().updateRegion(region);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getRegionService().updateRegion(region);
    }
}
%>

<%
if(request.getParameter("serviceAction").equals("delete")) {
    long regionID = Long.parseLong(request.getParameter("regionID"));

    if (request.getParameter("serviceType").equals("file")) {
        TourServices.getFileServices().getRegionService().deleteRegion(regionID);
    } else if (request.getParameter("serviceType").equals("xml")) {
        TourServices.getXmlServices().getRegionService().deleteRegion(regionID);
    } else if (request.getParameter("serviceType").equals("db")) {
        TourServices.getDBServices().getRegionService().deleteRegion(regionID);
    }
}
%>


<%
if(request.getParameter("serviceAction").equals("viewAll")) {
    if (request.getParameter("serviceType").equals("file")) {
        list = TourServices.getFileServices().getRegionService().getRegions();
    } else if (request.getParameter("serviceType").equals("xml")) {
        list = TourServices.getXmlServices().getRegionService().getRegions();
    } else if (request.getParameter("serviceType").equals("db")) {
        list = TourServices.getDBServices().getRegionService().getRegions();
    }
}
%>

<script language="JavaScript">
    <!--
    function callRegionItemClick(id, name, country) {
        document.serviceForm.regionID.value = id;
        document.serviceForm.regionName.value = name;
        document.serviceForm.countryID.value = country;
    }
    // -->
</script>


<% if(list != null) { %>
<ul>
  <% for(Region region : list) { %>
  <li onclick="callRegionItemClick('<%=region.getId()%>', '<%=region.getRegionName()%>', '<%=region.getCountry().getId()%>')">
    <%="id:" + region.getId() + "; name: " + region.getRegionName() + "; countryID: " + region.getCountry().getId()%>
  </li>
  <% }%>
</ul>
<% }%>
