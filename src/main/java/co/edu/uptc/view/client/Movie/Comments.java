package co.edu.uptc.view.client.Movie;

import co.edu.uptc.model.Comment;
import co.edu.uptc.model.Movie;
import co.edu.uptc.structures.Queue;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Comments extends JPanel {

    private DashBoard dashBoard;
    private JTextArea textArea;
    private JButton button;
    private ArrayList<Comment> comments;
    private Movie movie;
    private Font textFont;
    private Color colorFont;
    private Color colorButton;
    private UIButton uiButton;
    private JPanel commentsPanel;
    private JScrollPane scrollPane;

    public Comments(DashBoard dashBoard, Movie movie){
        this.dashBoard = dashBoard;
        this.movie = movie;
        setComments();
        initComponents();
    }

    public void initComponents(){
        setLayout(new GridBagLayout());
        CustomFont customFont = new CustomFont();
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);
        colorFont = new Color(33, 37, 41);
        colorButton = new Color(0, 123, 255);
        uiButton = new UIButton(colorButton);
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(700,30));
        commentsPanel = new JPanel();
        commentsPanel.setPreferredSize(new Dimension(870, 200));
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));

        addComment();
        addComponents();

    }

    public void addComponents(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        add(textArea, gbc);

        gbc.gridx = 1;
        add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        for (Comment comment : comments){
            CommentLabel label = new CommentLabel(comment);
            commentsPanel.add(label);
            gbc.gridy++;
        }
        scrollPane = new JScrollPane(commentsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(33, 37, 41));
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, gbc);
    }

    public void addComment(){
        button = Components.customizeButtonWithTextOnly(textFont, colorFont,
                new Color(0,0,0), new Dimension(120, 30), "Comentar");
        button.setUI(uiButton);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.moviePlatform.addComment(textArea.getText(),
                        movie, dashBoard.getUser());
                dashBoard.panelMovie(movie);
            }
        });
    }

    public void setComments(){
        comments = (ArrayList<Comment>) movie.getComments().inOrder();
    }
}
