
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.Client;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.Tour;

import java.text.SimpleDateFormat;
import java.util.List;


public class TourFileService extends FileService<Tour> implements TourService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public TourFileService(String file) {
        super(file);
    }

    @Override
    public List<Tour> getTours() {
        return getAllObjects();
    }

    @Override
    public Tour getTourById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addTour(Tour tour) {
        return addObject(tour);
    }

    @Override
    public boolean updateTour(Tour tour) {
        return updateObject(tour);
    }

    @Override
    public boolean deleteTour(long id) {
        return deleteObjectByID(id);
    }

    @Override
    protected Tour convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Tour tour = new Tour();
            tour.setClient(new Client());
            tour.setHotel(new Hotel());

            tour.setId(Long.parseLong(parts[0]));
            tour.getClient().setId(Long.parseLong(parts[1]));
            tour.getHotel().setId(Long.parseLong(parts[2]));
            try {
                tour.setFrom(dateFormat.parse(parts[3]));
                tour.setTo(dateFormat.parse(parts[4]));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tour.setPersonCount(Integer.valueOf(parts[5]));

            return tour;
        }

        return null;
    }

    @Override
    protected String convertToString(Tour object) {
        StringBuffer buffer = new StringBuffer().
                append(object.getId()).append("|").
                append(object.getClient().getId()).append("|").
                append(object.getHotel().getId()).append("|").
                append(dateFormat.format(object.getFrom())).append("|").
                append(dateFormat.format(object.getTo())).append("|").
                append(object.getPersonCount().intValue());

        return buffer.toString();
    }

}
