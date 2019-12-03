
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.model.Identifier;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;


abstract class FileService<T extends Identifier> {

    private final String file;

    private Writer writer = null;

    private List<T> objects = null;


    protected abstract String convertToString(T object);

    protected abstract T convertToObject(String line);


    protected FileService(final String file) {
        this.file = file;

        try {
            Path path = Paths.get(file);
            if (!Files.exists(path)) {
                if ((path.getParent() != null) && (!Files.exists(path.getParent()))) {
                    Files.createDirectories(path.getParent());
                }
                Files.createFile(path);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected List<T> getAllObjects() {
        if (objects != null) {
            return objects;
        }

        objects = new ArrayList<>();

        checkFile(line -> {
            T object = convertToObject(line);
            if (object != null) {
                objects.add(object);
            }

            return true;
        });

        return objects;
    }

    protected List<T> filterObjects(FilterListener filter) {
        List<T> filtered = new ArrayList<>();

        for (T obj : getAllObjects()) {
            if (filter.filter(obj)) {
                filtered.add(obj);
            }
        }

        return filtered;
    }

    protected T getObjectByID(long id) {
        List<T> list = getAllObjects();

        for (T obj : list) {
            if (obj.getId() == id) {
                return obj;
            }
        }

        return null;
    }

    protected boolean addObject(T object) {
        List<T> list = getAllObjects();

        for (T obj : list) {
            if (obj.getId() == object.getId()) {
                return false;
            }
        }

        appendSingleRecord(convertToString(object));
        objects.add(object);

        return true;
    }

    protected boolean updateObject(T object) {
        List<T> list = getAllObjects();

        T objToUpdate = null;

        for (T obj : list) {
            if (obj.getId() == object.getId()) {
                objToUpdate = obj;
            }
        }

        if (objToUpdate == null) {
            return false;
        }

        renameAndCheckFile(line -> {
            if ((line != null) && (line.trim().length() > 0)) {
                String[] parts = line.split("\\|");

                long key = Long.parseLong(parts[0]);
                if (key == object.getId()) {
                    appendRecord(convertToString(object));
                } else {
                    appendRecord(line);
                }
            }
            return true;
        });

        objects = null;

        return true;
    }

    protected boolean deleteObjectByID(long id) {
        List<T> list = getAllObjects();

        T objToDelete = null;

        for (T obj : list) {
            if (obj.getId() == id) {
                objToDelete = obj;
            }
        }

        if (objToDelete == null) {
            return false;
        }

        renameAndCheckFile(line -> {
            if ((line != null) && (line.trim().length() > 0)) {
                String[] parts = line.split("\\|");

                long key = Long.parseLong(parts[0]);
                if (key != id) {
                    appendRecord(line);
                }
            }
            return true;
        });

        objects = null;

        return true;
    }

    private synchronized void checkFile(LineListener listener) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (!listener.checkLine(line)) {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private synchronized void renameAndCheckFile(LineListener listener) {
        BufferedReader bufferedReader = null;

        Path source = null;
        Path temp = null;

        try {
            source = Paths.get(file);
            temp = Files.createTempFile("file", null);

            Files.copy(source, temp, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(source);

            openFile(false);

            bufferedReader = new BufferedReader(new FileReader(temp.toFile()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                listener.checkLine(line);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                    Files.delete(temp);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            closeFile();
        }
    }

    private synchronized boolean appendSingleRecord(String line) {
        openFile(true);
        appendRecord(line);
        closeFile();
        return true;
    }

    private synchronized void openFile(boolean isAppend) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void closeFile() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();

                writer = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void appendRecord(String line) {
        try {
            writer.append(line);
            writer.append(System.lineSeparator());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
