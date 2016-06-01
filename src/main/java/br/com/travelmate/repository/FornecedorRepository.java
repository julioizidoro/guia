package br.com.travelmate.repository;



import br.com.travelmate.model.Fornecedor;
import javax.ejb.Stateless;

@Stateless
public class FornecedorRepository extends AbstractRepository<Fornecedor> {
    
    public FornecedorRepository() {
        super(Fornecedor.class);
    }
}
