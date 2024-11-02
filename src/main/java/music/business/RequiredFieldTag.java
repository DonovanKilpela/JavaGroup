package music.business;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class RequiredFieldTag extends TagSupport {

    // Attribute for the field value passed from JSP
    private String fieldValue;

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            // Check if the field is empty
            if (fieldValue == null || fieldValue.trim().isEmpty()) {
                out.print(" <span style='color: red;'>*</span>");
            }
        } catch (IOException e) {
            throw new JspException("Error in RequiredFieldTag", e);
        }
        return SKIP_BODY;  // Skip the body of the tag
    }
}
