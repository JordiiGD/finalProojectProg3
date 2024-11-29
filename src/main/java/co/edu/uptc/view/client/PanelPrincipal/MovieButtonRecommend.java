package co.edu.uptc.view.client.PanelPrincipal;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MovieButtonRecommend extends JButton {

    public MovieButtonRecommend(String image, Dimension d) {
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(d.width, d.height,  Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        setIcon(icon);
        setBackground(Color.WHITE);
        Border br = BorderFactory.createLineBorder(Color.white);
        setBorder(br);
        setPreferredSize(d);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
