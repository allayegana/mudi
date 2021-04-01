package com.OuvertureAPP.ouverture.controllers;
import com.OuvertureAPP.ouverture.fatoryConnection.ConnectionFatory;
import com.OuvertureAPP.ouverture.dao.DadosPessoasDAO;
import com.OuvertureAPP.ouverture.modelo.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Table;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




@Controller
@Table(name = "Contas")
public class ContaControllers{

    @Autowired

    private final DadosPessoasDAO dadosPessoasDAO;

    public ContaControllers(){

        Connection connection = new ConnectionFatory().recuperarConexecao();
        this.dadosPessoasDAO = new DadosPessoasDAO(connection);

    }




    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView novo(Conta conta){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Conta conta, BindingResult result, RedirectAttributes attributes) throws ParseException, SQLException {
        if (result.hasErrors()){
            return  novo(conta);
        }else{
            ModelAndView mv = new ModelAndView("redirect:/listar");
            attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso");

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(conta.getData_de_nascimento());

            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = simpleDateFormat1.format(date);
            conta.setData_de_nascimento(dataFormatada);

            dadosPessoasDAO.GRAVAR(conta);

            return mv;
        }
    }

    @RequestMapping("/listar")
    public  ModelAndView listarContas() {
        ModelAndView mv = new ModelAndView("/listar");
        List<Conta> contas = dadosPessoasDAO.listar();
        mv.addObject("contas", contas);

        long registro = dadosPessoasDAO.count();
        mv.addObject("registro" ,registro);
        return mv;
    }


    @RequestMapping("/deletarConta")
    public String deletarConta(String cpf) throws SQLException {
       dadosPessoasDAO.deletarContar(cpf);

       return "redirect:/listar";

    }


}

