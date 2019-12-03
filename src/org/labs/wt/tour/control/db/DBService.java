
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.model.Identifier;

import java.util.List;


abstract class DBService<T extends Identifier> {

    private final String tableName;


    protected DBService(String tableName) {
        this.tableName = tableName;
    }

    protected List<T> getAllObjects() {
        return null;
    }

    protected List<T> filterObjects(FilterListener filter) {
        return null;
    }

    protected T getObjectByID(long id) {
        return null;
    }

    protected boolean addObject(T object) {
        return true;
    }

    protected boolean updateObject(T object) {
        return true;
    }

    protected boolean deleteObjectByID(long id) {
        return true;
    }

}
