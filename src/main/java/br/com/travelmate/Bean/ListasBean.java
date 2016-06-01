/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Bean;

import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.repository.FornecedorCidadeRepository;
import br.com.travelmate.repository.FornecedorRepository;
import br.com.travelmate.repository.PaisRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Wolverine
 */
public class ListasBean {
    
    @EJB
    private FornecedorRepository fornecedorRepository;
    @EJB
    private PaisRepository paisRepository;
    @EJB
    private FornecedorCidadeRepository fornecedorCidadeRepository;
    
    public List<Fornecedor> listarFornecedor(){
        List<Fornecedor> lista = fornecedorRepository.list("SELECT f FROM Fronecedor f ORDER BY f.nome");
        if (lista==null){
            lista = new ArrayList<>();
        }
        return lista;
    }
    
    public List<Pais> listarPais(){
        List<Pais> lista = paisRepository.list("SELECT p FROM Pais p ORDER BY p.nome");
        if (lista==null){
            lista = new ArrayList<>();
        }
        return lista;
    }
    
    public List<Fornecedorcidade> listarFornecedorCidade(int idFornecedor, int idPais){
        List<Fornecedorcidade> lista = fornecedorCidadeRepository.list("SELECT f FROM Fornecedorcidade f where f.cidade.pais.idpais=" + 
                idPais + " and f.fornecedor.idfornecedor=" + idFornecedor + " ORDER BY f.cidade.nome");
        if (lista==null){
            lista = new ArrayList<>();
        }
        return lista;
    }
    
    
}
