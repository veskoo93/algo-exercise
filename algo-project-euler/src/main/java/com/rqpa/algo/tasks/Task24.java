package com.rqpa.algo.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

012   021   102   120   201   210

What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Task24
{
    public static void main(String[] args)
    {
        System.out.println(getMillionthLexicographicPermutationOfDigits0To9());
//        System.out.println(permutationToString(getNthLexicographicPermutation(1, Arrays.asList('0', '1', '2'))));
    }

    public static String getMillionthLexicographicPermutationOfDigits0To9()
    {
        return permutationToString(getNthLexicographicPermutation(
                1_000_000,
                Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')));
    }

    private static List<Character> getNthLexicographicPermutation(int n, List<Character> chars)
    {
        int[] permutationsPerChar = new int[chars.size()];
        permutationsPerChar[permutationsPerChar.length - 1] = 1;
        for (int i = permutationsPerChar.length - 2; i >= 0; i--)
        {
            int nextCharPermutations = permutationsPerChar[i + 1];
            permutationsPerChar[i] = nextCharPermutations * (permutationsPerChar.length - i);
        }

        List<Character> sortedChars = new ArrayList<>(chars);
        sortedChars.sort(Comparator.naturalOrder());
        List<Character> nthPermutation = new ArrayList<>(sortedChars.size());
        int neededPermutationIdx = n - 1;

        for (int i = 0; i < chars.size() - 1; i++)
        {
            int nextCharPerms = permutationsPerChar[i + 1];
            nthPermutation.add(sortedChars.remove(neededPermutationIdx / nextCharPerms));
            neededPermutationIdx =  neededPermutationIdx % nextCharPerms;
        }

        nthPermutation.add(sortedChars.remove(0));
        return nthPermutation;
    }

    private static String permutationToString(List<Character> perm)
    {
        StringBuilder sb = new StringBuilder();
        for (Character character : perm)
        {
            sb.append(character);
        }
        return sb.toString();
    }
    // 0 1 2
    // Perms: 012 021 102 120 201 210
    // Perms per char: 6 2 1
    // 5th perm: 201
    // ind[0] = 2 | ind[1] = 0 | ind[2] = 0
}
