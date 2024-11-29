package co.edu.uptc.view.client.PanelPrincipal;

import co.edu.uptc.model.Category;
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

public class PanelMoviesForCategory1 extends JPanel {

    private DashBoard dashBoard;
    private Category category;
    private ArrayList<Movie> movies;
    private JLabel title;
    private Font titleFont;
    private Font buttonFont;
    private Color colorFont;
    private int movie1 = 1;
    private int movie2 = 2;
    private int movie3 = 3;
    private int movie4 = 4;
    private JButton movie1Button;
    private JButton movie2Button;
    private JButton movie3Button;
    private JButton movie4Button;

    public PanelMoviesForCategory1(DashBoard dashBoard, Category category) {
        this.dashBoard = dashBoard;
        this.category = category;
        setMovies();
        initComponents();
    }

    public void initComponents(){
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));
        setPreferredSize(new Dimension(dashBoard.getWidth(), 190));
        colorFont = new Color(33, 37, 41);
        CustomFont customFont = new CustomFont();
        titleFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 3, 20);
        buttonFont = customFont.customizeFont(FilePathConstants.FONT_TEXT_PATH, 1, 10);

        title = Components.customizeJLabel(colorFont, new Color(0,0,0),
                new Dimension(150, 20), titleFont, SwingConstants.CENTER);
        title.setText(category.getName());

        if (movies.size() < 5){
            addMovies();
        }else {
            addMoviesWithButtons();
        }
    }

    public void addMoviesWithButtons(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(title, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton left = Components.customizeButtonNoBackground(buttonFont, colorFont,
                new Dimension(50, 40), "<");
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left();
            }
        });
        add(left, gbc);
        movie1Button = new MovieButtonRecommend(movies.get(movie1).getWallpaper(),
                new Dimension(280, 150));
        movie1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie1));
                dashBoard.getConnection().getMovie(movies.get(movie1).getName());
            }
        });
        gbc.gridx = 1;
        add(movie1Button, gbc);
        movie2Button = new MovieButtonRecommend(movies.get(movie2).getWallpaper(),
                new Dimension(280, 150));
        movie2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie2));
                dashBoard.getConnection().getMovie(movies.get(movie2).getName());
            }
        });
        gbc.gridx = 2;
        add(movie2Button, gbc);
        movie3Button = new MovieButtonRecommend(movies.get(movie3).getWallpaper(),
                new Dimension(280, 150));
        movie3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie3));
                dashBoard.getConnection().getMovie(movies.get(movie3).getName());
            }
        });
        gbc.gridx = 3;
        add(movie3Button, gbc);
        movie4Button = new MovieButtonRecommend(movies.get(movie4).getWallpaper(),
                new Dimension(280, 150));
        movie4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie4));
                dashBoard.getConnection().getMovie(movies.get(movie4).getName());
            }
        });
        gbc.gridx = 4;
        add(movie4Button, gbc);
        JButton right = Components.customizeButtonNoBackground(buttonFont, colorFont,
                new Dimension(50, 40), ">");
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right();
            }
        });
        gbc.gridx = 5;
        add(right, gbc);
    }

    public void left(){
        if(movie1 > 0 && movie2 > 0 && movie3 > 0 && movie4 > 0){
            movie1--;
            movie2--;
            movie3--;
            movie4--;
        }else if(movie1 == 0){
            movie1 = movies.size() - 1;
            movie2--;
            movie3--;
            movie4--;
        }else if(movie2 == 0){
            movie1--;
            movie2 = movies.size() - 1;
            movie3--;
            movie4--;
        }else if(movie3 == 0){
            movie1--;
            movie2--;
            movie3 = movies.size() - 1;
            movie4--;
        }else if(movie4 == 0){
            movie1--;
            movie2--;
            movie3--;
            movie4 = movies.size() - 1;
        }
        setMovieButtons();
    }

    public void right(){
        int temp = movies.size() - 1;
        if(movie1 < temp && movie2 < temp
                && movie3 < temp && movie4 < temp){
            movie1++;
            movie2++;
            movie3++;
            movie4++;
        }else if(movie1 == temp){
            movie1 = 0;
            movie2++;
            movie3++;
            movie4++;
        }else if(movie2 == temp){
            movie1++;
            movie2 = 0;
            movie3++;
            movie4++;
        }else if(movie3 == temp){
            movie1++;
            movie2++;
            movie3 = 0;
            movie4++;
        }else if(movie4 == temp){
            movie1++;
            movie2++;
            movie3++;
            movie4 = 0;
        }
        setMovieButtons();
    }

    public void setMovieButtons(){
        movie1Button.setIcon(getIconMovie(movies.get(movie1).getWallpaper()));
        movie2Button.setIcon(getIconMovie(movies.get(movie2).getWallpaper()));
        movie3Button.setIcon(getIconMovie(movies.get(movie3).getWallpaper()));
        movie4Button.setIcon(getIconMovie(movies.get(movie4).getWallpaper()));
        movie1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie1));
                dashBoard.getConnection().getMovie(movies.get(movie1).getName());
            }
        });
        movie2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie2));
                dashBoard.getConnection().getMovie(movies.get(movie2).getName());
            }
        });
        movie3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie3));
                dashBoard.getConnection().getMovie(movies.get(movie3).getName());
            }
        });
        movie4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashBoard.panelMovie(movies.get(movie4));
                dashBoard.getConnection().getMovie(movies.get(movie4).getName());
            }
        });
    }

    public void addMovies(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        add(title, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        int count = 0;
        for (Movie movie : movies) {
            JButton jButton = new MovieButtonRecommend(movie.getWallpaper(),
                    new Dimension(280, 150));
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashBoard.panelMovie(movie);
                    dashBoard.getConnection().getMovie(movie.getName());
                }
            });
            gbc.gridx = count++;
            add(jButton, gbc);
        }
    }

    public ImageIcon getIconMovie(String image){
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(movie1Button.getWidth(), movie1Button.getHeight(),  Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public void setMovies(){
        movies = dashBoard.getConnection().getMoviesForCategory(category.getName());
    }

}
