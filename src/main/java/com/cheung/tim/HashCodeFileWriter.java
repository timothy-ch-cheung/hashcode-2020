package com.cheung.tim;

import com.cheung.tim.output.model.LibrarySubmission;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class HashCodeFileWriter {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String SPACE = " ";

    public static void write(String filename, List<LibrarySubmission> submission) throws IOException {
        BufferedWriter bw = null;
        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(submission.size() + LINE_SEPARATOR);

            for(LibrarySubmission t : submission){
                bw.write(t.getLibrary() + SPACE + t.getNumBooks() + LINE_SEPARATOR);
                bw.write(t.getBooks() + LINE_SEPARATOR);
            }

        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }

        }
    }
}
