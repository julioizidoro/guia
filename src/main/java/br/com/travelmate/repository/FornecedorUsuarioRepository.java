package br.com.travelmate.repository;



import br.com.travelmate.model.Fornecedorusuario;
import javax.ejb.Stateless;

@Stateless
public class FornecedorUsuarioRepository extends AbstractRepository<Fornecedorusuario> {
    
    public FornecedorUsuarioRepository() {
        super(Fornecedorusuario.class);
    }
}
