
package org.labs.wt.tour.control.db;


import java.sql.ResultSet;


public interface ResultSetListener<T> {

    T onResultSet(ResultSet resultSet);

}
