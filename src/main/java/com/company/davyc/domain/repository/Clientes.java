package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Deprecated
public class Clientes{

// JDBC //
/*  private final String INSERT = "Insert into CLIENTE (nome) values (?)";
    private final String SELECT_ALL = "Select * from Cliente";
    private final String SEARCH_ID = "SELECT * FROM Cliente WHERE ID = ?";
    private final String SEARCH_NOME = "SELECT * FROM Cliente WHERE NOME LIKE ? ";
    private final String DELETE = "DELETE FROM Cliente WHERE ID = ?";
    private final String UPDATE = "UPDATE Cliente set NOME = ? WHERE ID = ?";

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

        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getid());

    }*/

    private final EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    public Clientes(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }
// JPA //

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public List<Cliente> listaCliente(){
        String jpql = "from Cliente";
        return entityManager.createQuery(jpql, Cliente.class).getResultList();
    }

    @Transactional
    public Cliente updateCliente(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletaCliente(Cliente cliente){
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletaCliente(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        entityManager.remove(cliente);
    }

    @Transactional
    public List<Cliente> buscaCliente(String nome){
        String jpql = "select c from Cliente c where c.NOME like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();

    }

}