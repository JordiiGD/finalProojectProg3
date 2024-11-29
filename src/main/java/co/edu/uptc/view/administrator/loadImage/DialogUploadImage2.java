package co.edu.uptc.view.administrator.loadImage;

import co.edu.uptc.view.administrator.principalPanel.AddMoviePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class DialogUploadImage2 extends JDialog{

	private File selectArchive;
	private AddMoviePanel panel;

	public DialogUploadImage2(AddMoviePanel panel) {
		this.panel = panel;
		setSize(500, 600);
		setLocationRelativeTo(null);
	}
	
	public void init() {
		JPanel selectImage = new JPanel();
		selectImage.setLayout(new BorderLayout());

        JFileChooser selectorArchive = new JFileChooser();
        selectorArchive.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png"));
        selectorArchive.setCurrentDirectory(new File(""));
        selectImage.add(selectorArchive, BorderLayout.CENTER);

        if (selectorArchive.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectArchive = selectorArchive.getSelectedFile();
            selectArchive.renameTo(new File("src/data/wallpapers/" + selectArchive.getName()));
			panel.setConfirmationLoad();
        }
        add(selectImage);
		setVisible(true);
	}
	
	public String getNameImgMovie() {
		return "src/data/wallpapers/" + selectArchive.getName();
	}

}
