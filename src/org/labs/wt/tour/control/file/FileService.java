
package org.labs.wt.tour.control.file;


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
        List<T> list = new ArrayList<>();

        checkFile(line -> {
            T object = convertToObject(line);
            if (object != null) {
                list.add(object);
            }

            return true;
        });

        return list;
    }

    protected T getObjectByID(long id) {
        List<T> list = new ArrayList<>();

        checkFile(line -> {
            if ((line != null) && (line.trim().length() > 0)) {
                String[] parts = line.split("\\|");

                long key = Long.parseLong(parts[0]);
                if (key == id) {
                    T object = convertToObject(line);
                    if (object != null) {
                        list.add(object);
                    }

                    return false;
                }
            }

            return true;
        });

        return list.size() > 0 ? list.get(0) : null;
    }

    protected boolean addObject(T object) {
        return appendSingleRecord(convertToString(object));
    }

    protected boolean updateObject(T object) {
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

        return true;
    }

    protected boolean deleteObjectByID(long id) {
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
