package com.mocoplex.jarsample;

public enum TestListItem {

    HEADER("AD", true),
    BANNER_DYNAMIC("Banner Dynamic"),
    INTERSTITIAL_DYNAMIC("Interstitial Dynamic");

    private String value;
    private boolean isHeader = false;

    TestListItem(String value) {
        this.value = value;
    }

    TestListItem(String value, boolean isHeader) {
        this.value = value;
        this.isHeader = isHeader;
    }

    public String getValue() {
        return value;
    }

    public boolean isHeader() {
        return isHeader;
    }
}
