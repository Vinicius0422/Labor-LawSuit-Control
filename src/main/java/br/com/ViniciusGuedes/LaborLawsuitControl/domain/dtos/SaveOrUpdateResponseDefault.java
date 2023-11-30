package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;

public class SaveOrUpdateResponseDefault {

    private HttpStatus statusCode;
    private List<String> message;

    public SaveOrUpdateResponseDefault() {
    }

    public SaveOrUpdateResponseDefault(HttpStatus statusCode, List<String> message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
