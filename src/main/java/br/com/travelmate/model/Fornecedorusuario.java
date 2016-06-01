/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "fornecedorusuario")
public class Fornecedorusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorusuario")
    private Integer idfornecedorusuario;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Size(max = 30)
    @Column(name = "login")
    private String login;
    @Size(max = 30)
    @Column(name = "senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorusuario")
    private List<Fornecedorcidadeguia> fornecedorcidadeguiaList;

    public Fornecedorusuario() {
    }

    public Fornecedorusuario(Integer idfornecedorusuario) {
        this.idfornecedorusuario = idfornecedorusuario;
    }

    public Integer getIdfornecedorusuario() {
        return idfornecedorusuario;
    }

    public void setIdfornecedorusuario(Integer idfornecedorusuario) {
        this.idfornecedorusuario = idfornecedorusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Fornecedorcidadeguia> getFornecedorcidadeguiaList() {
        return fornecedorcidadeguiaList;
    }

    public void setFornecedorcidadeguiaList(List<Fornecedorcidadeguia> fornecedorcidadeguiaList) {
        this.fornecedorcidadeguiaList = fornecedorcidadeguiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorusuario != null ? idfornecedorusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedorusuario)) {
            return false;
        }
        Fornecedorusuario other = (Fornecedorusuario) object;
        if ((this.idfornecedorusuario == null && other.idfornecedorusuario != null) || (this.idfornecedorusuario != null && !this.idfornecedorusuario.equals(other.idfornecedorusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorusuario[ idfornecedorusuario=" + idfornecedorusuario + " ]";
    }
    
}
