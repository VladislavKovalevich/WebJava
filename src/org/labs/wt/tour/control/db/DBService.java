
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.model.Identifier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


abstract class DBService<T extends Identifier> {

    private final String tableName;


    protected DBService(String tableName) {
        this.tableName = tableName;
    }


    protected List<T> getAllObjects(ResultSetListener<T> listener) {
        String sql = "select * from " + tableName;

        List<T> objects = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DBSource.getInstance().getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                objects.add(listener.onResultSet(resultSet));
            }

            resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return objects;
    }

    protected List<T> filterObjects(FilterListener filter) {
        return new ArrayList<>();
    }

    protected T getObjectByID(long id, String idName, ResultSetListener<T> listener) {
        String sql = "select * from " + tableName + " where " + idName + " = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBSource.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return listener.onResultSet(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    protected boolean addObject(String sql, PreparedStatementListener listener) {
        PreparedStatement preparedStatement = null;

        Connection connection = null;
        try {
            connection = DBSource.getInstance().getConnection();

            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(sql);
            listener.onPreparedStatement(preparedStatement);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    protected boolean updateObject(String sql, PreparedStatementListener listener) {
        PreparedStatement preparedStatement = null;

        Connection connection = null;
        try {
            connection = DBSource.getInstance().getConnection();

            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(sql);
            listener.onPreparedStatement(preparedStatement);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    protected boolean deleteObjectByID(long id, String idName) {
        try {
            String sql = "delete from " + tableName + " where " + idName + " = " + id;

            Connection connection = DBSource.getInstance().getConnection();
            connection.setAutoCommit(true);
            connection.createStatement().executeUpdate(sql);
            connection.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
