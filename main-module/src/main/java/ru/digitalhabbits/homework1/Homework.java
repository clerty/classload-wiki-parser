package ru.digitalhabbits.homework1;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
        final String searchString = getSearchString(args);
        final String revision = getRevision(args);
        new WikipediaSearchEngine().search(searchString, revision);
    }

    @Nonnull
    private static String getSearchString(String[] args) {
        if (args.length > 0) {
            return args[0];
        }
        final Scanner in = new Scanner(System.in);
        System.out.print("Enter search string: ");
        return in.nextLine();
    }

    @Nullable
    private static String getRevision(String[] args) {
        return args.length > 1 ? args[1] : null;
    }
}
