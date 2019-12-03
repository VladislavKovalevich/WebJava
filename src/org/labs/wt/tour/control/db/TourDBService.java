
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.Tour;

import java.util.List;


public class TourDBService extends DBService<Tour> implements TourService {


    public TourDBService() {
        super("tour");
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

}
