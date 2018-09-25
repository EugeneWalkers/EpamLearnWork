package com.example.yauheni_skarakhodau.epamlearnwork;

import java.util.ArrayList;
import java.util.List;

public final class DescriptionCreator {

    private static final StringBuilder descriptionBuilder;

    static {
        descriptionBuilder = new StringBuilder();
    }

    private DescriptionCreator() {

    }

    public static String getNumericDescriptionFrom0ToI(final int i) {
        descriptionBuilder.setLength(0);
        for (int j = 0; j < i; j++) {
            descriptionBuilder.append(j).append(", ");
        }
        descriptionBuilder.append(i);
        return descriptionBuilder.toString();
    }

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static List<String> getItemsList() {
        final List<String> items = new ArrayList<>();

        items.add("Zero");
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");
        items.add("Six");
        items.add("Seven");
        items.add("Eight");
        items.add("Nine");
        items.add("Ten");
        items.add("Eleven");
        items.add("Twelve");
        items.add("Thirteen");
        items.add("Fourteen");
        items.add("Fifteen");
        items.add("Sixteen");
        items.add("Seventeen");
        items.add("Eighteen");
        items.add("Nineteen");
        items.add("Twenty");
        items.add("Twenty one");
        items.add("Twenty two");
        items.add("Twenty three");
        items.add("Twenty four");
        items.add("Twenty five");
        items.add("Twenty six");
        items.add("Twenty seven");
        items.add("Twenty eight");
//        items.add("Twenty nine");

        return items;
    }

}
