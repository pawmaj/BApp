package com.luxoft.cjp.misc;
import java.util.HashMap;

public class TestLoadFactor {

    private static abstract class IntWrapper {
        public final int i;

        public IntWrapper(int i) {
            this.i = i;
        }
    }

    private static class OnlyTwoDifferentHashCode extends IntWrapper {
        public OnlyTwoDifferentHashCode(int i) {
            super(i);
        }

        @Override
        public int hashCode() {
            return i % 2;
        }

    }

    public static void main(String[] args) {
        HashMap<IntWrapper, Integer> map = new HashMap<IntWrapper, Integer>();

        for (int i = 0; i < 20; i++) {
            map.put(new OnlyTwoDifferentHashCode(i), i);
        }

        System.out.println(map);


    }

}

