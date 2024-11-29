package co.edu.uptc.view.administrator.loadImage;

import co.edu.uptc.view.administrator.principalPanel.AddMoviePanel;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogUploadImage extends JDialog{
	
	private File selectArchive;
	private AddMoviePanel panel;
	
	public DialogUploadImage(AddMoviePanel panel) {
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
            selectArchive.renameTo(new File("src/data/posters/" + selectArchive.getName()));
			panel.setConfirmationLoad();
        }
        add(selectImage);
		setVisible(true);
	}
	
	public String getNameImgMovie() {
		return "src/data/posters/" + selectArchive.getName();
	}

}
