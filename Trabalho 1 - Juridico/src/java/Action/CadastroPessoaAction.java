/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Enum.TipoEnvolvimentoEnum;
import Model.PessoaAdvogado;
import Model.PessoaCliente;
import Model.PessoaContrario;
import Model.PessoaOutro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Interface.IAction;
import Persistencia.PessoaDAO;

/**
 *
 * @author thassya
 */
public class CadastroPessoaAction implements IAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nome = request.getParameter("txtNome");
        String documento = request.getParameter("txtDocumento");
        String email = request.getParameter("txtEmail");
        String tipoE =request.getParameter("txtTipoPessoa");

        if (nome.equals("") || tipoE.equals("")) {
            response.sendRedirect("Pessoa/Formulario.jsp");
        } else {
            try {
                TipoEnvolvimentoEnum tipo = TipoEnvolvimentoEnum.getEnumByInt(Integer.parseInt(tipoE));

                switch (tipo) {
                    case ADVOGADO: { 
                        PessoaAdvogado pessoa = new PessoaAdvogado();
                        pessoa.setEmail(email);
                        pessoa.setNome(nome);
                        pessoa.setNumeroDocumento(documento); 
                        PessoaDAO.getInstance().salvar(pessoa);
                        break;}
                    case CLIENTE:{ 
                        PessoaCliente pessoa = new PessoaCliente();
                        pessoa.setEmail(email);
                        pessoa.setNome(nome);
                        pessoa.setNumeroDocumento(documento);
                        PessoaDAO.getInstance().salvar(pessoa);
                        break;}
                    case CONTRARIO: {
                        PessoaContrario pessoa = new PessoaContrario();
                        pessoa.setEmail(email);
                        pessoa.setNome(nome);
                        pessoa.setNumeroDocumento(documento); 
                        PessoaDAO.getInstance().salvar(pessoa);
                        break;}
                    case OUTROS: { 
                        PessoaOutro pessoa = new PessoaOutro();
                        pessoa.setEmail(email);
                        pessoa.setNome(nome);
                        pessoa.setNumeroDocumento(documento); 
                        PessoaDAO.getInstance().salvar(pessoa);
                        break;}
                    default: {System.out.println("Escolha um tipo de pessoa correto!"); break;}
                    
                }
                response.sendRedirect("FrontController?action=ListarPessoas");                
                               
            } catch (SQLException e) {
                response.sendRedirect("Erro.jsp");
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CadastroPessoaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
