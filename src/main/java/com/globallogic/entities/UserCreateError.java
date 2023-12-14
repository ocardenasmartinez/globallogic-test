package com.globallogic.entities;

import java.util.List;

public class UserCreateError {
    private List<ErrorDetails> errorDetailsList;

    public List<ErrorDetails> getErrorDetailsList() {
        return errorDetailsList;
    }
    public void setErrorDetailsList(List<ErrorDetails> errorDetailsList) {
        this.errorDetailsList = errorDetailsList;
    }
}
