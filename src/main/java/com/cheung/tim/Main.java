package com.cheung.tim;

import com.cheung.tim.input.model.InputHeading;
import com.cheung.tim.input.model.LibraryCollection;
import com.cheung.tim.input.model.TaxiRank;
import com.cheung.tim.output.model.Taxi;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String BASE_DIR = "src/main/resources/";
    private static final String FILE_A = "a_example";
    private static final String FILE_B = "b_should_be_easy";
    private static final String FILE_C = "c_no_hurry";
    private static final String FILE_D = "d_metropolis";
    private static final String FILE_E = "e_high_bonus";

    private static final String IN_EXTENSION = ".in";
    private static final String OUT_EXTENSION = ".out";

    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        solve(FILE_E);
        return;
    }

    private static void solve(final String FILE_NAME) throws IOException {
        LibraryCollection libraryCollection = HashCodeFileParser.read(getInFileName(FILE_NAME));
        HashMap<Integer, Taxi> schedule = new HashMap<>();

    }

    public static String getInFileName(String name) {
        return BASE_DIR + name + IN_EXTENSION;
    }

    public static String getOutFileName(String name) {
        return BASE_DIR + "out/" + name + OUT_EXTENSION;
    }
}
