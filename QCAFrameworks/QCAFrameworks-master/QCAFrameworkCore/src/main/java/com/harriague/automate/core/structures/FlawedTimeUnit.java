package com.harriague.automate.core.structures;

public class FlawedTimeUnit implements Comparable<FlawedTimeUnit> {
    private long m_miliseconds = 0;
    private final static int SECONDS = 1000;
    private final static int MINUTES = 60000;
    private final static int HOURS = 3600000;

    /**
     * Returns a FlawedTimeUnit set to zero
     */
    public FlawedTimeUnit() {
        m_miliseconds = 0;
    }

    /**
     * Returns a FlawedTimeUnit instance set to the amount of milliseconds from the argument
     * 
     * @param milliseconds Amount of milliseconds that will be set
     */
    private FlawedTimeUnit(long milliseconds) {
        m_miliseconds = milliseconds;
    }

    /**
     * Returns a FlawedTimeUnit instance set to the amount of milliseconds from the argument
     * 
     * @param milliseconds Amount of milliseconds that will be set
     */
    private FlawedTimeUnit(int milliseconds) {
        m_miliseconds = milliseconds;
    }

    /**
     * Gets the amount of the current milliseconds this FlawedTimeUnit has
     * 
     * @return the amount of milliseconds this instance holds
     */
    public long toMiliseconds() {
        return m_miliseconds;
    }

    /**
     * Gets the amount of current seconds this FlawedTimeUnit has
     * 
     * @return the amount of seconds this instance holds
     */
    public long toSeconds() {
        return m_miliseconds / SECONDS;
    }

    /**
     * Gets the amount of current minutes this FlawedTimeUnit has
     * 
     * @return the amount of minutes this instance holds
     */
    public long toMinutes() {
        return m_miliseconds / MINUTES;
    }

    /**
     * Gets the amount of current hours this FlawedTimeUnit has
     * 
     * @return the amount of hours this instance holds
     */
    public long toHours() {
        return m_miliseconds / HOURS;
    }

    /**
     * Compare this FlawedTimeUnit to another
     * 
     * @param compareToThis The FlawedTimeUnit to be compared
     * @return true if the other FlawedTimeUnit has less time than this one
     */
    public boolean isLesserThan(FlawedTimeUnit compareToThis) {
        return m_miliseconds < compareToThis.toMiliseconds();
    }

    /**
     * Compare this FlawedTimeUnit to another
     * 
     * @param compareToThis The FlawedTimeUnit to be compared
     * @return true if the other FlawedTimeUnit has more time than this one
     */
    public boolean isGreaterThan(FlawedTimeUnit compareToThis) {
        return m_miliseconds > compareToThis.toMiliseconds();
    }

    /**
     * Compare this FlawedTimeUnit to another
     * 
     * @param object The FlawedTimeUnit to be compared
     * @return -1 if this instances is lesser, 1 if it is greater and 0 if they are equal.
     */
    @Override
    public int compareTo(FlawedTimeUnit object) {
        FlawedTimeUnit comparedTo = (FlawedTimeUnit) object;
        if (isLesserThan(comparedTo)) {
            return -1;
        } else if (isGreaterThan(comparedTo)) {
            return 1;
        }
        return 0;
    }

    /**
     * waits for the define time from the parameter, that time is then added to this one
     * 
     * @param timeToWait The amount of time to sleep
     */
    public void waitFor(FlawedTimeUnit timeToWait) {
        final long time = timeToWait.toMiliseconds();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
        m_miliseconds += time;
    }

    /**
     * waits for the define time from the parameter, that amount of time is then removed from this
     * one
     * <p>
     * The minimum amount of time is zero, meaning it can not hold negative numbers
     * 
     * @param elapsedTime The amount of time to sleep
     * @return true while this instance has time remaining
     */
    public boolean elapseTime(FlawedTimeUnit elapsedTime) {
        final long time = elapsedTime.toMiliseconds();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
        m_miliseconds -= elapsedTime.toMiliseconds();
        return m_miliseconds > 0;
    }

    /**
     * Sets the current time to the same amount of the parameter
     * 
     * @param newTime the time to be set
     * @return the amount of milliseconds after it was setted
     */
    public long setTime(FlawedTimeUnit newTime) {
        return m_miliseconds = newTime.toMiliseconds();
    }

    /**
     * Adds time to this instance
     * 
     * @param time the time to be truncated
     * @return the amount of milliseconds after it was added
     */
    public long appendTime(FlawedTimeUnit time) {
        m_miliseconds = time.toMiliseconds();
        return m_miliseconds;
    }

    /**
     * Sets the current time to the amount of milliseconds from the parameter
     * 
     * @param milliseconds the amount of milliseconds to be set
     */
    public void setToMiliseconds(long milliseconds) {
        checkArgumentIsValid(milliseconds);
        m_miliseconds = milliseconds;
    }

    /**
     * Sets the current time to the amount of seconds from the parameter
     * 
     * @param seconds the amount of seconds to be set
     */
    public void setToSeconds(int seconds) {
        checkArgumentIsValid(seconds);
        m_miliseconds = seconds * SECONDS;
    }

    /**
     * Sets the current time to the amount of minutes from the parameter
     * 
     * @param seconds the amount of minutes to be set
     */
    public void setToMinutes(int minutes) {
        checkArgumentIsValid(minutes);
        m_miliseconds = minutes * MINUTES;
    }

    /**
     * Sets the current time to the amount of hours from the parameter
     * 
     * @param seconds the amount of hours to be set
     */
    public void setToHours(int hour) {
        checkArgumentIsValid(hour);
        m_miliseconds = hour * HOURS;
    }

    /**
     * Adds time to this instance
     * 
     * @param milliseconds the amount of milliseconds to be added
     * @return the total amount of milliseconds
     */
    public long appendMiliseconds(long milliseconds) {
        checkArgumentIsValid(milliseconds);
        m_miliseconds += milliseconds;
        return m_miliseconds;
    }

    /**
     * Adds time to this instance
     * 
     * @param seconds the amount of seconds to be added
     * @return the total amount of seconds
     */
    public long appendSeconds(int seconds) {
        checkArgumentIsValid(seconds);
        m_miliseconds += seconds * SECONDS;
        return m_miliseconds / SECONDS;
    }

    /**
     * Adds time to this instance
     * 
     * @param minutes the amount of minutes to be added
     * @return the total amount of minutes
     */
    public long appendMinutes(int minutes) {
        checkArgumentIsValid(minutes);
        m_miliseconds += minutes * MINUTES;
        return m_miliseconds / MINUTES;
    }

    /**
     * Adds time to this instance
     * 
     * @param hour the amount of hours to be added
     * @return the total amount of hours
     */
    public long appendHours(int hour) {
        checkArgumentIsValid(hour);
        m_miliseconds += hour * HOURS;
        return HOURS / SECONDS;
    }

    /**
     * Sleep the thread this is on, for a time equal to this instance
     */
    public void waitThisMuch() {
        try {
            Thread.sleep(m_miliseconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Create an instance of FlawedTimeUnit
     * 
     * @param milliseconds the amount of milliseconds that the instance will have
     * @return an instance of FlawedTimeUnit with this many milliseconds
     */
    public static FlawedTimeUnit milliseconds(long milliseconds) {
        checkArgumentIsValid(milliseconds);
        return new FlawedTimeUnit(milliseconds);
    }

    /**
     * Create an instance of FlawedTimeUnit
     * 
     * @param seconds the amount of seconds that the instance will have
     * @return an instance of FlawedTimeUnit with this many seconds
     */
    public static FlawedTimeUnit seconds(int seconds) {
        checkArgumentIsValid(seconds);
        return new FlawedTimeUnit(seconds * SECONDS);
    }

    /**
     * Create an instance of FlawedTimeUnit
     * 
     * @param minutes the amount of minutes that the instance will have
     * @return an instance of FlawedTimeUnit with this many minutes
     */
    public static FlawedTimeUnit minutes(int minutes) {
        checkArgumentIsValid(minutes);
        return new FlawedTimeUnit(minutes * MINUTES);
    }

    /**
     * Create an instance of FlawedTimeUnit
     * 
     * @param hours the amount of hours that the instance will have
     * @return an instance of FlawedTimeUnit with this many hours
     */
    public static FlawedTimeUnit hours(int hours) {
        checkArgumentIsValid(hours);
        return new FlawedTimeUnit(hours * HOURS);
    }

    private static void checkArgumentIsValid(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(
                    number + " is not acceptable, only positives numbers are allowed");
        }
    }

    private static void checkArgumentIsValid(long number) {
        if (number < 0) {
            throw new IllegalArgumentException(
                    number + " is not acceptable, only positives numbers are allowed");
        }
    }
}
