package com.harriague.automate.core.structures;

public enum SwipeDirection {
    LEFT, UP, DOWN, RIGHT, DIAGONAL_RIGHT_TOP, DIAGONAL_RIGHT_BOTTOM, DIAGONAL_LEFT_TOP, DIAGONAL_LEFT_BOTTOM;
    public static SwipeDirection get(String direction) {
        final String parsedValue = direction.replaceAll("\\s", "_").toUpperCase();
        SwipeDirection res = null;
        for(SwipeDirection swipeDirection : SwipeDirection.values()) {
            if(swipeDirection.toString().equals(parsedValue)) {
                res = swipeDirection;
                break;
            }
        }
        return res;
    }
}
