package com.api.webservice.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;


public class RandomUtil {
    private static RandomUtil owner;
    private static char[] chars = new char[]{'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    private static char[] numbers = new char[]{'0','1','2','3','4','5','6','7','8','9'};

    public static RandomUtil getInstance() {
        if (owner == null) {
            owner = new RandomUtil();
        }
        return owner;
    }

    public String getRandomNumeric(int randomNumberLength){
        return RandomStringUtils.randomNumeric(randomNumberLength);
    }

    /**
     * @param randomNumberCount 需要生成的随机数数量
     * @return 返回一个randomNumberCount长度的随机数，包含大写字母，小写字母和数字
     */
    public String getRandomAlphanumeric(int randomNumberCount) {
        return RandomStringUtils.randomAlphanumeric(randomNumberCount);
    }

    /**
     * @param listCount  需要生成的list数组元素的数量
     * @param randomNumberLength 需要生成的随机数长度
     * @return 返回一个包含randomNumberCount长度的随机数，包含大写字母，小写字母和数字的list
     */
    public List<String> getRandomAlphanumericStringList(int listCount, int randomNumberLength) {
        List<String> randomAlphanumericStringList = new ArrayList<String>();
        for (int i = 0; i < listCount; i++) {
            randomAlphanumericStringList.add(getRandomAlphanumeric(randomNumberLength));
        }
        return randomAlphanumericStringList;
    }

    /**
     * @param begin            起始数
     * @param orderNumberCount 一共需要生成多少个有序数
     * @return 返回生成的随机数list
     */

    public List<String> getOrderNumberList(long begin, int orderNumberCount) {
        if (begin < 0) {
            return null;
        }
        if (orderNumberCount <= 0) {
            return null;
        }
        List<String> orderNumberList = new ArrayList<String>();
        for (int i = 0; i < orderNumberCount; i++) {
            orderNumberList.add(String.valueOf(begin++));
        }
        return orderNumberList;
    }


    public String getCapitalOrNumber(int count) {
        return RandomStringUtils.random(count, chars);
    }

    /**
     * @param count    随机数长度
     * @return 返回生成的随机数
     */
    public String getRandomNumber(int count) {
        return RandomStringUtils.random(count, numbers);
    }

}
