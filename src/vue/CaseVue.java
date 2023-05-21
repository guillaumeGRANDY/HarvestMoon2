package vue;

import model.CaseModel;
import model.JoueurModel;
import model.Ordonnanceur;
import model.legume.LegumeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CaseVue extends JPanel implements Observer, MouseListener {
    private Image image ;
    private JLabel labelImage=new JLabel();

    private CaseModel caseModel;

    public CaseVue() {
        ImageIcon imageIcon = new ImageIcon("./src/img/vide.png");
        image = imageIcon.getImage();
        this.add(labelImage,BorderLayout.CENTER);
        this.addMouseListener(this);
    }

    public CaseVue(CaseModel caseModel) {
        this();
        this.caseModel = caseModel;
    }

    public void changeImage(String stage)
    {
        image= new ImageIcon("./src/img/"+stage+".png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int dimension=Math.min(getHeight(),getHeight());
        ImageIcon icon = new ImageIcon(image.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH));
        labelImage.setIcon(icon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.plant();
    }

    private void plant() {
        List<LegumeModel> legumesInInventory = JoueurModel.getInstance().getInventory().grainsToPlant();
        if(legumesInInventory.size() == 0) {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas de graines dans votre inventaire !");
            return;
        }
        LegumeModel legume = legumesInInventory.get(0);
        legume.plant();
        caseModel.plant(legume);
        legume.addObserver(CaseVue.this);
        Ordonnanceur.getInstance().addRunnable(legume);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof LegumeModel legumeModel) {
            switch (legumeModel.getCurrentState().stateType()) {
                case GRAINE -> changeImage("stage1");
                case BOURGON -> changeImage("stage2");
                case FLEURIE -> changeImage("stage3");
                case MATURE -> changeImage(legumeModel.getType().getName());
            }
        }
    }
}
