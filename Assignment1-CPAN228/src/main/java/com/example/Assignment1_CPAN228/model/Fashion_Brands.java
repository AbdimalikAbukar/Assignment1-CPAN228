package com.example.Assignment1_CPAN228.model;

public enum Fashion_Brands {
    BALENCIAGA("Balenciaga"), CHANEL("Chanel"), DIOR("Dior"), GUCCI("Gucci"), LOUIS_VUITTON("Louis Vuitton"),
    PRADA("Prada"), VERSACE("Versace"), YVES_SAINT_LAURENT("Yves Saint Laurent");

    public final String name;

    private Fashion_Brands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
