package br.com.travelmate.repository;



import br.com.travelmate.model.Fornecedorcidade;
import javax.ejb.Stateless;

@Stateless
public class FornecedorCidadeRepository extends AbstractRepository<Fornecedorcidade> {
    
    public FornecedorCidadeRepository() {
        super(Fornecedorcidade.class);
    }
}
