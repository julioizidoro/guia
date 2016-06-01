package br.com.travelmate.repository;



import br.com.travelmate.model.Guiaescola;
import javax.ejb.Stateless;

@Stateless
public class GuiaEscolaRepository extends AbstractRepository<Guiaescola> {
    
    public GuiaEscolaRepository() {
        super(Guiaescola.class);
    }
}
