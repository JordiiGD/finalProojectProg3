package co.edu.uptc.view.client.Movie;

import co.edu.uptc.model.Comment;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;

public class CommentLabel extends JPanel {

    private JLabel user;
    private JLabel date;
    private JLabel comment;
    private Font fontTxt;
    private Font fontComment;
    private Comment commentTxt;
    private Color colorFont;

    public CommentLabel(Comment commentTxt) {
        this.commentTxt = commentTxt;
        initComponents();
    }

    public void initComponents(){
        CustomFont customFont = new CustomFont();
        fontTxt = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);
        fontComment = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 14);
        colorFont = new Color(33, 37, 41);

        createLabels();
        addComponents();

    }

    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        add(user, c);

        c.gridx = 1;
        add(date, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        add(comment, c);
    }

    public void createLabels(){
        Color color = new Color(0,0,0);
        user = Components.customizeJLabel(colorFont, color,
                new Dimension(200, 10), fontTxt, SwingConstants.CENTER);
        user.setText(commentTxt.getUser().getName());

        date = Components.customizeJLabel(colorFont, color,
                new Dimension(200, 10), fontTxt, SwingConstants.CENTER);
        date.setText(commentTxt.getCreationDate().toString());

        comment = Components.customizeJLabel(colorFont, color,
                new Dimension(870, 10), fontComment, SwingConstants.CENTER);
        comment.setText(commentTxt.getComment());
    }


}
