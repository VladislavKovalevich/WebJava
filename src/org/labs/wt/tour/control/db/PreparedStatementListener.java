
package org.labs.wt.tour.control.db;


import java.sql.PreparedStatement;


public interface PreparedStatementListener {

    void onPreparedStatement(PreparedStatement preparedStatement);

}
