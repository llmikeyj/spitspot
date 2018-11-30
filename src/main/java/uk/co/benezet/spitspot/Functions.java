package uk.co.benezet.spitspot;

import com.google.common.collect.ImmutableList;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functions {

    public static Function<String, List<String>> entries = filename -> {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(filename))).lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    };

    public static Function<List<String>, List<String>> sorted = unsorted -> unsorted.stream().sorted().collect(Collectors.toList());

    public static List<String> subset(String from, String to, List<String> entries) {
        return entries.subList(entries.indexOf(from), entries.indexOf(to) + 1);
    }

    public static Function<Tuple, Tuple> filter = t -> {

        if (t.filtered.isEmpty()) {
            return new Tuple(ImmutableList.of(t.theRest.get(0)),  t.theRest.subList(1, t.theRest.size()));
        }

        int differences = characterDifferencesOf(lastOf(t.filtered), firstOf(t.theRest));

        if (differences == 0) {
            return new Tuple(t.filtered, t.theRest.subList(1, t.theRest.size()));
        }

        if (differences == 1) {
            return new Tuple(
                    ImmutableList.<String>builder().addAll(t.filtered).add(t.theRest.get(0)).build(),
                    t.theRest.subList(1, t.theRest.size()));
        }

        return new Tuple(t.filtered, t.theRest.subList(1, t.theRest.size()));
    };

    public static String firstOf(List<String> things) {
        return things.get(0);
    }

    public static String lastOf(List<String> things) {
        return things.get(things.size() - 1);
    }

    public static int characterDifferencesOf(String a, String b) {
        int differences = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                differences++;
            }
        }
        return differences;
    }

    public static Function<List<String>, List<String>> filtered = unfiltered ->  {
        List<String> filtered = ImmutableList.of();
        List<String> theRest = ImmutableList.copyOf(unfiltered);

        Tuple t = new Tuple(filtered, theRest);
        while (!t.theRest.isEmpty()) {
            t = filter.apply(t);
        }

        return t.filtered;
    };

    public static void verify(String from, String to, List<String> entries) {
        verifyEntries(entries);
        verifyFrom(from, entries);
        verifyTo(to, entries);
    }

    private static void verifyTo(String to, List<String> entries) {
        if (!entries.contains(to)) { throw new RuntimeException("Dictionary does not contain end word."); }
    }

    private static void verifyFrom(String from, List<String> entries) {
        if (!entries.contains(from)) { throw new RuntimeException("Dictionary does not contain start word."); }
    }

    private static void verifyEntries(List<String> entries) {
        if (entries.isEmpty()) throw new RuntimeException("No dictionary entries.");

        int length = entries.get(0).length();
        entries.forEach(s -> {
            if (s.length() != length) throw new RuntimeException("Dictionary entries not all the same length.");
        });
    }

    public static List<String> squash(String startWord, String endWord, List<String> entries) {
        return Functions.filtered.apply(Functions.subset(startWord, endWord, Functions.sorted.apply(entries)));
    }
}
