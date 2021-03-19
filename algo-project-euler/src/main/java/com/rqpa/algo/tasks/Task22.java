package com.rqpa.algo.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
Using the resource /tasks/p022_names.txt, a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.

What is the total of all the name scores in the file?
 */
public class Task22
{
    public static void main(String[] args) throws IOException
    {
        System.out.println(calculateScoresSum());
    }

    public static long calculateScoresSum() throws IOException
    {
        List<String> sortedNames = readAndSortNames();
        long sum = 0;
        int idx = 0;

        for (String sortedName : sortedNames)
        {
            sum += calculateScore(idx++, sortedName);
        }
        return sum;
    }

    private static List<String> readAndSortNames() throws IOException
    {
        ArrayList<String> sortedNames = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(Task22.class.getResourceAsStream("/tasks/p022_names.txt"))))
        {
            String currentName = null;
            while ((currentName = parseNextName(reader)) != null)
            {
                sortedNames.add(currentName);
            }
        }

        sortedNames.sort(Comparator.naturalOrder());
        return sortedNames;
    }

    private static String parseNextName(Reader reader) throws IOException
    {
        StringBuilder nameSb = new StringBuilder();
        int firstCharacter = reader.read();
        if (firstCharacter == -1)
        {
            // EOF reached.
            return null;
        }
        char currentChar = (char) firstCharacter;
        if (currentChar == ',')
        {
            // Skip the comma
            currentChar = requireNextChar(reader);
        }
        if (currentChar != '"')
        {
            throw new IOException("Unexpected token: " + currentChar);
        }
        while((currentChar = requireNextChar(reader)) != '"')
        {
            nameSb.append(currentChar);
        }
        return nameSb.toString();
    }

    private static char requireNextChar(Reader reader) throws IOException
    {
        int read = reader.read();
        if (read == -1)
        {
            throw new IOException("Unexpected EOF!");
        }
        return (char) read;
    }

    private static long calculateScore(int index, String name)
    {
        return (index + 1) * calculateWorth(name);
    }

    private static long calculateWorth(String name)
    {
        long worth = name.toUpperCase().chars()
                .map(c -> 1 + c - 'A')
                .sum();
        return worth;
    }
}
