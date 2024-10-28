import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bureau extends JFrame {
    JMenuBar menuBar;
    JMenu menuTPSwing, menuTPBase;
    JMenuItem menuItem1,  menuItem2;

    JDesktopPane desktop;


    Bureau(){
        this.setTitle("Projet JAVA");
        this.setSize(100,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        //creation
        desktop=new JDesktopPane();
        this.add(desktop);

        menuBar=new JMenuBar();

        menuTPSwing=new JMenu("TP Swing");
        menuItem1= new JMenuItem("TP1");
        menuItem2= new JMenuItem("TP2");
        menuTPSwing.add(menuItem1);
        menuTPSwing.add(menuItem2);

        menuTPBase=new JMenu("TP DB");

        menuBar.add(menuTPSwing);
        menuBar.add(menuTPBase);

        this.setJMenuBar(menuBar);


        //event
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionProfile gp= new GestionProfile();
                gp.setVisible(true);
                gp.setIconifiable(true);
                gp.setMaximizable(true);
                gp.setClosable(true);
                desktop.add(gp);
            }
                /*
                JInternalFrame fr=new JInternalFrame();
                fr.setTitle("Fenetre Internet");
                fr.setSize(400,200);
                fr.setIconifiable(true);
                fr.setMaximizable(true);
                fr.setClosable(true);
                fr.setVisible(true);
                desktop.add(fr);
            }*/
        });
    }

    public static void main(String[] args){
        Bureau b= new Bureau();
        b.setVisible(true);
    }

}
