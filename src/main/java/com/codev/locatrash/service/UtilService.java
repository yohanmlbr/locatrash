package com.codev.locatrash.service;

public class UtilService {

    public static String clearEncodage(String s){
        s=s.replaceAll("\u00e0","à");
        s=s.replaceAll("\u00e2","â");
        s=s.replaceAll("\u00e4","ä");
        s=s.replaceAll("\u00e7","ç");
        s=s.replaceAll("\u00e8","è");
        s=s.replaceAll("\u00e9","é");
        s=s.replaceAll("\u00ea","ê");
        s=s.replaceAll("\u00eb","ë");
        s=s.replaceAll("\u00ee","î");
        s=s.replaceAll("\u00ef","ï");
        s=s.replaceAll("\u00f4","ô");
        s=s.replaceAll("\u00f6","ö");
        s=s.replaceAll("\u00f9","ù");
        s=s.replaceAll("\u00fb","û");
        s=s.replaceAll("\u00fc","ü");
        return s;
    }
}
