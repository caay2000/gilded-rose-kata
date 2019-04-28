package com.gildedrose.model;

import com.gildedrose.Item;

public class Product {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int EXPIRE_DAY = 0;

    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_ITEM_QUALITY = 50;

    private Item item;

    public Product(Item item) {
        this.item = item;
    }

    public String getName() {
        return this.item.name;
    }

    public int getQuality() {
        return this.item.quality;
    }

    public int getDaysToExpire() {
        return this.item.sellIn;
    }

    public boolean isExpired() {
        return this.item.sellIn < EXPIRE_DAY;
    }

    public void expireOneDay() {
        this.item.sellIn--;
    }

    public void decreaseQuality() {
        if (getQuality() > MIN_ITEM_QUALITY) {
            this.item.quality--;
        }
    }

    public void increaseQuality() {
        if (getQuality() < MAX_ITEM_QUALITY) {
            this.item.quality++;
        }
    }

    public void killQuality() {
        this.item.quality = 0;
    }

    public void processExpireDate() {
        if (getName().equals(SULFURAS)) {
            return;
        }
        expireOneDay();
    }

    public void processQuality() {
        if (getName().equals(SULFURAS)) {
            return;
        }
        if (getName().equals(AGED_BRIE)) {
            increaseQuality();
            if (isExpired()) {
                increaseQuality();
            }
        } else if (getName().equals(BACKSTAGE_PASS)) {
            if (isExpired()) {
                killQuality();
            } else {
                increaseQuality();
                if (getDaysToExpire() < 10) {
                    increaseQuality();
                }
                if (getDaysToExpire() < 5) {
                    increaseQuality();
                }
            }
        } else {
            decreaseQuality();
            if (isExpired()) {
                decreaseQuality();
            }
        }
    }
}