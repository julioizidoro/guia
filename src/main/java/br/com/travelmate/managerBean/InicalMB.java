/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.managerBean;

import br.com.travelmate.model.Cidade;
import br.com.travelmate.model.Fornecedorcidade;
import br.com.travelmate.model.Pais;
import br.com.travelmate.repository.CidadeRepository;
import br.com.travelmate.repository.FornecedorCidadeRepository;
import br.com.travelmate.repository.PaisRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Pais pais;
    private List<Pais> listaPais;
    private List<Pais> listaPaisSelecionados;
    private Cidade cidade;
    private List<Cidade> listaCidade;
    private List<Cidade> listaCidadeSelecionadas;
    private Fornecedorcidade fornecedorCidade;
    private List<Fornecedorcidade> listaFornecedorCidade;

    @PostConstruct
    public void init() {
        pais = new Pais();
        cidade = new Cidade();
        fornecedorCidade = new Fornecedorcidade();
        listaCidade = new ArrayList<Cidade>();
        listaCidadeSelecionadas = new ArrayList<Cidade>();
        listaFornecedorCidade = new ArrayList<Fornecedorcidade>();
        listaPais = new ArrayList<Pais>();
        listaPaisSelecionados = new ArrayList<Pais>();
        gerarListaPais();
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

    public Fornecedorcidade getFornecedorCidade() {
        return fornecedorCidade;
    }

    public void setFornecedorCidade(Fornecedorcidade fornecedorCidade) {
        this.fornecedorCidade = fornecedorCidade;
    }

    public List<Fornecedorcidade> getListaFornecedorCidade() {
        return listaFornecedorCidade;
    }

    public void setListaFornecedorCidade(List<Fornecedorcidade> listaFornecedorCidade) {
        this.listaFornecedorCidade = listaFornecedorCidade;
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

    public void gerarListaPais() {
        listaPais = paisRepository.list("SELECT p FROM Pais p order by p.nome");
        if (listaPais == null) {
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

    public void gerarListaFornecedor() {
        if (listaCidadeSelecionadas != null) {
            String sql = "SELECT f FROM Fornecedorcidade f where ";
            if ((listaCidadeSelecionadas.size() == 1)) {
                sql = sql + " f.cidade.idcidade=" + listaCidadeSelecionadas.get(0).getIdcidade();
            } else {
                for (int i = 0; i < listaCidadeSelecionadas.size(); i++) {
                    sql = sql + " f.cidade.idcidade=" + listaCidadeSelecionadas.get(i).getIdcidade();
                    if ((i + 1) < listaCidadeSelecionadas.size()) {
                        sql = sql + " or ";
                    }
                }
            }
            sql = sql + " GROUP BY f.fornecedor.idfornecedor ORDER BY f.fornecedor.nome";
            listaFornecedorCidade = fornecedorCidadeRepository.list(sql);
        } else {
            listaFornecedorCidade = new ArrayList<Fornecedorcidade>();
        }
    }

    public String proximoTela() {
        if (fornecedorCidade != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("listaPais", listaPaisSelecionados);
            session.setAttribute("listaCidade", listaCidadeSelecionadas);
            session.setAttribute("fornecedorCidade", fornecedorCidade);
            return "formulario";
        } else {
            FacesMessage mensagemAtencao = new FacesMessage("Select a school.", "");
            FacesContext.getCurrentInstance().addMessage("Attention", mensagemAtencao);
            return null;
        }
    }
}
