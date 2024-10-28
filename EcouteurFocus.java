import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EcouteurFocus extends FocusAdapter {
    GestionProfile gp;

    EcouteurFocus(GestionProfile gp){
        this.gp=gp;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(gp.tf_nom.getText().equalsIgnoreCase("saisir votre nom"))
        {
            gp.tf_nom.setText("");
        }
        if(gp.tf_prenom.getText().equalsIgnoreCase("saisir votre prenom"))
        {
            gp.tf_prenom.setText("");
        }
        if(gp.tf_pseudo.getText().equalsIgnoreCase("saisir votre pseudo"))
        {
            gp.tf_pseudo.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (gp.tf_nom.getText().equalsIgnoreCase(""))
        {
            gp.tf_nom.setText("Saisir votre nom");
        }
        if (gp.tf_prenom.getText().equalsIgnoreCase(""))
        {
            gp.tf_prenom.setText("Saisir votre prenom");
        }
        if (gp.tf_pseudo.getText().equalsIgnoreCase(""))
        {
            gp.tf_pseudo.setText("Saisir votre pseudo");
        }
    }
}
