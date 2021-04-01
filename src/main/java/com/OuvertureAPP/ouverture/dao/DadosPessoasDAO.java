package com.OuvertureAPP.ouverture.dao;
import com.OuvertureAPP.ouverture.modelo.Conta;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DadosPessoasDAO {

    private final Connection connection;

    public DadosPessoasDAO(Connection connection) {

        this.connection = connection;

    }


    public void GRAVAR(Conta conta) throws SQLException {

        String sql = "insert into conta(cpf,nome,sobrenome,cidade,cep,local,estado,data_de_nascimento)values(?,?,?,?,?,?,?,?)";

        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, conta.getCpf());
            pst.setString(2, conta.getNome());
            pst.setString(3, conta.getSobrenome());
            pst.setString(4, conta.getCidade());
            pst.setString(5, conta.getCep());
            pst.setString(6, conta.getLocal());
            pst.setString(7, conta.getEstado());
            pst.setString(8, conta.getData_de_nascimento());
            pst.execute();

            try (ResultSet rst = pst.getGeneratedKeys()) {

                while (rst.next()) {
                    System.out.println(conta.getCpf() + "\n" + conta.getNome());
                }

            }
        }
    }



    public List<Conta> listar(){

        try {

            List<Conta> dadoPesssoa = new ArrayList<>();

            try (PreparedStatement pst = connection.prepareStatement
                    ("select id, cpf,nome,sobrenome,cidade,cep,local,estado,data_de_nascimento from Conta")) {
                pst.execute();
                try (ResultSet rst = pst.getResultSet()) {

                    while (rst.next()) {
                        Conta conta =
                                new Conta(rst.getLong(1), rst.getString(2)
                                        , rst.getString(3), rst.getString(4), rst.getString(5)
                                        , rst.getString(6), rst.getString(7), rst.getString(8),
                                        rst.getString(9));

                           dadoPesssoa.add(conta);

                    }
                }
            }
            return dadoPesssoa;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Conta deletarContar(String cpf) throws SQLException {

        try ( PreparedStatement pst = connection.prepareStatement("DELETE FROM Conta where cpf = ?")){
            pst.setString(1, cpf);
            pst.execute();


            }

        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return null;
    }


    public long count() {
        long registro = 0;
        return listar().size();
    }
}



