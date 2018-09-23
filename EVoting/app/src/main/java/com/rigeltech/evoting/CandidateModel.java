package com.rigeltech.evoting;

/**
 * Created by Mahesh on 2018-09-23.
 */

public class CandidateModel {
    String Srno;
    String name;

    public String getSrno() {
        return Srno;
    }

    public void setSrno(String srno) {
        Srno = srno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    String symbol;
}
