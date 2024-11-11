package music.business;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequiredFieldTagTest {

    private RequiredFieldTag tag;
    private PageContext pageContext;
    private JspWriter jspWriter;

    @BeforeEach
    public void setUp() {
        tag = new RequiredFieldTag();
        pageContext = Mockito.mock(PageContext.class);
        jspWriter = Mockito.mock(JspWriter.class);
        tag.setPageContext(pageContext);
        when(pageContext.getOut()).thenReturn(jspWriter);
    }

    @AfterEach
    public void tearDown() {
        tag = null;
    }

    @Test
    public void testDoStartTag_FieldValueIsNull() throws JspException, IOException {
        tag.setFieldValue(null);

        int result = tag.doStartTag();

        verify(jspWriter).print(" <span style='color: red;'>*</span>");
        assertEquals(TagSupport.SKIP_BODY, result);
    }

    @Test
    public void testDoStartTag_FieldValueIsEmpty() throws JspException, IOException {
        tag.setFieldValue("");

        int result = tag.doStartTag();

        verify(jspWriter).print(" <span style='color: red;'>*</span>");
        assertEquals(TagSupport.SKIP_BODY, result);
    }

    @Test
    public void testDoStartTag_FieldValueIsNotEmpty() throws JspException, IOException {
        tag.setFieldValue("Non-empty value");

        int result = tag.doStartTag();

        verify(jspWriter, never()).print(anyString());
        assertEquals(TagSupport.SKIP_BODY, result);
    }
}
