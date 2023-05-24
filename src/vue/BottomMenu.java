package vue;

import javax.swing.*;
import java.awt.*;

public class BottomMenu extends JPanel {

    public BottomMenu() {
        this.setBackground(new java.awt.Color(0, 0, 0, 0));
        GridLayout downLayout = new GridLayout(1, 7);
        this.setLayout(downLayout);

        BottomMenuItem buttonCactus=new BottomMenuItem("cactus");
        this.add(buttonCactus);

        BottomMenuItem buttonFleurViolet=new BottomMenuItem("fleurViolet");
        this.add(buttonFleurViolet);

        BottomMenuItem buttonFleurOrange=new BottomMenuItem("fleurOrange");
        this.add(buttonFleurOrange);

        BottomMenuItem buttonFleurBleu=new BottomMenuItem("fleurBleu");
        this.add(buttonFleurBleu);

        BottomMenuItem buttonFleurRouge=new BottomMenuItem("fleurRouge");
        this.add(buttonFleurRouge);

        BottomMenuItem buttonFleurJaune=new BottomMenuItem("fleurJaune");
        this.add(buttonFleurJaune);

        BottomMenuItem buttonChampignonMarron=new BottomMenuItem("champignonMarron");
        this.add(buttonChampignonMarron);

        BottomMenuItem buttonChampignonRouge=new BottomMenuItem("champignonRouge");
        this.add(buttonChampignonRouge);
    }
}
