package vue;

import model.CaseModel;
import model.JardinModel;
import model.JoueurModel;
import model.Ordonnanceur;
import model.legume.Tomate;

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
        JoueurModel.createDefault(1000);
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
        JLabel lblArgent=new JLabel("Argent: " + JoueurModel.getInstance().getSolde() + "€");
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
                CaseVue uneCase = new CaseVue(jardinModel.getCase(i, j));
                uneCase.setBackground(Color.white);
                zoneJardin.add(uneCase); // add à la grid
            }
        }

        this.add(zoneJardin,windowLayout.CENTER);

        //créer le menu lateral
        JPanel menuLateral=new JPanel();
        menuLateral.setBackground(Color.WHITE);
        this.add(menuLateral,windowLayout.EAST);


        JButton buttonRecolte=new JButton("Récolter");
        buttonRecolte.setBackground(new java.awt.Color(46, 171, 0));
        buttonRecolte.setForeground(Color.white);
        menuLateral.add(buttonRecolte);

        //créer le menu inférieur
        JPanel menuDown=new JPanel();
        menuLateral.setBackground(Color.WHITE);
        GridLayout downLayout=new GridLayout(1,7);
        menuDown.setLayout(downLayout);
        this.add(menuDown,windowLayout.SOUTH);

        JButton buttonChou=new JButton(new ImageIcon(new ImageIcon("./src/img/chou.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonChou.setContentAreaFilled(false);
        menuDown.add(buttonChou);

        JButton buttonAubergine=new JButton(new ImageIcon(new ImageIcon("./src/img/aubergine.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonAubergine.setContentAreaFilled(false);
        menuDown.add(buttonAubergine);

        JButton buttonCitrouille=new JButton(new ImageIcon(new ImageIcon("./src/img/citrouille.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonCitrouille.setContentAreaFilled(false);
        menuDown.add(buttonCitrouille);

        JButton buttonAil=new JButton(new ImageIcon(new ImageIcon("./src/img/ail.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonAil.setContentAreaFilled(false);
        menuDown.add(buttonAil);

        JButton buttonCarotte=new JButton(new ImageIcon(new ImageIcon("./src/img/carotte.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonCarotte.setContentAreaFilled(false);
        menuDown.add(buttonCarotte);

        JButton buttonOignon=new JButton(new ImageIcon(new ImageIcon("./src/img/oignon.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonOignon.setContentAreaFilled(false);
        menuDown.add(buttonOignon);

        JButton buttonTomate=new JButton(new ImageIcon(new ImageIcon("./src/img/tomate.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        buttonTomate.setContentAreaFilled(false);
        buttonTomate.addActionListener(e -> {
            try {
                JoueurModel.getInstance().buy(new Tomate());
                System.out.println(JoueurModel.getInstance().getInventory());
                lblArgent.setText("Argent: " + JoueurModel.getInstance().getSolde() + "€");
            } catch (Exception ex) {
                // show error message in a dialog box swing
                JOptionPane.showMessageDialog(buttonTomate, ex.getMessage());
                ex.printStackTrace();
            }
        });
        menuDown.add(buttonTomate);

        setVisible(true);
        Ordonnanceur.getInstance().start();
    }
}
