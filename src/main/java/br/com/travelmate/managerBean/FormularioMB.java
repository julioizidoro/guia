/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;


import br.com.travelmate.Bean.ListasBean;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Guiaescola;
import br.com.travelmate.model.Pais;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class FormularioMB implements Serializable{

   
    @EJB
    private Fornecedorcidade repoFornecedorcidade;
    private List<Fornecedor> listaFornecedor;
    private List<Pais> listaPais;
    private List<Fornecedorcidade> listaFornecedorCidade;
    private Guiaescola guiaEscola;
    private Fornecedor fornecedor;
    private Pais pais;
    
    @PostConstruct
    public void init(){
        ListasBean listasBean = new ListasBean();
        listaFornecedor = listasBean.listarFornecedor();
        listaPais = listasBean.listarPais();
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Guiaescola getGuiaEscola() {
        return guiaEscola;
    }

    public void setGuiaEscola(Guiaescola guiaEscola) {
        this.guiaEscola = guiaEscola;
    }

    public Fornecedorcidade getRepoFornecedorcidade() {
        return repoFornecedorcidade;
    }

    public void setRepoFornecedorcidade(Fornecedorcidade repoFornecedorcidade) {
        this.repoFornecedorcidade = repoFornecedorcidade;
    }

    public List<Fornecedorcidade> getListaFornecedorCidade() {
        return listaFornecedorCidade;
    }

    public void setListaFornecedorCidade(List<Fornecedorcidade> listaFornecedorCidade) {
        this.listaFornecedorCidade = listaFornecedorCidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
    
    public void getListFornecedorCidade(){
        ListasBean listasBean = new ListasBean();
        if ((pais!=null) && (fornecedor!=null)){
            listaFornecedorCidade = listasBean.listarFornecedorCidade(fornecedor.getIdfornecedor(), pais.getIdpais());
        }else {
            listaFornecedorCidade = listasBean.listarFornecedorCidade(0, 0);
        }
    }
    
    
    
}
