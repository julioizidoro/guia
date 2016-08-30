/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedor;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.repository.CidadeRepository;
import br.com.travelmate.repository.FornecedorCidadeRepository;
import br.com.travelmate.repository.FornecedorRepository;
import br.com.travelmate.repository.PaisRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class InicalMB implements Serializable {

    @EJB
    private PaisRepository paisRepository;
    @EJB
    private FornecedorCidadeRepository fornecedorCidadeRepository;
    @EJB
    private CidadeRepository cidadeRepository;
    @EJB
    private FornecedorRepository fornecedorRepository;
    private Pais pais;
    private List<Pais> listaPais;
    private List<Pais> listaPaisSelecionados;
    private Cidade cidade;
    private List<Cidade> listaCidade;
    private List<Cidade> listaCidadeSelecionadas;
    private List<Fornecedor> listaFornecedor;
    private Fornecedor fornecedor = new Fornecedor();

    @PostConstruct
    public void init() {
        pais = new Pais();
        cidade = new Cidade();
        listaCidade = new ArrayList<Cidade>();
        listaCidadeSelecionadas = new ArrayList<Cidade>();
        listaPais = new ArrayList<Pais>();
        listaPaisSelecionados = new ArrayList<Pais>();
        gerarListaFornecedor();
        fornecedor = new Fornecedor();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Pais> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<Pais> getListaPaisSelecionados() {
        return listaPaisSelecionados;
    }

    public void setListaPaisSelecionados(List<Pais> listaPaisSelecionados) {
        this.listaPaisSelecionados = listaPaisSelecionados;
    }

    public List<Cidade> getListaCidadeSelecionadas() {
        return listaCidadeSelecionadas;
    }

    public void setListaCidadeSelecionadas(List<Cidade> listaCidadeSelecionadas) {
        this.listaCidadeSelecionadas = listaCidadeSelecionadas;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public void gerarListaFornecedor(){
        listaFornecedor = fornecedorRepository.list("SELECT f FROM Fornecedor f where f.idfornecedor>1000 order by f.nome");
        if (listaFornecedor==null){
            listaFornecedor = new ArrayList<Fornecedor>();
        }
    }

    public void gerarListaPais() {
        List<Fornecedorcidade> listaFornecedorCidade;
        if (fornecedor != null) {
            String sql = "SELECT f FROM Fornecedorcidade f where ";
            sql = sql + " f.fornecedor.idfornecedor=" + fornecedor.getIdfornecedor();
            sql = sql + " GROUP BY f.cidade.pais.idpais ORDER BY f.cidade.pais.nome";
             listaFornecedorCidade= fornecedorCidadeRepository.list(sql);
            if (listaFornecedorCidade!=null){
                listaPais = new ArrayList<Pais>();
                for(int i=0;i<listaFornecedorCidade.size();i++){
                    listaPais.add(listaFornecedorCidade.get(i).getCidade().getPais());
                }
            }else {
                listaPais = new ArrayList<Pais>();
            }
        } else {
            listaPais = new ArrayList<Pais>();
        }
    }

    public void gerarListaCidade() {
        if ((listaPaisSelecionados != null) && (listaPaisSelecionados.size() == 1)) {
            listaCidade = listaPaisSelecionados.get(0).getCidadeList();
        } else if ((listaPaisSelecionados != null) && (listaPaisSelecionados.size() > 1)) {
            String sql = "select c from Cidade c where";
            for (int i = 0; i < listaPaisSelecionados.size(); i++) {
                sql = sql + " c.pais.idpais=" + listaPaisSelecionados.get(i).getIdpais();
                if ((i + 1) < listaPaisSelecionados.size()) {
                    sql = sql + " or";
                }
            }
            sql = sql + " ORDER BY c.pais.nome, c.cidade.nome";
            listaCidadeSelecionadas = cidadeRepository.list(sql);
            listaCidade = new ArrayList<Cidade>();
            gerarListaFornecedor();
        }else listaCidade = new ArrayList<Cidade>();
    }
    
    public String proximoTela() {
        if (fornecedor != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("listaPais", listaPaisSelecionados);
            session.setAttribute("listaCidade", listaCidadeSelecionadas);
            session.setAttribute("fornecedor", fornecedor);
            return "formulario";
        } else {
            FacesMessage mensagemAtencao = new FacesMessage("Select a school.", "");
            FacesContext.getCurrentInstance().addMessage("Attention", mensagemAtencao);
            return null;
        }
    }
}
