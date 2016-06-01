package br.com.travelmate.repository;



import br.com.travelmate.model.Pais;
import javax.ejb.Stateless;

@Stateless
public class PaisRepository extends AbstractRepository<Pais> {
    
    public PaisRepository() {
        super(Pais.class);
    }
}
