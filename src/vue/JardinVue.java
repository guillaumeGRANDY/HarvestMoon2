package vue;

import model.CaseModel;
import model.JardinModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JardinVue extends JFrame {
    private final JardinModel jardinModel;

    public JardinVue(JardinModel model) {
        super();
        this.jardinModel = model;
        initialize();
    }

    public void initialize(){
        this.setSize(500, 400);
        BorderLayout windowLayout=new BorderLayout(10, 10);
        this.setLayout(windowLayout);
        this.setTitle("HarvestMoon2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //paramètre de la fenêtre
        this.getContentPane().setBackground(Color.GRAY);

        //créer le bandeau supérieur
        JPanel bandeauSup=new JPanel();
        bandeauSup.setBackground(Color.WHITE);
        bandeauSup.setLayout(new BorderLayout());
        this.add(bandeauSup,windowLayout.NORTH);

        //Label de la date
        JLabel lblDate=new JLabel("Date Inconnu");
        lblDate.setFont(new Font("Arial", Font.PLAIN, 30));
        bandeauSup.add(lblDate,BorderLayout.WEST);

        //Label pour l'argent
        JLabel lblArgent=new JLabel("Argent: Inconnu");
        lblArgent.setFont(new Font("Arial", Font.PLAIN, 24));
        bandeauSup.add(lblArgent,BorderLayout.EAST);

        //créer le jardin
        JPanel zoneJardin=new JPanel();
        zoneJardin.setBackground(Color.lightGray);
        GridLayout jardinLayout=new GridLayout(4,6);
        jardinLayout.setHgap(10);
        jardinLayout.setVgap(10);
        zoneJardin.setLayout(jardinLayout);

        for (int i = 0; i < 4; i++) {
            for(int j=0;j<6;j++) {
                CaseVue uneCase = new CaseVue();
                uneCase.setBackground(Color.white);
                zoneJardin.add(uneCase); // add à la grid
            }
        }

        this.add(zoneJardin,windowLayout.CENTER);

        //créer le menu lateral
        JPanel menuLateral=new JPanel();
        menuLateral.setBackground(Color.WHITE);
        GridLayout menuLayout=new GridLayout(6,1);
        menuLateral.setLayout(menuLayout);
        this.add(menuLateral,windowLayout.EAST);


        JButton buttonRecolte=new JButton("Récolter");
        menuLateral.add(buttonRecolte);

        JButton buttonChou=new JButton(new ImageIcon(new ImageIcon("./src/img/chou.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        menuLateral.add(buttonChou);

        JButton buttonAubergine=new JButton(new ImageIcon(new ImageIcon("./src/img/aubergine.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        menuLateral.add(buttonAubergine);

        JButton buttonCitrouille=new JButton(new ImageIcon(new ImageIcon("./src/img/citrouille.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        menuLateral.add(buttonCitrouille);

        setVisible(true);
    }
}
