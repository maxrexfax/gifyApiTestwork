package com.example.gifslistapitestwork;

import java.util.ArrayList;

public class Data {
    public String key;
    public GifElement[] gifElementsArray;

    public Data(String key, GifElement[] gifElementsArray) {
        this.key = key;
        this.gifElementsArray = gifElementsArray;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public GifElement[] getGifElementsArray() {
        return gifElementsArray;
    }

    public void setGifElementsArray(GifElement[] gifElementsArray) {
        this.gifElementsArray = gifElementsArray;
    }
}
