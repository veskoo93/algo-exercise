package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.stream.IntStream;

import org.slf4j.Logger;

import com.rqpa.algo.text.IntegerToWordsTransformer;

public class Task17
{
    private static final Logger logger = getLogger(Task17.class);

    public static void main(String[] args)
    {
        System.out.println(findSumOfLettersInNumbersFrom1To1000Incl());
    }

    public static long findSumOfLettersInNumbersFrom1To1000Incl()
    {
        long lettersCount = IntStream.rangeClosed(1, 1000)
                .mapToObj(IntegerToWordsTransformer.instance::toWords)
                .peek(logger::trace)
                .mapToLong(Task17::findLettersCount)
                .sum();
        return lettersCount;
    }

    private static long findLettersCount(String s)
    {
        return s.chars()
                .filter(Character::isLetter)
                .count();
    }
}
