package net.fiap.postech.fastburger.adapters.persistence.dto.enumerations;

public enum PayMentMethodEnum {
    CARD("CARD"), CASH("CASH"), PIX("PIX");
    private String type;

    public String getType() {
        return this.type;
    }

    PayMentMethodEnum(String type) {
        this.type = type;
    }
}
