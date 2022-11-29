package com.example.demo_get.model.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserRespond {
    private Object data;
    private String messenger;


    public UserRespond(String messenger) {
        this.messenger = messenger;
    }

    public UserRespond(Object data, String messenger) {
        this.messenger = messenger;
        this.data = data;

    }

    public UserRespond() {
    }

}
