/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.Bean;

import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.repository.FornecedorCidadeGuiaRepository;
import br.com.travelmate.repository.GuiaEscolaRepository;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Wolverine
 */
public class GuiaEscolaBean {
    
    @EJB
    private GuiaEscolaRepository guiaEscolaRepository;
    @EJB 
    private FornecedorCidadeGuiaRepository fornecedorCidadeGuiaRepository;
    
    public void salvar(List<Fornecedorcidade> lsita){
        
    }
    
}
