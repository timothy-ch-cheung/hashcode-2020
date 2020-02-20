package com.cheung.tim;

import com.cheung.tim.input.model.Library;
import com.cheung.tim.input.model.LibraryCollection;
import com.cheung.tim.input.model.LibraryHeading;
import com.cheung.tim.output.model.LibrarySubmission;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String BASE_DIR = "src/main/resources/";
    private static final String FILE_A = "a_example";
    private static final String FILE_B = "b_read_on";
    private static final String FILE_C = "c_incunabula";
    private static final String FILE_D = "d_tough_choices";
    private static final String FILE_E = "e_so_many_books";
    private static final String FILE_F = "f_libraries_of_the_world";

    private static final String IN_EXTENSION = ".txt";
    private static final String OUT_EXTENSION = ".out";

    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        solve(FILE_A);
        return;
    }

    private static void solve(final String FILE_NAME) throws IOException {
        LibraryCollection libraryCollection = HashCodeFileParser.read(getInFileName(FILE_NAME));

        ArrayList<Library> goodLibraries = getGoodLibraries(libraryCollection.libraries, libraryCollection.booksScores);
        ArrayList<LibrarySubmission> librarySubmissions = new ArrayList<>();

        /**
         * (library, days)
         */

        HashMap<Library, Integer> libraryMap = new HashMap<>();
        int counter = 0;
        int signUpTime;
        for(int i = 0; i < goodLibraries.size(); i ++) {
            signUpTime = goodLibraries.get(i).data.get(LibraryHeading.signup_time.toString());
            libraryMap.put(goodLibraries.get(i),counter + signUpTime);
            counter += signUpTime;
        }
        for(Map.Entry<Library, Integer> entry : libraryMap.entrySet()) {
            Library key = entry.getKey();
            Integer value = entry.getValue();
            LibrarySubmission librarySubmission = new LibrarySubmission(key.libraryNum);
            System.out.println("Library and days: " + key.libraryNum + " " + value);

            HashSet<Integer> books;
            for(int i = 0; i < value;i ++) {
                for(int j = 0; j < key.data.get(LibraryHeading.books_per_day.toString()); j ++) {
                    Integer currentBook = key.books.get(j);
                    key.books.remove(j);
                    librarySubmission.addBook(currentBook);
                    System.out.println("Current book: " + currentBook);
                }
            }
            librarySubmissions.add(librarySubmission);
        }
        HashCodeFileWriter.write(getOutFileName(FILE_NAME), librarySubmissions);

    }

    private static ArrayList<Library> getGoodLibraries(HashMap<Integer, Library> libraries, HashMap<Integer, Integer> bookScores) {
        ArrayList<Library> libraryList = new ArrayList();
        for (Library li: libraries.values()){
            libraryList.add(li);
        }
        //Collections.sort(libraryList, Comparator.comparingInt(Library::getSignUpTime));
        HashMap<Integer, Integer> libraryScore;
        ArrayList<Integer> books;
        for(Library li : libraryList){
            Integer score = 0;
            books = li.books;
            for(Integer i : books){
                score += bookScores.get(i);
            }
            li.score = score;
        }
        Collections.sort(libraryList, Comparator.comparingInt(Library::getScore));
        return libraryList;
    }

    public static String getInFileName(String name) {
        return BASE_DIR + name + IN_EXTENSION;
    }

    public static String getOutFileName(String name) {
        return BASE_DIR + "out/" + name + OUT_EXTENSION;
    }
}
