
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Tour;

import java.util.List;


public interface TourService {

    List<Tour> getTours();

    Tour getTourById(long id);

    boolean addTour(Tour tour);

    boolean updateTour(Tour tour);

    boolean deleteTour(long id);

}
