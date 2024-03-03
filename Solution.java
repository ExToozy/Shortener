package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new FileStorageStrategy(), 10);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);

    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();

        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startTime1 = new Date();
        Set<Long> keys = getIds(shortener, strings);
        Date endTime1 = new Date();
        Helper.printMessage(String.valueOf(endTime1.getTime() - startTime1.getTime()));

        Date startTime2 = new Date();
        Set<String> stringSet = getStrings(shortener, keys);
        Date endTime2 = new Date();
        Helper.printMessage(String.valueOf(endTime2.getTime() - startTime2.getTime()));

        if (keys.size() == stringSet.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> idSet = new HashSet<>();
        for (String string : strings) {
            Long id = shortener.getId(string);
            idSet.add(id);
        }
        return idSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long key : keys) {
            String string = shortener.getString(key);
            stringSet.add(string);
        }
        return stringSet;
    }
}
