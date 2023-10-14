package br.com.fiap.challenge.domain.enumerations;

public enum CategoryEnum {
    BURGERS("BURGERS"),
    SIDES("SIDES"),
    DRINKS("DRINKS"),
    DESSERTS("DESSERTS");

    private String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
