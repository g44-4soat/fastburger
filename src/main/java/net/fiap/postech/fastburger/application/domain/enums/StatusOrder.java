package net.fiap.postech.fastburger.application.domain.enums;

public enum StatusOrder {
    RECEIVED("RECEIVED"),
    INPREPARATION("INPREPARATION"),
    READY("READY"),
    FINISHED("FINISHED");
    public String statusItemOrder;

    StatusOrder(String statusItemOrder) {
        this.statusItemOrder = statusItemOrder;
    }

    public  String getStatusItemOrder(){
        return this.statusItemOrder;
    }
}
