package org.labs.wt.tour.control;

import org.labs.wt.tour.model.Tour;

public interface IUserFunc {
    void deleteTour(Tour tour);
    Tour searchTour();
    void bookTour(Tour tour);
    Tour modifyTour(Tour tour);
    Tour createTour();
}
