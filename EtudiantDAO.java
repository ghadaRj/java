import java.sql.*;

public class EtudiantDAO {

    Connection conn=null;
    Statement st=null;

    EtudiantDAO(){

        //chargement driver
        try {
            Class.forName(Config.nomDriver);
            System.out.println("Driver OK ... ");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver " +e.getMessage());
        }



        try {
            conn = DriverManager.getConnection(Config.url,Config.username,Config.pwd);
            System.out.println("Connected ..");
            st=conn.createStatement();

        } catch (SQLException e){
            System.out.println("Erreur de connection !!! "+e.getMessage());
        }
    }
    int insertEtudiant(int CIN, String nom, String prenom, double moyenne)
    {
        int a=0;
        //execution des requetes
        String req_insertion="insert into etudiant values("+CIN+",'"+nom+"','"+prenom+"',"+moyenne+")";
        if(st != null)
        {
            try {
                a= st.executeUpdate(req_insertion);
                System.out.println("Insertion avec success");
            } catch (SQLException e){
                System.out.println("Erreur insertion "+e.getMessage());
            }
        }
        return a;
    }
    int insertEtudiant2(int CIN, String nom, String prenom, double moyenne)
    {
        int a=0;
        String req="Insert into etudiant(?,?,?,?)";
        try {
            PreparedStatement ps=conn.prepareStatement(req);
            ps.setInt(1,CIN);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setDouble(4,moyenne);

            System.out.println(CIN + nom +prenom +moyenne);

        } catch (SQLException e) {
            System.out.println("Erreur "+e.getMessage());
        }
        return a;
    }
    int delectEtudiant(int CIN){
        String req="Delect from etudiant where CIN=?";
        try {
            PreparedStatement ps=conn.prepareStatement(req);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }


    ResultSet selectEtudiant(String req_selection)
    {
        ResultSet rs=null;
        try {
            rs= st.executeQuery(req_selection);
        } catch (SQLException e) {
            System.out.println("Erreur de selection "+e.getMessage());
        }
        return rs;
    }
    void afficheResultSet(ResultSet rs)
    {
        try {
            //affichage dans le console
            ResultSetMetaData rsmd=rs.getMetaData();
            //affichage les titres des champs
            int nbcol=rsmd.getColumnCount();
            for (int i=0; i<nbcol; i++)
            {
                System.out.println(rsmd.getColumnName(i+1));
            }

            while (rs.next()){
                int CIN=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
                double moyenne=rs.getDouble(4);

                System.out.println(CIN+nom+prenom+moyenne);
            }
        }catch (SQLException e) {
            System.out.println("Erreur de selection "+e.getMessage());
        }
    }
}
