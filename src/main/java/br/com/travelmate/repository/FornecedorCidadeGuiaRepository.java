package br.com.travelmate.repository;



import br.com.travelmate.model.Fornecedorcidadeguia;
import javax.ejb.Stateless;

@Stateless
public class FornecedorCidadeGuiaRepository extends AbstractRepository<Fornecedorcidadeguia> {
    
    public FornecedorCidadeGuiaRepository() {
        super(Fornecedorcidadeguia.class);
    }
}
