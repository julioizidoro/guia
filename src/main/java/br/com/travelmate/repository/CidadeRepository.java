package br.com.travelmate.repository;



import br.com.travelmate.model.Cidade;
import javax.ejb.Stateless;

@Stateless
public class CidadeRepository extends AbstractRepository<Cidade> {
    
    public CidadeRepository() {
        super(Cidade.class);
    }
}
