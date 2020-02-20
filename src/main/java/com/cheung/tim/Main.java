package com.cheung.tim;

import com.cheung.tim.input.model.InputHeading;
import com.cheung.tim.input.model.TaxiRank;
import com.cheung.tim.output.model.Taxi;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String BASE_DIR = "src/main/resources/";
    private static final String FILE_A = "a_example";
    private static final String FILE_B = "b_read_on";
    private static final String FILE_C = "c_incunabula";
    private static final String FILE_D = "d_tough_choices";
    private static final String FILE_E = "e_so_many_books";

    private static final String IN_EXTENSION = ".txt";
    private static final String OUT_EXTENSION = ".out";

    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        solve(FILE_E);
        return;
    }

    private static void solve(final String FILE_NAME) throws IOException {
        TaxiRank taxiRank = HashCodeFileParser.read(getInFileName(FILE_NAME));
        System.out.println(taxiRank);

        Map<String, Integer> headers = taxiRank.getHeader();
        List<Map<String, Integer>> rides = taxiRank.getRides();

        HashMap<Integer, Taxi> schedule = new HashMap<>();

        for (int i = 0; i < headers.get(InputHeading.vehicles.toString()); i++) {
            schedule.put(i, new Taxi(i));
        }

        Random r = new Random();

        for (Map<String, Integer> ride : rides) {
            int choice = r.nextInt(headers.get(InputHeading.vehicles.toString()));
            schedule.get(choice).addRide(ride.get("id"));
        }

        HashCodeFileWriter.write(getOutFileName(FILE_NAME), new ArrayList(schedule.values()));
    }

    public static String getInFileName(String name) {
        return BASE_DIR + name + IN_EXTENSION;
    }

    public static String getOutFileName(String name) {
        return BASE_DIR + "out/" + name + OUT_EXTENSION;
    }
}
