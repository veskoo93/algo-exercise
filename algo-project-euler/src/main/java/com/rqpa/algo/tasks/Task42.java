package com.rqpa.algo.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.rqpa.algo.files.QuotedStringsFileParser;
import com.rqpa.algo.numbers.GeometricNumbersGenerator;

/*
The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
 */
public class Task42
{
    public static void main(String[] args) throws IOException
    {
        System.out.println(findTriangleWordsCount());
    }
    
    public static long findTriangleWordsCount() throws IOException
    {
        TriangleWordsSumCollector sumCollector = new TriangleWordsSumCollector();
        try (InputStream is = Task42.class.getResourceAsStream("/tasks/p042_words.txt"))
        {
            QuotedStringsFileParser.instance.parse(is, sumCollector);
        }

        return sumCollector.sum;
    }


    private static class TriangleWordsSumCollector implements Consumer<String>
    {
        private long sum = 0;
        private final ArrayList<Long> knownTriangleNumbers = new ArrayList<>(100);

        @Override
        public void accept(String s)
        {
            if (isTriangleWord(s))
            {
                sum += 1;
            }
        }

        private boolean isTriangleWord(String word)
        {
            long wordValue = word.chars()
                    .map(c -> c - 'A' + 1)
                    .sum();
            ensureFirstTriangleNumberAfterNAvailable(wordValue);
            return Collections.binarySearch(knownTriangleNumbers, wordValue) >= 0;
        }

        private void ensureFirstTriangleNumberAfterNAvailable(long n)
        {
            int nextTriangleNumberIndex = knownTriangleNumbers.size() + 1;
            long lastTriangleNumber = -1;
            if (!knownTriangleNumbers.isEmpty())
            {
                lastTriangleNumber = knownTriangleNumbers.get(knownTriangleNumbers.size() - 1);
            }

            while (lastTriangleNumber <= n)
            {
                lastTriangleNumber = GeometricNumbersGenerator.getTriangleNumber(nextTriangleNumberIndex++);
                knownTriangleNumbers.add(lastTriangleNumber);
            };
        }
    }
}
