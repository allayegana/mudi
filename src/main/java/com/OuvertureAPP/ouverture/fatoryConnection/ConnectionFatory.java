package com.OuvertureAPP.ouverture.fatoryConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConnectionFatory {


    public DataSource dataSources;

    public ConnectionFatory(){

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        comboPooledDataSource.setUser("System");
        comboPooledDataSource.setPassword("Futuro2021");

        comboPooledDataSource.setMaxPoolSize(20);

        this.dataSources = comboPooledDataSource;


    }


    @Bean
    public Connection recuperarConexecao()  {

         try {
             return this.dataSources.getConnection();
         }catch (SQLException e){
             throw new RuntimeException(e);
         }

    }


}
