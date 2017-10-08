package com.testing.mvc.controller.vo.binding;

import java.beans.PropertyEditorSupport;

public class CustomNameEditor extends PropertyEditorSupport{

    // Spring MVC will run setAsText before preforming data binding task for
    // studentName property of student Object
    @Override
    public void setAsText(String studentName) throws IllegalArgumentException {
        if (studentName.contains("Mr.") || studentName.contains("Ms.")) {
            setValue(studentName);
        } else {
            // default to Ms.
            studentName = "Ms." + studentName;
            setValue(studentName);
        }
    }
}