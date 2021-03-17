package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.slf4j.Logger;

/*
You are given the following information, but you may prefer to do some research for yourself.

1 Jan 1900 was a Monday.
Thirty days has September,
April, June and November.
All the rest have thirty-one,
Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Task19
{
    private static final Logger logger = getLogger(Task19.class);

    private static final DayOfWeek[] daysOfWeekIndexed = {
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    };

    private static final Map<DayOfWeek, Integer> dayOfWeekToIndex;
    static
    {
        Map<DayOfWeek, Integer> dayOfWeekToIndexMap = new EnumMap<>(DayOfWeek.class);
        for (int i = 0; i < daysOfWeekIndexed.length; i++)
        {
            dayOfWeekToIndexMap.put(daysOfWeekIndexed[i], i);
        }
        dayOfWeekToIndex = Collections.unmodifiableMap(dayOfWeekToIndexMap);
    }

    private static final Month[] monthsIndexed = {
            Month.JANUARY,
            Month.FEBRUARY,
            Month.MARCH,
            Month.APRIL,
            Month.MAY,
            Month.JUNE,
            Month.JULY,
            Month.AUGUST,
            Month.SEPTEMBER,
            Month.OCTOBER,
            Month.NOVEMBER,
            Month.DECEMBER
    };


    private static final Map<Month, Integer> monthToIndex;
    static
    {
        Map<Month, Integer> monthToIndexMap = new EnumMap<>(Month.class);
        for (int i = 0; i < monthsIndexed.length; i++)
        {
            monthToIndexMap.put(monthsIndexed[i], i);
        }
        monthToIndex = Collections.unmodifiableMap(monthToIndexMap);
    }

    public static void main(String[] args)
    {
        System.out.println(sundaysOnFirstOfMonthFrom1Jan1901To31Dec2000Incl());
    }

    public static int sundaysOnFirstOfMonthFrom1Jan1901To31Dec2000Incl()
    {
        // Initialize
        int currentDay = 1;
        Month currentMonth = Month.JANUARY;
        int currentYear = 1900;
        DayOfWeek currentDayOfWeek = DayOfWeek.MONDAY;

        int sundaysOnFirstCount = 0;
        do
        {
            if (currentDayOfWeek.equals(DayOfWeek.SUNDAY) && currentYear >= 1901)
            {
                logger.trace("{} {} {} is sunday!", currentDay, currentMonth, currentYear);
                sundaysOnFirstCount += 1;
            }

            currentDayOfWeek = dayOfWeekAfterDays(getDaysInMonth(currentYear, currentMonth), currentDayOfWeek);
            currentMonth = nextMonth(currentMonth);
            if (currentMonth.equals(Month.JANUARY))
            {
                currentYear += 1;
            }
        } while (currentYear <= 2000);

        return sundaysOnFirstCount;
    }

    private static Month nextMonth(Month currentMonth)
    {
        int nextMonthIndex = (monthToIndex.get(currentMonth) + 1) % monthsIndexed.length;
        return monthsIndexed[nextMonthIndex];
    }

    private static int getDaysInMonth(int year, Month month)
    {
        switch (month)
        {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
                return isLeapYear ? 29 : 28;
        }
        throw new RuntimeException("Unknown month:" + month);
    }

    private static DayOfWeek dayOfWeekAfterDays(int days, DayOfWeek currentDay)
    {
        int resultDayIndex = (dayOfWeekToIndex.get(currentDay) + days) % daysOfWeekIndexed.length;
        return daysOfWeekIndexed[resultDayIndex];
    }
}
