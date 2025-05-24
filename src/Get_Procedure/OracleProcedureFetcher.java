/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Get_Procedure;

import DAO.ConnectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhuyc
 */
public class OracleProcedureFetcher {
    public static List<String> getProcedures(Connection conn, String schemaName) throws SQLException {
        List<String> procedures = new ArrayList<>();

        String sql = "SELECT OBJECT_NAME FROM ALL_OBJECTS " +
                     "WHERE OBJECT_TYPE = 'PROCEDURE' AND OWNER = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schemaName.toUpperCase());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    procedures.add(rs.getString("OBJECT_NAME"));
                }
            }
        }

        return procedures;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print(getProcedures(ConnectionUtils.getMyConnection(), "GK").toString());
    }
}
