package net.fiap.postech.fastburger.modules.domain.enums;

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

