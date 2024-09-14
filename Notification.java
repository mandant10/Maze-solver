import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Notification {
    public static void addText(String text, boolean positive, JTextPane eventsTextPane) {
        Color kolor=new Color(0, 128, 0);
        if(!positive) kolor=Color.RED;
        StyledDocument doc = eventsTextPane.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, kolor);
        StyleConstants.setFontSize(attributes, 14);
        try {
            doc.insertString(doc.getLength(), text + '\n', attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
