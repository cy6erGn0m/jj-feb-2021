package org.level.web;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AddPartForm {
    @NotBlank
    private String partNumber;

    @NotBlank
    @Length(max = 50)
    private String partTitle;

    public AddPartForm(String partNumber, String partTitle) {
        this.partNumber = partNumber;
        this.partTitle = partTitle;
    }

    public AddPartForm() {
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartTitle() {
        return partTitle;
    }

    public void setPartTitle(String partTitle) {
        this.partTitle = partTitle;
    }
}
