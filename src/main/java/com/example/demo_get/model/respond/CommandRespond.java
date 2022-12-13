package com.example.demo_get.model.respond;

import lombok.Data;

@Data

public class CommandRespond {
    private Object data;
    private String messenger;


    public CommandRespond(String messenger) {
        this.messenger = messenger;
    }

    public CommandRespond(Object data, String messenger) {
        this.messenger = messenger;
        this.data = data;

    }

    public CommandRespond() {
    }

}
