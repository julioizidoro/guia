/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;


import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Fornecedorcidadeguia;
import br.com.travelmate.model.Guiaescola;
import br.com.travelmate.model.Pais;
import br.com.travelmate.repository.CidadeRepository;
import br.com.travelmate.repository.FornecedorCidadeGuiaRepository;
import br.com.travelmate.repository.FornecedorCidadeRepository;
import br.com.travelmate.repository.GuiaEscolaRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class FormularioMB implements Serializable{

   
    @EJB
    private GuiaEscolaRepository guiaEscolaRepository;
    @EJB
    private FornecedorCidadeGuiaRepository fornecedorCidadeGuiaRepository;
    @EJB 
    private FornecedorCidadeRepository fornecedorCidadeRepository;
    @EJB 
    private CidadeRepository cidadeRepository;
    private List<Pais> listaPais;
    private List<Cidade> listaCidade;
    private Fornecedor fornecedor;
    private Guiaescola guiaEscola;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	listaPais = (List<Pais>) session.getAttribute("listaPais");
        listaCidade = (List<Cidade>) session.getAttribute("listaCidade");
        fornecedor = (Fornecedor) session.getAttribute("fornecedor");
        guiaEscola = new Guiaescola();
        String nome = (String) session.getAttribute("nome");
        guiaEscola.setNome(nome);
    }

    
    public Guiaescola getGuiaEscola() {
        return guiaEscola;
    }

    public void setGuiaEscola(Guiaescola guiaEscola) {
        this.guiaEscola = guiaEscola;
    }
    
    public String salvar(){
        guiaEscola = guiaEscolaRepository.update(guiaEscola);
        if (listaPais.size()==1){
            salvarCidade(listaPais.get(0));
        }else {
            salvarPais();
        }
        FacesMessage mensagemAtencao = new FacesMessage("Saved successfully.", "");
        FacesContext.getCurrentInstance().addMessage("Confirmation", mensagemAtencao);
        return "index";
    }
    
    public void salvarPais(){
        for(int i=0;i<listaPais.size();i++){
            salvarCidade(listaPais.get(i));
        }
    } 
    
    public void salvarCidade(Pais pais){
        if (listaCidade==null || listaCidade.size()==0){
            String sql = "select c from Cidade c where c.pais.idpais=" + pais.getIdpais() 
                    + " ORDER BY c.pais.nome, c.cidade.nome";
            listaCidade = cidadeRepository.list(sql);
        }
        for(int i=0;i<listaCidade.size();i++){
            salvarGuia(pais.getIdpais(), listaCidade.get(i).getIdcidade());
        }
    }
    
    public void salvarGuia(int idPais, int idCidade){
        Fornecedorcidadeguia fornecedorcidadeguia = new Fornecedorcidadeguia();
        Fornecedorcidade f = fornecedorCidadeRepository.find("SELECT f FROM Fornecedorcidade f where f.fornecedor.idfornecedor=" +
                this.fornecedor.getIdfornecedor() + " and f.cidade.idcidade=" + idCidade);
        if (f!=null){
            fornecedorcidadeguia.setFornecedorcidade(f);
            fornecedorcidadeguia.setGuiaescola(guiaEscola);
            fornecedorCidadeGuiaRepository.update(fornecedorcidadeguia);
        }
    }
}
