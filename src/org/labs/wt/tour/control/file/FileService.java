
package org.labs.wt.tour.control.file;


import java.io.*;


public class FileService {

    private final String file;

    FileService(final String file) {
        this.file = file;
    }

    protected boolean checkFile(LineListener listener) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (!listener.checkLine(line)) {
                    return true;
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

        return true;
    }

    protected boolean appendRecord(String line) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            writer.append(line);
            writer.append(System.lineSeparator());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

}
