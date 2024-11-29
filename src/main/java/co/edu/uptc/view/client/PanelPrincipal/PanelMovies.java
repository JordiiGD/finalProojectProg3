package co.edu.uptc.view.client.PanelPrincipal;

import co.edu.uptc.model.Movie;
import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.client.DashBoard;
import co.edu.uptc.view.customize.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelMovies extends JPanel {

    private DashBoard dashBoard;
    private ArrayList<Movie> movies;
    private JButton movie;
    private JButton left;
    private JButton right;
    private int count;
    private Font textFont;
    private Color colorFont;

    public PanelMovies(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        movies = new ArrayList<>();
        setMovies();
        count = 0;
        initComponents();
    }

    public void initComponents() {
        setBackground(new Color(248, 249, 250));
        colorFont = new Color(33, 37, 41);
        CustomFont customFont = new CustomFont();
        textFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 12);

        createButtonMovie();
        createButtonLeft();
        createButtonRight();

        addComponents();
    }

    public void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(movie, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(right, gbc);
    }

    public void createButtonMovie(){
        movie = new MovieButtonRecommend(movies.get(count).getWallpaper(), new Dimension(1000, 500));
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(count));
                dashBoard.getConnection().getMovie(movies.get(count).getName());
            }
        });
    }

    public void createButtonLeft(){
        left = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(100, 40), "<");
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left();
            }
        });
    }

    public void left(){
        if(count > 0){
            count--;
        }else if(count == 0){
            count = movies.size() - 1;
        }
        movie.setIcon(getIconMovie(movies.get(count).getWallpaper()));
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(count));
                dashBoard.getConnection().getMovie(movies.get(count).getName());
            }
        });
    }

    public void createButtonRight(){
        right = Components.customizeButtonNoBackground(textFont, colorFont,
                new Dimension(100, 40),">");
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right();
            }
        });
    }

    public void right(){
        if(count < movies.size() - 1){
            count++;
        }else if(count == movies.size() - 1){
            count = 0;
        }
        movie.setIcon(getIconMovie(movies.get(count).getWallpaper()));
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(count));
                dashBoard.getConnection().getMovie(movies.get(count).getName());
            }
        });
    }

    public ImageIcon getIconMovie(String image){
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(movie.getWidth(), movie.getHeight(),  Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public void setMovies() {
        for (Movie movie1 : dashBoard.getConnection().getMovies()){
            if (movie1.getWallpaper() != null){
                movies.add(movie1);
            }
        }
    }
}
