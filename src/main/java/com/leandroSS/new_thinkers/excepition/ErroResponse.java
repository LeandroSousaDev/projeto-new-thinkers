package com.leandroSS.new_thinkers.excepition;

import lombok.Data;

@Data
public class ErroResponse {
    private Integer statusCode;
    private String menssage;

    public ErroResponse(Integer statuscode, String menssage) {
        this.menssage = menssage;
        this.statusCode = statuscode;
    }

}
