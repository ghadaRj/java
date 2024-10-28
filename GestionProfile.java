import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GestionProfile extends JInternalFrame {
    //declaration
    ArrayList<Profil> data=new ArrayList<Profil>();

    DefaultListModel model;

    JLabel lb_nom, lb_prenom, lb_pseudo, lb_help;
    JTextField tf_nom, tf_prenom, tf_pseudo;
    JButton btnvalider;

    JSplitPane jsp;
    JTabbedPane jtp;
    JList jl;



    GestionProfile() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(800,600);
        this.setTitle("Gestion des profiles");

        lb_nom= new JLabel("Nom");
        lb_prenom=new JLabel("Prenom");
        lb_pseudo=new JLabel("Pseudo");
        lb_help=new JLabel("HELP");
        tf_nom=new JTextField(15);
        tf_nom.setText("Saisir votre nom");
        tf_prenom=new JTextField(15);
        tf_prenom.setText("Saisir votre prenom");
        tf_pseudo=new JTextField(15);
        tf_pseudo.setText("Saisir votre pseudo");
        btnvalider=new JButton("Valider");

        jsp=new JSplitPane();
        JPanel pn=new JPanel();

        pn.setLayout(new FlowLayout());
        pn.add(lb_nom);pn.add(tf_nom);
        pn.add(lb_prenom);pn.add(tf_prenom);
        pn.add(lb_pseudo);pn.add(tf_pseudo);
        pn.add(btnvalider);

        this.setLayout(new BorderLayout());
        this.add(pn,BorderLayout.NORTH);
        this.add(lb_help,BorderLayout.SOUTH);


        jsp=new JSplitPane();


        model=new DefaultListModel();
        jl=new JList(model);
        jl.setPreferredSize(new Dimension(150,0));

        jsp.setLeftComponent(jl);

        jtp=new JTabbedPane();
        /*jtp.addTab("TAB 1",new JPanel());
        jtp.addTab("TAB 2", new JPanel());*/

        jsp.setRightComponent(jtp);

        this.add(jsp,BorderLayout.CENTER);
        this.setVisible(true);

        //event
        btnvalider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.add(new Profil(tf_nom.getText(),tf_prenom.getText(),tf_pseudo.getText()));
                model.addElement(tf_pseudo.getText());
            }
        });


        //anonyme class
        btnvalider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ps=tf_pseudo.getText();
                boolean psExits=false;
                for (int i = 0; i <data.size() ; i++) {
                    if(data.get(i).pseudo.equalsIgnoreCase(ps)){
                        psExits=true;
                        break;
                    }
                }
                //si ps exists
                if(psExits){
                    JOptionPane.showMessageDialog(null,"le pseudo "+ps+" exist déjà","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    data.add(new Profil(tf_nom.getText(),tf_prenom.getText(),tf_pseudo.getText()));
                    model.addElement(tf_pseudo.getText());
                }


            }
        });


        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2) //double click
                {
                    //rechercher le profil + le pseudo selectionnée
                    String ps=jl.getSelectedValue()+"";
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).pseudo.equalsIgnoreCase(ps))
                        {
                            FormPanel p=new FormPanel(data.get(i));
                            jtp.addTab(ps,p);
                            break;
                        }
                    }

                }
                if (e.getButton()==MouseEvent.BUTTON3)
                {
                    JPopupMenu popupMenu=new JPopupMenu();
                    JMenuItem itemmod=new JMenuItem("Modifier");
                    JMenuItem itemSup=new JMenuItem("Supprimer");
                    JMenuItem itemSuppts=new JMenuItem("Supprimer tous");
                    popupMenu.add(itemmod);
                    popupMenu.add(itemSup);
                    popupMenu.add(itemSuppts);

                    popupMenu.show(jl,e.getX(),e.getY());


                    //event
                    itemSuppts.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            data.clear();
                            model.clear();
                        }
                    });

                    itemSup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            data.remove(jl.getSelectedIndex());
                            model.remove(jl.getSelectedIndex());
                        }
                    });

                    itemmod.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = jl.getSelectedIndex();
                            if (selectedIndex != -1) {
                                Profil selectedProfil = data.get(selectedIndex);

                                // Ouvrir la fenêtre de modification
                                EcouteurModif dialog = new EcouteurModif(null, selectedProfil);
                                dialog.setVisible(true);

                                // Mettre à jour la liste après la modification
                                Profil updatedProfil = dialog.getUpdatedProfil();
                                model.setElementAt(updatedProfil.pseudo, selectedIndex);
                            }
                        }
                    });
                }
            }
        });


        //sur label
        lb_nom.addMouseListener(new EcouteurLabel(this));

        //sur focus
        tf_nom.addFocusListener(new EcouteurFocus(this));
        tf_prenom.addFocusListener(new EcouteurFocus(this));
        tf_pseudo.addFocusListener(new EcouteurFocus(this));



        lb_nom.addMouseListener(new EcouteurLabel(this));
        lb_prenom.addMouseListener(new EcouteurLabel( this));
    }

}
