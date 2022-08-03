import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    
    private JTextField backgroundImageTitleField;
    private JTextField backgroundImageDescriptionField;
    private JTextField memeCaptionField;
    
    private JComboBox vertAlignComboBox;
     
    
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);


        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();
        
        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        // TODO The button needs a listener
        backgroundImageButton.addActionListener(new OpenButtonListener());
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);        
 
        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
        
        // Create a panel that provides input for the BackgroundImage title
        JPanel backgroundImageTitlePanel = new JPanel();
        
        // Label
        JLabel backgroundImageTitleLabel = new JLabel("Title: ");
        backgroundImageTitleLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageTitlePanel.add(backgroundImageTitleLabel);
        
        
        // Field that will contain the title of the image
        backgroundImageTitleField = new JTextField();
        backgroundImageTitlePanel.add(backgroundImageTitleField);
        backgroundImageTitleField.setPreferredSize(new Dimension(350, 20));
        
        // Add the panel about the BackgroundImage title to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageTitlePanel);
        
        //Create a panel that provides input for the BackgroundImage description
        JPanel backgroundImageDescriptionPanel = new JPanel();
        
        // Label
        JLabel backgroundImageDescriptionLabel = new JLabel("Description: ");
        backgroundImageDescriptionLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionLabel);
       
        
        // Field that will contain the description of the image
        backgroundImageDescriptionField = new JTextField();
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionField);
        backgroundImageDescriptionField.setPreferredSize(new Dimension(350, 20));
        
        // Add the panel about the BackgroundImage description to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageDescriptionPanel, BorderLayout.SOUTH);
        
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        
        
        // Create a meme panel that holds the Meme creating information
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
        controlPanel.add(memePanel, BorderLayout.CENTER);
        
        // Create a panel that provides input for the BackgroundImage title
        JPanel memeCaptionPanel = new JPanel();
        
        // Label
        JLabel memeCaptionLabel = new JLabel("Caption: ");
        memeCaptionLabel.setPreferredSize(new Dimension(100, 20));
        memeCaptionPanel.add(memeCaptionLabel);
        
        
        // Field that will contain the title of the image
        memeCaptionField = new JTextField();
        memeCaptionPanel.add(memeCaptionField);
        memeCaptionField.setPreferredSize(new Dimension(350, 20));
        
        // Add the panel about the BackgroundImage title to the BackgroundImage information panel
        memePanel.add(memeCaptionPanel, BorderLayout.NORTH);
        
     // Create a panel that provides input for the BackgroundImage title
        JPanel memeVertPanel = new JPanel();
        
        // Label
        JLabel memeVertLabel = new JLabel("Vertical Align: ");
        memeVertLabel.setPreferredSize(new Dimension(100, 20));
        memeVertPanel.add(memeVertLabel);
        
        //ComboBox Implementation
        String[] boxList = {"top", "middle", "bottom"};
        vertAlignComboBox = new JComboBox(boxList);
        vertAlignComboBox.setPreferredSize(new Dimension(350, 20));
        memeVertPanel.add(vertAlignComboBox);
        
        // Add the panel
        memePanel.add(memeVertPanel);
        
        //Creating Generate Button Panel 
        JPanel generateSavePanel = new JPanel();
        controlPanel.add(generateSavePanel, BorderLayout.SOUTH);
        
        //Creating Generate Button
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(100, 20));
        generateButton.addActionListener(new GenerateButtonListener());
        generateSavePanel.add(generateButton);
        
        //Creating Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100,20));
        saveButton.addActionListener(new SaveButtonListener());
        generateSavePanel.add(saveButton);
        
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * ActionListener for the generate button.  When the button is pressed, this ActionListener
     * creates and displays the GraphicalMeme into the imageDisplayLabel
     * Instantiates a GraphicalMeme object and uses compileMeme() method to compile the meme
     */
    
    private class GenerateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			BufferedImage buffImage = null;
    		String title = backgroundImageTitleField.getText();
    		String description = backgroundImageDescriptionField.getText();
    		BackgroundImage bgImage = new BackgroundImage(backgroundImageFilename, title, description);
    		
    		String memeCaption = memeCaptionField.getText();
    		currentMeme = new GraphicalMeme(bgImage, memeCaption, user);
    		currentMeme.setCaptionVerticalAlign(vertAlignComboBox.getSelectedItem().toString());
    		
    		try {
    			buffImage = currentMeme.compileMeme();
    		} catch (IOException iOException) {
    			iOException.printStackTrace();
    		} catch (NullPointerException pointerException) {
    			System.err.println("File was not uploaded correctly");
    			pointerException.printStackTrace();
    		} catch (Exception e) {
    			System.err.println("The uploaded image is unreadable");
    			e.printStackTrace();
    		}
    		
    		imageDisplayLabel.setIcon(new ImageIcon(buffImage));
    		memeViewPanel.repaint();
			
		}
    	
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                if (!destinationFile.contains(".png"))
                    destinationFile += ".png";
                
                // TODO: Writing an image throws a checked exception that must be handled.
                //       Catch the exceptions and provide the user with an appropriate message
                // ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
                
                try {
                	ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
                } catch (NullPointerException nullE) {
                	System.err.println("Destination argument is null");
                	nullE.printStackTrace();
                } catch (IllegalArgumentException argE) {
                	System.err.println("Parameter used is null");
                	argE.printStackTrace();
                } catch (IOException iOE) {
                	System.err.println("Error occurred during writing");
                	iOE.printStackTrace();
                } catch (Exception e) {
                	System.err.println("An error occurred");
                	e.printStackTrace();
                }

            }

        }
    }
}