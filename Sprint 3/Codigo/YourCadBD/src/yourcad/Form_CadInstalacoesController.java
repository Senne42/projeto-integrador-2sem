/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import yourcad.DBConexao.*;
import yourcad.Form_instalacaoAguaController;
import yourcad.Form_instalacaoEnergiaController;
import yourcad.Concessionaria;
<<<<<<< HEAD
import static yourcad.Form_CadClienteController.alterInstalacaoId;
=======
>>>>>>> 22435b5a1474e789c226635ff30337f8cc5cd3f6
import static yourcad.Form_instalacaoAguaController.*;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class Form_CadInstalacoesController implements Initializable {

    @FXML
    private MenuBar menuBar_TelaInicial;
    @FXML
    private ComboBox<String> cbox_tipoInstalacao;
    @FXML
    private Pane panel_01;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane ancPane_TelaInicio;

    public ComboBox<String> getCbox_tipoInstalacao() {
        return cbox_tipoInstalacao;
    }

    public void setCbox_tipoInstalacao(ComboBox<String> cbox_tipoInstalacao) {
        this.cbox_tipoInstalacao = cbox_tipoInstalacao;
    }

    public TextField getTxt_idCliente() {
        return txt_idCliente;
    }

    public void setTxt_idCliente(TextField txt_idCliente) {
        this.txt_idCliente = txt_idCliente;
    }

    public TextField getTxt_numeroInstalacao() {
        return txt_numeroInstalacao;
    }

    public void setTxt_numeroInstalacao(TextField txt_numeroInstalacao) {
        this.txt_numeroInstalacao = txt_numeroInstalacao;
    }

    public TextField getTxt_apelidoInstalacao() {
        return txt_apelidoInstalacao;
    }

    public void setTxt_apelidoInstalacao(TextField txt_apelidoInstalacao) {
        this.txt_apelidoInstalacao = txt_apelidoInstalacao;
    }

    public TextField getTxt_idInstalacao() {
        return txt_idInstalacao;
    }

    public void setTxt_idInstalacao(TextField txt_idInstalacao) {
        this.txt_idInstalacao = txt_idInstalacao;
    }
    @FXML
    private MenuItem menuItem_CadCliente;
    @FXML
    private MenuItem menuItem_CadConta;
    @FXML
    private MenuItem menuItem_CadConcessionaria;
    @FXML
    private MenuItem menuItem_PesqCliente;
    @FXML
    private MenuItem menuItem_PesqConta;
    @FXML
    private MenuItem menuItem_PesqConcessionaria;
    @FXML
    private TextField txt_idCliente;
    @FXML
    private TextField txt_numeroInstalacao;
    @FXML
    private TextField txt_apelidoInstalacao;
    @FXML
    private TextField txt_idInstalacao;
    @FXML
    private ComboBox<Concessionaria> cbox_concessionariaInstalacao;
    @FXML
    private Button btn_limparInstalacao;
    @FXML
    private Button btn_salvarInstalacao;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Concessionaria> linhas_banco;
<<<<<<< HEAD

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        
        int instalacao_id = Form_CadClienteController.alterInstalacaoId;
        
        if(instalacao_id != 0)
        {
            try{
                Connection conn = null;
                ResultSet resultadoBanco = null;
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();

                //****** Selecionando tipo de instalação
                String sql0;
                sql0 = "SELECT * FROM instalacao WHERE instalacao_id = " + alterInstalacaoId +";";
                resultadoBanco = stm.executeQuery(sql0);
                String inst_tipo = null;
                String inst_numero = null;
                String inst_apelido = null;
                String inst_cliente = null;
                String inst_id = null;
                String inst_concessionaria = null;

                while(resultadoBanco.next())
                { 
                    inst_id = resultadoBanco.getString("instalacao_id"); 
                    inst_apelido = resultadoBanco.getString("instalacao_apelido"); 
                    inst_cliente = resultadoBanco.getString("cliente_id"); 
                    inst_concessionaria = resultadoBanco.getString("concessionaria_id"); 
                    inst_numero = resultadoBanco.getString("instalacao_numero"); 
                    inst_tipo = resultadoBanco.getString("instalacao_tipo"); 
                }
                txt_idCliente.setText(inst_cliente);
                txt_apelidoInstalacao.setText(inst_apelido);
                txt_numeroInstalacao.setText(inst_numero);
                txt_idInstalacao.setText(inst_id);
                //cbox_concessionariaInstalacao.setValue(inst_concessionaria);
                cbox_tipoInstalacao.setValue(inst_tipo);

                if("Agua e Esgoto".equals(inst_tipo))
                {
                   panel_01.getChildren().clear();
                   Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoAgua.fxml"));
                   panel_01.getChildren().add(newLoadedPane); 
                }
                // ***** Se for de energia deleta instalacao de energia
                if("Energia".equals(inst_tipo))
                {
                    panel_01.getChildren().clear();
                    Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoEnergia.fxml"));
                    panel_01.getChildren().add(newLoadedPane);
                }


            } catch (Exception ex) { Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);}
        }else{
        
            //Campo que recebe ID do cliente
             String clienteInstalacao = Form_CadClienteController.clienteInstalacao;
             txt_idCliente.setText(clienteInstalacao);

            // ComboBox Tipo de Instalação
            ObservableList<String> lista = FXCollections.observableArrayList("Agua e Esgoto","Energia");
            cbox_tipoInstalacao.setItems(lista);


            // ComboBox Concessionaria
            try{          
                Connection conn = null;
                ResultSet resultadoBanco = null;
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();
                resultadoBanco = stm.executeQuery("SELECT * FROM concessionaria;");

                linhas_banco = FXCollections.observableArrayList();

                while(resultadoBanco.next())
                {                
                    linhas_banco.add(new Concessionaria(resultadoBanco.getInt(1), resultadoBanco.getString(2), resultadoBanco.getString(3),
                      resultadoBanco.getString(4), resultadoBanco.getString(5), resultadoBanco.getString(6), resultadoBanco.getString(7),
                      resultadoBanco.getString(8), resultadoBanco.getString(9), resultadoBanco.getString(10), resultadoBanco.getString(11), 
                      resultadoBanco.getString(12), resultadoBanco.getString(13)));
                }

                    cbox_concessionariaInstalacao.setItems(null);
                    cbox_concessionariaInstalacao.setItems(linhas_banco);

                }   catch (Exception ex) {
                    Logger.getLogger(Form_CadInstalacoesController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        }
=======
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
                
        //Campo que recebe ID do cliente
         String clienteInstalacao = Form_CadClienteController.clienteInstalacao;
         txt_idCliente.setText(clienteInstalacao);
         
         
        // ComboBox Tipo de Instalação
        ObservableList<String> lista = FXCollections.observableArrayList("Agua e Esgoto","Energia");
        cbox_tipoInstalacao.setItems(lista);
        
     
        // ComboBox Concessionaria
        try{
                        
            Connection conn = null;
            ResultSet resultadoBanco = null;
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
            resultadoBanco = stm.executeQuery("SELECT * FROM concessionaria;");
            
            linhas_banco = FXCollections.observableArrayList();



            while(resultadoBanco.next())
            {                
                linhas_banco.add(new Concessionaria(resultadoBanco.getInt(1), resultadoBanco.getString(2), resultadoBanco.getString(3),
                  resultadoBanco.getString(4), resultadoBanco.getString(5), resultadoBanco.getString(6), resultadoBanco.getString(7),
                  resultadoBanco.getString(8), resultadoBanco.getString(9), resultadoBanco.getString(10), resultadoBanco.getString(11), 
                  resultadoBanco.getString(12), resultadoBanco.getString(13)));
            }
                
                cbox_concessionariaInstalacao.setItems(null);
                cbox_concessionariaInstalacao.setItems(linhas_banco);
     
            }   catch (Exception ex) {
                Logger.getLogger(Form_CadInstalacoesController.class.getName()).log(Level.SEVERE, null, ex);
            }
>>>>>>> 22435b5a1474e789c226635ff30337f8cc5cd3f6
    }
    
     // INICIO MENU BAR //
    // FUNÇÃO PARA ABRIR TELA A PARTIR DE MENU BAR 
    @FXML
    public void gotoCliente(ActionEvent event) throws IOException{ 
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoPesqCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoPesqConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoPesqConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    // FIM MENU BAR //
    @FXML
    private void chama_painel(ActionEvent event) throws IOException {
        String tipo = cbox_tipoInstalacao.getValue();
        
        if(tipo == "Agua e Esgoto")
        {
            panel_01.getChildren().clear();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoAgua.fxml"));
            panel_01.getChildren().add(newLoadedPane);            
        }
        else if(tipo == "Energia")
        {
            panel_01.getChildren().clear();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoEnergia.fxml"));
            panel_01.getChildren().add(newLoadedPane);
        }
    }
<<<<<<< HEAD
    static int instalacao_id;
    @FXML
    private void salvarInstalacao(ActionEvent event) throws SQLException, Exception {
        
        int inst_id = Form_CadClienteController.alterInstalacaoId;
        
        String cliente_id = txt_idCliente.getText();
        String apelido_instalacao = txt_apelidoInstalacao.getText();
        String numero_Instalacao = txt_numeroInstalacao.getText();
        String tipo_instalacao = cbox_tipoInstalacao.getValue();
        String id_instalacao = txt_idInstalacao.getText();
        
        if(inst_id == 0)
        {
=======
    
        static int instalacao_id;
    @FXML
    private void salvarInstalacao(ActionEvent event) throws SQLException, Exception {
               
        String cliente_id = txt_idCliente.getText();
        String apelido_instalacao = txt_apelidoInstalacao.getText();
        String numero_Instalacao = txt_numeroInstalacao.getText();
        String tipo_instalacao = cbox_tipoInstalacao.getValue();
        
        //Concessionaria conc = (Concessionaria) cbox_concessionariaInstalacao.getSelectionModel().getSelectedItem();
        //int concessionaria_id = conc.getConcessionaria_id();
        //int concessionaria_id = cbox_concessionariaInstalacao.getSelectionModel().getSelectedItem().getConcessionaria_id();
        //System.out.println(cbox_concessionariaInstalacao.getSelectionModel());
        
        
>>>>>>> 22435b5a1474e789c226635ff30337f8cc5cd3f6
            Connection conn = null;
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
            String query;
            query = "INSERT INTO instalacao(instalacao_numero, instalacao_apelido, instalacao_tipo, cliente_id) VALUES "
                + "('"+ numero_Instalacao +"','"
                    + apelido_instalacao +"','"
                    + tipo_instalacao +"','"
                    + cliente_id + "');";
            System.out.println(query);
            stm.executeUpdate(query);
            
            Statement stm0 = conn.createStatement();
            String query0;
            query0 = "SELECT LAST_INSERT_ID();";
            ResultSet resultado = stm.executeQuery(query0);
            
            while(resultado.next())
            {
                instalacao_id = resultado.getInt("LAST_INSERT_ID()");
            }
<<<<<<< HEAD
        }else
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
                     
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();

            String sql;
            sql = "UPDATE instalacao SET "
                    + "instalacao_numero = '"+ numero_Instalacao +"', "
                    + "instalacao_apelido = '"+ apelido_instalacao +"', "
                    + "instalacao_tipo = '"+ tipo_instalacao +"' "            
                    + "WHERE instalacao_id = "+ id_instalacao +";";
            stm.executeUpdate(sql);
        }
        
=======
            System.out.println(instalacao_id);
            
//        if(tipo_instalacao == "Agua e Esgoto"){
//                
////            String hidrometro = instAgua.txt_hidrometroAgua.getText();
////            String codigoCliente = instAgua.txt_codclienteAgua.getText();
////            String codigoSabesp = instAgua.txt_codsabespAgua.getText();
////            String economias = instAgua.txt_economiasAgua.getText();
////            String tipoLigacao = instAgua.txt_tipoLigacao.getText();
//            
////            String hidrometro = Form_instalacaoAguaController.hidrometro;
////            String codigoCliente = Form_instalacaoAguaController.codigoCliente;
////            String codigoSabesp = Form_instalacaoAguaController.codigoSabesp;
////            String economias = Form_instalacaoAguaController.economias;
////            String tipoLigacao = Form_instalacaoAguaController.tipoLigacao;
////            
////           
////            Statement stm1 = conn.createStatement();
////            String query1;
////            query1 = "INSERT INTO instalacao_agua(instalacao_id" +
////                     "instalacao_agua_hidrometro,\n" +
////                     "instalacao_agua_cod_sabesp,\n" +
////                     "instalacao_agua_cod_cliente,\n" +
////                     "instalacao_agua_economias,\n" +
////                     "instalacao_agua_tipo_ligacao) VALUES "
////                    + "('"+ instalacao_id +"','"
////                    + hidrometro +"','"
////                    + codigoSabesp +"','"
////                    + codigoCliente +"','"
////                    + economias +"','"
////                    + tipoLigacao +"');";
////            System.out.println(query1);
////            stm.executeUpdate(query1);
//            
//
//        }
            
        
//          System.out.println(query);  // TESTE DE FUNCIONAMENTO

>>>>>>> 22435b5a1474e789c226635ff30337f8cc5cd3f6
    }

}
