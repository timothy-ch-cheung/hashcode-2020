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

    static int daysToProcess;

    public static void main(String[] args) throws IOException {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//        solve(FILE_A);
        solve(FILE_B);
        solve(FILE_C);
        solve(FILE_D);
        solve(FILE_E);
        solve(FILE_F);

        return;
    }

    private static void solve(final String FILE_NAME) throws IOException {
        LibraryCollection libraryCollection = HashCodeFileParser.read(getInFileName(FILE_NAME));

        daysToProcess = libraryCollection.daysForScanning;

        ArrayList<Library> goodLibraries = getGoodLibraries(libraryCollection.libraries, libraryCollection.booksScores);
        ArrayList<LibrarySubmission> librarySubmissions = new ArrayList<>();

        /**
         * (library, days)
         */

        HashMap<Library, Integer> libraryMap = new HashMap<>();
        HashSet<Integer> booksCounted = new HashSet<>();
        int counter = 0;
        int signUpTime;
        for(int i = 0; i < goodLibraries.size(); i ++) {
            signUpTime = goodLibraries.get(i).data.get(LibraryHeading.signup_time.toString());
            counter += signUpTime;
            if (counter > libraryCollection.daysForScanning){
//                libraryMap.put(goodLibraries.get(i),counter);
                break;
            }
            libraryMap.put(goodLibraries.get(i),counter);

        }
        int counterDancho = 0;
        System.out.println(libraryMap.size());
        for(Map.Entry<Library, Integer> entry : libraryMap.entrySet()) {
            counterDancho += 1;
            System.out.println(counterDancho);
//            if (counterDancho > 2000){
//                break;
//            }
            Library key = entry.getKey();
            Integer value = entry.getValue();
            LibrarySubmission librarySubmission = new LibrarySubmission(key.libraryNum);
//            System.out.println();
//            System.out.println("Library and days: " + key.libraryNum + " " + value);


            HashSet<Integer> books;
            for(int i = 0; i < value;i ++) {
                for(int j = 0; j < key.data.get(LibraryHeading.books_per_day.toString()); j ++) {
                    if (key.books.size() > 0){
                        Integer currentBook = key.books.get(0);
                        while(booksCounted.contains(currentBook) && key.books.size() >1){
                            key.books.remove(0);
                            currentBook = key.books.get(0);

                        }
                        key.books.remove(0);
                        librarySubmission.addBook(currentBook);
                        booksCounted.add(currentBook);
//                        System.out.println("Current book: " + currentBook);
                    }
                    else{
                        break;
                    }

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
            int weight = li.getBooksPerDay() * daysToProcess;
            int length = books.size() > weight ? weight : books.size();
            for(Integer i = 0; i < length; i++){
//                System.out.println("curr: " + bookScores.get(i));
                score += bookScores.get(i);
            }
            li.score = score * li.getSignUpTime();
//            System.out.println("Score: " + score);
        }

//        Collections.sort(libraryList, Comparator.comparingInt(Library::getNumOfBooks));

//        Collections.sort(libraryList, Comparator.comparingInt(Library::getSignUpTime));


        Collections.sort(libraryList, Comparator.comparingInt(Library::getBooksPerDay));
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
