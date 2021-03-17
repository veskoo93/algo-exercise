package com.rqpa.algo.text;

public class IntegerToWordsTransformer
{
    public static final IntegerToWordsTransformer instance = new IntegerToWordsTransformer();

    private IntegerToWordsTransformer()
    {
        // Singleton
    }

    public String toWords(int n)
    {
        if ( n < 0 || n > 1000)
        {
            throw new IllegalArgumentException("Number must be in the range [0, 1000]");
        }
        if (n == 1000)
        {
            return "one thousand";
        }

        int hundreds = n / 100;
        int tens = (n % 100) / 10;
        int ones = n % 10;

        if (n < 10)
        {
            return digitAsWord(n);
        }

        StringBuilder sb = new StringBuilder();
        if (hundreds > 0)
        {
            sb.append(digitAsWord(hundreds)).append(" hundred");
        }

        if (tens == 0 && ones == 0)
        {
            return sb.toString();
        }

        if (hundreds > 0)
        {
            sb.append(" and ");
        }
        appendLastTwoDigitsAsWord(tens, ones, sb);
        return sb.toString();
    }

    private void appendLastTwoDigitsAsWord( int tens, int ones, StringBuilder sb)
    {
        switch (tens)
        {
            case 1:
            {
                switch (ones)
                {
                    case 0:
                        sb.append("ten");
                        break;
                    case 1:
                        sb.append("eleven");
                        break;
                    case 2:
                        sb.append("twelve");
                        break;
                    case 3:
                        sb.append("thirteen");
                        break;
                    case 4:
                        sb.append("fourteen");
                        break;
                    case 5:
                        sb.append("fifteen");
                        break;
                    case 6:
                        sb.append("sixteen");
                        break;
                    case 7:
                        sb.append("seventeen");
                        break;
                    case 8:
                        sb.append("eighteen");
                        break;
                    case 9:
                        sb.append("nineteen");
                        break;
                    default:
                        throw new RuntimeException("Unknown ones digit: " + ones);
                }
            }
            break;
            case 2:
                sb.append("twenty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 3:
                sb.append("thirty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 4:
                sb.append("forty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 5:
                sb.append("fifty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 6:
                sb.append("sixty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 7:
                sb.append("seventy");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 8:
                sb.append("eighty");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 9:
                sb.append("ninety");
                if (ones != 0)
                {
                    sb.append("-").append(digitAsWord(ones));
                }
                break;
            case 0:
                sb.append(digitAsWord(ones));
                break;
            default:
                throw new RuntimeException("Unknown tens digit: " + tens);
        }
    }

    private String digitAsWord(int digit)
    {
        switch (digit) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 0:
                return "zero";
            default:
                throw new RuntimeException("Unknown digit: " + digit);
        }
    }
}
