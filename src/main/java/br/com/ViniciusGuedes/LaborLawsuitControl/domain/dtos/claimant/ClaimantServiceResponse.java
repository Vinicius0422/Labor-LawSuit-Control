package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

import org.springframework.http.HttpStatus;

public class ClaimantServiceResponse<T> {

    private HttpStatus statusCode;
    private String message;
    private T data;

    public ClaimantServiceResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ClaimantServiceResponse(HttpStatus statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
