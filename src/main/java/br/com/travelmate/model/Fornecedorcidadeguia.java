/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.travelmate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "fornecedorcidadeguia")
public class Fornecedorcidadeguia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfornecedorcidadeguia")
    private Integer idfornecedorcidadeguia;
    @JoinColumn(name = "guiaescola_idguiaescola", referencedColumnName = "idguiaescola")
    @ManyToOne(optional = false)
    private Guiaescola guiaescola;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "fornecedorusuario_idfornecedorusuario", referencedColumnName = "idfornecedorusuario")
    @ManyToOne(optional = false)
    private Fornecedorusuario fornecedorusuario;

    public Fornecedorcidadeguia() {
    }

    public Fornecedorcidadeguia(Integer idfornecedorcidadeguia) {
        this.idfornecedorcidadeguia = idfornecedorcidadeguia;
    }

    public Integer getIdfornecedorcidadeguia() {
        return idfornecedorcidadeguia;
    }

    public void setIdfornecedorcidadeguia(Integer idfornecedorcidadeguia) {
        this.idfornecedorcidadeguia = idfornecedorcidadeguia;
    }

    public Guiaescola getGuiaescola() {
        return guiaescola;
    }

    public void setGuiaescola(Guiaescola guiaescola) {
        this.guiaescola = guiaescola;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Fornecedorusuario getFornecedorusuario() {
        return fornecedorusuario;
    }

    public void setFornecedorusuario(Fornecedorusuario fornecedorusuario) {
        this.fornecedorusuario = fornecedorusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfornecedorcidadeguia != null ? idfornecedorcidadeguia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedorcidadeguia)) {
            return false;
        }
        Fornecedorcidadeguia other = (Fornecedorcidadeguia) object;
        if ((this.idfornecedorcidadeguia == null && other.idfornecedorcidadeguia != null) || (this.idfornecedorcidadeguia != null && !this.idfornecedorcidadeguia.equals(other.idfornecedorcidadeguia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.travelmate.model.Fornecedorcidadeguia[ idfornecedorcidadeguia=" + idfornecedorcidadeguia + " ]";
    }
    
}
