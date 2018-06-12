package com.example.galdino.loginfacebookcomfirebase;


import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Galdino on 31/07/2016.
 */
public class Pessoa
{
    private static final int IND_FEMININO = 0;
    private static final int IND_MASCULINO = 1;
    private Date dtNascimento;
    //    dia/mes/ano
    private String sdtNascimento;
    private Integer cdRegistro,
            indSexo,
            fgProfessor,
            cdPessoaExterno,
            indCadastroFacebookIncompleto,
            indCadastroFeitoPorFacebook;
    private Integer nrIdade;
    private boolean masculino;
    // Campo usado ao fazer um UPDATE no SERVIDOR, indicando se o update irá conter as informações para
    // alterar o dados dessa classe
    private boolean fgAlteraServidorPessoa;
    // Gets
    public Date getDtNascimento() {
        return dtNascimento;
    }
    public String getSdtNascimento() {
        return sdtNascimento;
    }
    public Integer getCdRegistro() {
        return cdRegistro;
    }
    public Integer getIndSexo()
    {
        if(indSexo != null) {
            return indSexo;
        }
        else
        {
            if(masculino)
            {
                return IND_MASCULINO;
            }
            else
            {
                return IND_FEMININO;
            }
        }
    }
    public Integer getFgProfessor() {
        return fgProfessor;
    }
    public Integer getCdPessoaExterno() {
        return cdPessoaExterno;
    }
    public boolean isfgAlteraServidorPessoa() {
        return fgAlteraServidorPessoa;
    }
    public Integer getNrIdade() {
        return nrIdade;
    }
    public boolean isMasculino() {
        return masculino;
    }
    public Integer getIndCadastroFacebookIncompleto() {
        return indCadastroFacebookIncompleto;
    }
    public Integer getIndCadastroFeitoPorFacebook() {
        return indCadastroFeitoPorFacebook;
    }

    // Sets
    public void setFgProfessor(Integer fgProfessor) {
        this.fgProfessor = fgProfessor;
    }
    public void setIndSexo(Integer indSexo) {
        this.indSexo = indSexo;
    }
    public void setCdRegistro(Integer cdRegistro) {
        this.cdRegistro = cdRegistro;
    }
    public void setSdtNascimento(String sdtNascimento) {
        this.sdtNascimento = sdtNascimento;
        setIdadeToDataNascimento();
    }
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    public void setCdPessoaExterno(Integer cdPessoaExterno) {
        this.cdPessoaExterno = cdPessoaExterno;
    }
    public void setfgAlteraServidorPessoa(boolean fgAlteraServidorPessoa) {
        this.fgAlteraServidorPessoa = fgAlteraServidorPessoa;
    }
    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }

    public void setMasculino(boolean masculino)
    {
        this.masculino = masculino;
        if(masculino)
        {
            indSexo =1;
        }
        else
        {
            indSexo = 0;
        }
    }

    public void setIndCadastroFacebookIncompleto(Integer indCadastroFacebookIncompleto) {
        this.indCadastroFacebookIncompleto = indCadastroFacebookIncompleto;
    }

    public void setIndCadastroFeitoPorFacebook(Integer indCadastroFeitoPorFacebook) {
        this.indCadastroFeitoPorFacebook = indCadastroFeitoPorFacebook;
    }

    private Integer setIdadeToDataNascimento()
    {
        Integer idade = null;
        if(sdtNascimento != null && (!TextUtils.isDigitsOnly(sdtNascimento)))
        {
            setDateToAge();
            //dia/mes/ano
        }
        return idade;
    }

    private void setDateToAge()
    {
        //String sAno = null;
        Integer idade = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
                Date date = format.parse(sdtNascimento);
                //            DateFormat targetFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                //            String sAno = targetFormat.format(date);
                Calendar calendarNascimeto = Calendar.getInstance(); // Data nascimento
                calendarNascimeto.setTime(date);
                //
                Calendar calendarAtual = Calendar.getInstance(); // Data atual
                idade  = calendarAtual.get(Calendar.YEAR) - calendarNascimeto.get(Calendar.YEAR);
        } catch (ParseException e)
        {
            e.printStackTrace();
            idade = null;
        }
        this.nrIdade = idade;
    }

    public boolean isCadastroFeitoPorFacebook()
    {
        if(indCadastroFeitoPorFacebook  != null && indCadastroFeitoPorFacebook == 1) {
            return true;
        }
        return false;
    }

    public boolean isCadastroFacebookIncompleto() {
        if(indCadastroFacebookIncompleto  != null && indCadastroFacebookIncompleto == 1) {
            return true;
        }
        return false;
    }
}
