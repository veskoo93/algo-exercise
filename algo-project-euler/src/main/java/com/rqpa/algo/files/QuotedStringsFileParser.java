package com.rqpa.algo.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;

public class QuotedStringsFileParser
{
    public static final QuotedStringsFileParser instance = new QuotedStringsFileParser();

    private QuotedStringsFileParser()
    {
        // Singleton
    }

    public void parse(InputStream inputStream, Consumer<String> stringConsumer) throws IOException
    {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(inputStream)))
        {
            String currentString = null;
            while ((currentString = parseNextString(reader)) != null)
            {
                stringConsumer.accept(currentString);
            }
        }
    }

    private static String parseNextString(Reader reader) throws IOException
    {
        StringBuilder sb = new StringBuilder();
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
        while ((currentChar = requireNextChar(reader)) != '"')
        {
            sb.append(currentChar);
        }
        return sb.toString();
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
}
