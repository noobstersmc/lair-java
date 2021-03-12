package us.jcedeno.springlearning;

import lombok.AllArgsConstructor;

/**
 * Simple Object to create a HTML5(ugly) website from plain java
 * 
 * @author jcedeno
 */
@AllArgsConstructor(staticName = "create")
public class Page {
    String title;
    String paragraph;

    /**
     * Converts the java object into a full HTML 5 website.
     * 
     * @return HTML 5 website
     */
    public String toHtmlWebsite() {
        return String.format("<!DOCTYPE html><html>%s%s</html>", getHead(), toHtmlElement());
    }

    /**
     * Wraps everything in the object inside a html body object.
     * 
     * @return Html body element.
     */
    String toHtmlElement() {
        return String.format("<body>%s%s</body>", getTitleAsHeading(), getParragraphAsHtml());
    }

    /**
     * This method wraps the String title with a html h1 header.
     * 
     * @return Wrapped header
     */
    String getTitleAsHeading() {
        return String.format("<h1>%s</h1>", title);
    }

    /**
     * This method wraps the String paragraph with a html paragraph.
     * 
     * @return Wrapped string
     */
    String getParragraphAsHtml() {
        return String.format("<h2>%s</h2>", paragraph);
    }

    /**
     * Quick method that returns a head with css style built in
     * 
     * @return head with css.
     */
    String getHead() {
        return String.format("<head><style>%s</style></head>",
                "h1 { color: #111; font-family: 'Helvetica Neue', sans-serif; font-size: 275px; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: center; } h2 { color: #111; font-family: 'Open Sans', sans-serif; font-size: 30px; font-weight: 300; line-height: 32px; margin: 0 0 72px; text-align: center; } p { color: #685206; font-family: 'Helvetica Neue', sans-serif; font-size: 14px; line-height: 24px; margin: 0 0 24px; text-align: justify; text-justify: inter-word; }");
    }
}
