package de.aicard.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorModel
{
    private List<String> errorMessages = new ArrayList<>();


    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
