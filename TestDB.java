import java.sql.*;

public class TestDB {

    public static void main(String[] args) {

        EtudiantDAO dao=new EtudiantDAO();
        String req="Select * from etudiant";
        ResultSet rs=dao.selectEtudiant(req);
        dao.afficheResultSet(rs);
    }
}
