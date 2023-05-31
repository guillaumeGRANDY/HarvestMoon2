package org.polytech.vue;

import org.polytech.model.UniteTemps;
import org.polytech.utils.ExtensionImage;
import org.polytech.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedMenu extends JPanel implements ActionListener {

    private JButton btnRapide;
    private JButton btnLent;

    public SpeedMenu() {
        btnRapide = new JButton();
        btnRapide.setIcon(new ImageIcon(Utils.getImageIconFromResources("rapide", ExtensionImage.PNG).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        btnRapide.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnRapide.setBorderPainted(false);
        btnRapide.addActionListener(this);
        this.add(btnRapide);

        btnLent = new JButton();
        btnLent.setIcon(new ImageIcon(Utils.getImageIconFromResources("lent", ExtensionImage.PNG).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        btnLent.setBackground(new java.awt.Color(0, 0, 0, 0.5F));
        btnLent.setBorderPainted(false);
        btnLent.addActionListener(this);
        this.add(btnLent);

        this.setBackground(new java.awt.Color(0, 0, 0, 0));
        this.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnRapide)
        {
            btnRapide.setBackground(new java.awt.Color(0, 0, 0, 0.5F));
            btnLent.setBackground(new java.awt.Color(0, 0, 0, 0));
            UniteTemps.ECHELLE_TEMPS=100;
        }
        else
        {
            btnLent.setBackground(new java.awt.Color(0, 0, 0, 0.5F));
            btnRapide.setBackground(new java.awt.Color(0, 0, 0, 0));
            UniteTemps.ECHELLE_TEMPS=1000;
        }
    }
}
