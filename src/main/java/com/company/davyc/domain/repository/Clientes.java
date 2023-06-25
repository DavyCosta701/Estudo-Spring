package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private final String INSERT = "Insert into CLIENTE (nome) values (?)";
    private final String SELECT_ALL = "Select * from Cliente";
    private final String SEARCH_ID = "SELECT * FROM Cliente WHERE ID = ?";
    private final String SEARCH_NOME = "SELECT * FROM Cliente WHERE NOME LIKE ? ";
    private final String DELETE = "DELETE FROM Cliente WHERE ID = ?";
    private final String UPDATE = "UPDATE Cliente set NOME = ? WHERE ID = ?";

    private final JdbcTemplate jdbcTemplate;

    public Clientes(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Cliente salvarCliente(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[] {cliente.getNome()});
        return cliente;
    }

    public List<Cliente> listaCliente(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        });
    }

   public List<Cliente> buscaCliente(Integer id){
        List<Cliente> clientes =  jdbcTemplate.query(SEARCH_NOME,
                getClientMapper(),
                id);
       if (clientes.isEmpty()){
           System.out.println("Nenhum Cliente com este ID encontrado");
           return clientes;
       }

       return clientes;
   }


    public List<Cliente> buscaCliente(String nome){
        List<Cliente> clientes =  jdbcTemplate.query(SEARCH_NOME,
                getClientMapper(),
                nome);
        if (clientes.isEmpty()){
            System.out.println("Nenhum Cliente com este nome encontrado");
            return clientes;
        }

        else {
        return clientes;}
    }

    private static RowMapper<Cliente> getClientMapper() {
        return (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nome"));
    }


    public void deletaCliente(Integer id){
        jdbcTemplate.update(DELETE, id);

    }

    public void updateCliente(Cliente cliente){

        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getID());

    }
}