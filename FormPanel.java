import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    JLabel lb_bienvenue;
    FormPanel(Profil p){
        lb_bienvenue=new JLabel("Bienvenue "+p.nom+" "+p.prenom);
        this.setBackground(Color.MAGENTA);
        lb_bienvenue.setFont(new Font(Font.SANS_SERIF,Font.BOLD,34));
        this.setBackground(Color.LIGHT_GRAY);
        this.add(lb_bienvenue);
        this.add(new JButton("Valider"));
    }
}
