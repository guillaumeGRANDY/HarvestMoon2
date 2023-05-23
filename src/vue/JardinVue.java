package vue;

import model.*;
import model.legume.LegumeModel;
import model.legume.Tomate;
import model.legume.TypeLegume;
import model.legume.state.State;
import model.legume.state.StateType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class JardinVue extends JFrame implements Observer {
    private final JardinModel jardinModel;

    private DefaultTableModel tableInventoryModel;

    public JardinVue(JardinModel model) {
        super();
        this.jardinModel = model;
        initialize();
    }

    public void initialize(){
        JoueurModel.createDefault(1000);
        JoueurModel.getInstance().getInventory().addObserver(this);
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
        menuLateral.setBorder(new EmptyBorder(10, 10, 10, 10)); // add padding of 10 pixels on all sides
        this.add(menuLateral, BorderLayout.EAST);


        JButton buttonRecolte=new JButton("Récolter");
        buttonRecolte.setBackground(new java.awt.Color(46, 171, 0));
        buttonRecolte.setForeground(Color.white);
        menuLateral.add(buttonRecolte);

        String[] columnNames = {"Id", "Légume", "Etat", "Planté"};
        tableInventoryModel = new DefaultTableModel(columnNames, 0);

        // Créer le tableau Swing
        JTable table = new JTable(tableInventoryModel);
        table.setDefaultEditor(Object.class, null); // empêcher l'édition des cellules

        // Ajouter le tableau au menu latéral
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS)); // set layout to BoxLayout
        menuLateral.add(scrollPane);

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
                LegumeModel legume = new Tomate();
                JoueurModel.getInstance().buy(legume);
                legume.addObserver(this);
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Inventory inventory) {
            List<LegumeModel> legumes = inventory.getLegumes();

            // Clear the table model
            tableInventoryModel.setRowCount(0);

            // Re-populate the table model
            for (LegumeModel legume : legumes) {
                String name = legume.getType().toString();
                String currentState = legume.getCurrentState().stateType().toString();
                String isPlanted = legume.isPlanted() ? "Oui" : "Non";

                Object[] data = new Object[] { legume.getId() , name, currentState, isPlanted };
                tableInventoryModel.addRow(data);
            }
        }

        if(o instanceof LegumeModel legumeModel) {
            for (Vector vector : tableInventoryModel.getDataVector()) {
                int id = (int) vector.get(0);
                if(id == legumeModel.getId()) {
                    vector.set(2, legumeModel.getCurrentState().stateType().toString());
                    vector.set(3, legumeModel.isPlanted() ? "Oui" : "Non");
                    tableInventoryModel.fireTableDataChanged();
                    break;
                }
            }
        }
    }
}
