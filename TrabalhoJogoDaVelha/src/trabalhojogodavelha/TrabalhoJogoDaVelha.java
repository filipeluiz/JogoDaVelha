package trabalhojogodavelha;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author filipe
 */
public class TrabalhoJogoDaVelha extends Application {
    
    private Desktop desktop = Desktop.getDesktop();
    private Tabuleiro tab;
    private int repeticoes = 0;
    private String jogador1;
    private String jogador2;
    private StackPane rotulo;
    private Label labelTexto;
    Button [] bts;

    @Override
    public void start(Stage primaryStage) {   
        rotulo = new StackPane();
        bts = new Button[9];
        
        Scene layout1;
        Scene layout2;       

        GridPane grid = new GridPane();                    
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        int n = 0;         
        
    /* ------------- Menu's ------------- */
    
        MenuBar menuBar = new MenuBar();
        MenuBar menuBar2 = new MenuBar();
        
        Menu menuFile = new Menu("Arquivo");
        Menu menuFile2 = new Menu("Arquivo");
        Menu menuSobre = new Menu("Sobre"); 
        Menu menuSobre2 = new Menu("Sobre");
        
        MenuItem newGame = new MenuItem("Novo Jogo");
        MenuItem loadGame = new MenuItem("Continuar Jogo");
        MenuItem exit = new MenuItem("Sair"); 
        MenuItem disciplina = new MenuItem("Disciplina");
        MenuItem disciplina2 = new MenuItem("Disciplina");
        
        MenuItem newGame2 = new MenuItem("Novo Jogo");
        MenuItem saveGame2 = new MenuItem("Salva Jogo");
        MenuItem loadGame2 = new MenuItem("Continuar Jogo");
        MenuItem exit2 = new MenuItem("Sair");   
        
        menuFile.getItems().addAll(newGame, loadGame, exit);
        menuFile2.getItems().addAll(newGame2, saveGame2, loadGame2, exit2);
        menuSobre.getItems().addAll(disciplina);
        menuSobre2.getItems().addAll(disciplina2);
        
        menuBar.getMenus().addAll(menuFile, menuSobre);
        menuBar2.getMenus().addAll(menuFile2, menuSobre2);
               
        newGame.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));        
        loadGame.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));  
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+E")); 
        disciplina.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        
        newGame2.setAccelerator(KeyCombination.keyCombination("Ctrl+N")); 
        saveGame2.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        loadGame2.setAccelerator(KeyCombination.keyCombination("Ctrl+O")); 
        exit2.setAccelerator(KeyCombination.keyCombination("Ctrl+E")); 
        disciplina2.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));        
        
/* ------------- Layout 1 - Titulo e figura ------------- */        
              
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);        
        t.setFill(Color.web("#801E09"));
        t.setText("Jogo da Velha");
        t.setFont(Font.font(null, FontWeight.BOLD, 50));       
        
        Label label2 = new Label();
        Image image = new Image(getClass().getResourceAsStream("/Images/jogoDaVelha.png"));                
        label2.setGraphic(new ImageView(image));       
        
        VBox vbox1 = new VBox();         
        layout1 = new Scene(vbox1, 600, 600);
        ((VBox) layout1.getRoot()).getChildren().addAll(menuBar, t, label2);  
        vbox1.setAlignment(Pos.BASELINE_CENTER);              
        
    /* ------------- Layout 2 - É o jogo ------------- */        
        for (int i=0; i < 9; i++){
            bts[i] = new Button(" ");
            bts[i].setFont(new Font("Calibri", 60));
            bts[i].setFocusTraversable(false);
            bts[i].setStyle("-fx-font-color:black");
            bts[i].setMaxSize(140, 140);
            bts[i].setMinSize(140, 140);
        }
        
        bts[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(0, saveGame2);
        }
        });
        
        bts[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(1, saveGame2);
        }
        });
        bts[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(2, saveGame2);
        }
        });
        bts[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(3, saveGame2);
        }
        });
        bts[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(4, saveGame2);
        }
        });
        bts[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(5, saveGame2);
        }
        });
        bts[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(6, saveGame2);
        /* chama rotina para ver jogada no tabuleiro */
        }
        });
        bts[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(7, saveGame2);
        }
        });
        bts[8].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Jogada(8, saveGame2);
        }
        });       
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                grid.add(bts[n], j, i);
                n++;
            }
        }  
        
        HBox lowpart = new HBox(5);
        lowpart.setMinSize(500,100);    

        HBox.setMargin(rotulo,new Insets(0,0,0,20));

        labelTexto = new Label();
        labelTexto.setFont(new Font("Arial",32));
        
        rotulo.getChildren().addAll(labelTexto);        
       
        VBox vbox2 = new VBox(); 
        vbox2.setAlignment(Pos.TOP_CENTER);
        
        layout2 = new Scene(vbox2, 600, 600);
        ((VBox) layout2.getRoot()).getChildren().addAll(menuBar2,rotulo,grid);        

/* ------------- Eventos ------------- */        
        
        FileChooser fileChooser = new FileChooser();
              
        newGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {    
                TextInputDialog dialogoJogador1 = new TextInputDialog();
                TextInputDialog dialogoJogador2 = new TextInputDialog();

                dialogoJogador1.setTitle("Entrada com seu nome");
                dialogoJogador1.setHeaderText("Nome do jogador 1");
                dialogoJogador1.setContentText("Nome:");
                dialogoJogador1.setWidth(400);
                // se o usuário fornecer um valor, assignamos ao nome
                dialogoJogador1.showAndWait().ifPresent(v1 -> jogador1 = v1);                 
                
                while(jogador1 == null) {
                    dialogoJogador1.setTitle("Entrada com seu nome");
                    dialogoJogador1.setHeaderText("Por favor digite o seu nome do jogador 1");
                    dialogoJogador1.setContentText("Nome:");
                    dialogoJogador1.setWidth(400);
                    dialogoJogador1.showAndWait().ifPresent(v2 -> jogador1 = v2);                      
                }
                
                dialogoJogador2.setTitle("Entrada com seu nome");
                dialogoJogador2.setHeaderText("Nome do jogador 2");
                dialogoJogador2.setContentText("Nome:");
                dialogoJogador2.setWidth(400);
                // se o usuário fornecer um valor, assignamos ao nome
                dialogoJogador2.showAndWait().ifPresent(v3 -> jogador2 = v3); 
                
                if(jogador2 == null) {
                    while(jogador2 == null) {
                        dialogoJogador1.setTitle("Entrada com seu nome");
                        dialogoJogador1.setHeaderText("Por favor digite o seu nome do jogador 2");
                        dialogoJogador1.setContentText("Nome:");
                        dialogoJogador1.setWidth(400);
                        dialogoJogador1.showAndWait().ifPresent(v4 -> jogador2 = v4);                         
                    }
                }
                
                if(jogador2.equalsIgnoreCase(jogador1)){
                    while(jogador2.equalsIgnoreCase(jogador1)) {
                        dialogoJogador1.setTitle("Entrada com seu nome");
                        dialogoJogador1.setHeaderText("Nome repetido, por favor digite o nome diferente");
                        dialogoJogador1.setContentText("Nome:");
                        dialogoJogador1.setWidth(400);
                        dialogoJogador1.showAndWait().ifPresent(v4 -> jogador2 = v4);                      
                    }                      
                }

                limparGrafico(false); 
                
                Jogador jogador1Obj = new Jogador(jogador1);
                Jogador jogador2Obj = new Jogador(jogador2);
                tab = new Tabuleiro(jogador1Obj, jogador2Obj);
                repeticoes = 0;
                labelTexto.setText(tab.getNovaMessage());                
                primaryStage.setScene(layout2);                
            }            
        });
        
        newGame2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {  
                jogador1 = null;
                jogador2 = null;          
                saveGame2.setDisable(false);                
                TextInputDialog dialogoJogador1 = new TextInputDialog();
                TextInputDialog dialogoJogador2 = new TextInputDialog();

                dialogoJogador1.setTitle("Entrada de nome do jogador 1");
                dialogoJogador1.setHeaderText("Entre com seu nome");
                dialogoJogador1.setContentText("Nome:");
                dialogoJogador1.setWidth(400);
                // se o usuário fornecer um valor, assignamos ao nome
                dialogoJogador1.showAndWait().ifPresent(v -> jogador1 = v); 
                
                while(jogador1 == null) {
                    dialogoJogador1.setTitle("Entrada com seu nome");
                    dialogoJogador1.setHeaderText("Por favor digite o seu nome do jogador 1");
                    dialogoJogador1.setContentText("Nome:");
                    dialogoJogador1.setWidth(400);
                    dialogoJogador1.showAndWait().ifPresent(v2 -> jogador1 = v2);                      
                }                
                
                dialogoJogador2.setTitle("Entrada de nome do jogador 2");
                dialogoJogador2.setHeaderText("Entre com seu nome");
                dialogoJogador2.setContentText("Nome:");
                dialogoJogador2.setWidth(400);
                // se o usuário fornecer um valor, assignamos ao nome
                dialogoJogador2.showAndWait().ifPresent(v -> jogador2 = v); 
                
                if(jogador2 == null) {
                    while(jogador2 == null) {
                        dialogoJogador1.setTitle("Entrada com seu nome");
                        dialogoJogador1.setHeaderText("Por favor digite o seu nome do jogador 2");
                        dialogoJogador1.setContentText("Nome:");
                        dialogoJogador1.setWidth(400);
                        dialogoJogador1.showAndWait().ifPresent(v4 -> jogador2 = v4);                         
                    }
                }
                
                if(jogador2.equalsIgnoreCase(jogador1)){
                    while(jogador2.equalsIgnoreCase(jogador1)) {
                        dialogoJogador1.setTitle("Entrada com seu nome");
                        dialogoJogador1.setHeaderText("Nome repetido, por favor digite o nome diferente");
                        dialogoJogador1.setContentText("Nome:");
                        dialogoJogador1.setWidth(400);
                        dialogoJogador1.showAndWait().ifPresent(v4 -> jogador2 = v4);                      
                    }                      
                }                
                
                limparGrafico(false); 
                
                Jogador jogador1Obj = new Jogador(jogador1);
                Jogador jogador2Obj = new Jogador(jogador2);
                tab = new Tabuleiro(jogador1Obj, jogador2Obj);
                labelTexto.setText(tab.getNovaMessage());                
                repeticoes = 0;     
                labelTexto.setText(tab.getNovaMessage());                
            }            
        });        
        
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tab = new Tabuleiro();
                repeticoes = 0;
                fileChooser.setInitialDirectory(new File("savegame/"));
                File file = fileChooser.showOpenDialog(primaryStage);
                //função de carregar o jogo salvo
                try {                 
                   FileInputStream fin = new FileInputStream(file);
                   ObjectInputStream ois = new ObjectInputStream(fin);
                   Tabuleiro t = (Tabuleiro) ois.readObject();                                               
                   tab.setBorda(t.getBorda());
                   tab.setJogadores(t.getJogadores());
                   tab.setSimbolo(t.getSimbolo());
                   tab.setNovaMessage(t.getNovaMessage());
                   tab.setPotJogador1(t.getPotJogador1());
                   tab.setPotJogador2(t.getPotJogador2());                   
                    for (int i=0; i < 9; i++){
                        bts[i].setText(Character.toString(t.getBordaGrafico(i)));
                        bts[i].setFont(new Font("Calibri", 60));
                        bts[i].setFocusTraversable(false);
                        bts[i].setStyle("-fx-font-color:black");
                        bts[i].setDisable(false);  
                        if(t.getBordaGrafico(i) != ' '){
                            bts[i].setDisable(true);
                        }
                        else {
                            bts[i].setDisable(false);
                        } 
                    labelTexto.setText(tab.getNovaMessage());   
                    }   
                    ois.close();
                    fin.close();  
                }
                catch(IOException e) {
                    Alert notLoadAlert = new Alert(Alert.AlertType.ERROR);
                    notLoadAlert.setTitle("Erro de load");
                    notLoadAlert.setHeaderText(null);
                    notLoadAlert.setContentText("Deu erro o arquivo.");
                    notLoadAlert.showAndWait();                           
                    System.out.println("Não carregou: " + e.getMessage());            
                } catch (ClassNotFoundException ex) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro de load");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Não carregou!!!");
                    errorAlert.showAndWait();                           
                    System.out.println("Não carregou: " + ex.getMessage());                     
                    Logger.getLogger(TrabalhoJogoDaVelha.class.getName()).log(Level.SEVERE, null, ex);
                }  
                primaryStage.setScene(layout2); 
                labelTexto.setText(tab.getNovaMessage());
            }
        });
                
        loadGame2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tab = new Tabuleiro();
                repeticoes = 0;                
                fileChooser.setInitialDirectory(new File("savegame/"));
                File file = fileChooser.showOpenDialog(primaryStage);
                //função de carregar o jogo salvo
                try {                 
                   FileInputStream fin = new FileInputStream(file);
                   ObjectInputStream ois = new ObjectInputStream(fin);
                   Tabuleiro t = (Tabuleiro) ois.readObject(); 
                   tab.setBorda(t.getBorda());
                   tab.setJogadores(t.getJogadores());
                   tab.setSimbolo(t.getSimbolo());
                   tab.setNovaMessage(t.getNovaMessage());
                   tab.setPotJogador1(t.getPotJogador1());
                   tab.setPotJogador2(t.getPotJogador2());                   
                    for (int i=0; i < 9; i++){
                        bts[i].setText(Character.toString(t.getBordaGrafico(i)));
                        bts[i].setFont(new Font("Calibri", 60));
                        bts[i].setFocusTraversable(false);
                        bts[i].setStyle("-fx-font-color:black");
                        bts[i].setDisable(false);  
                        if(t.getBordaGrafico(i) != ' '){
                            bts[i].setDisable(true);
                        }
                        else {
                            bts[i].setDisable(false);
                        }                        
                    }   
                    labelTexto.setText(tab.getNovaMessage());                    
                    ois.close();
                    fin.close(); 
                }
                catch(IOException e) {
                    Alert notLoadAlert = new Alert(Alert.AlertType.ERROR);
                    notLoadAlert.setTitle("Erro de load");
                    notLoadAlert.setHeaderText(null);
                    notLoadAlert.setContentText("Deu erro o arquivo.");

                    notLoadAlert.showAndWait();                           
                    System.out.println("Não carregou: " + e.getMessage());            
                } catch (ClassNotFoundException ex) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro de load");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Não carregou!!!");

                    errorAlert.showAndWait();                           
                    System.out.println("Não carregou: " + ex.getMessage());                     
                    Logger.getLogger(TrabalhoJogoDaVelha.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                }
                labelTexto.setText(tab.getNovaMessage());                
            }
        });        
        
        saveGame2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.setInitialDirectory(new File("savegame/"));                
                File file = fileChooser.showSaveDialog(primaryStage);
                // função para salvar 
                if(file != null){
                    try {
                        FileOutputStream fout = new FileOutputStream(file + ".jv");
                        BufferedOutputStream bufferedStream = new BufferedOutputStream(fout);
                        ObjectOutputStream oos = new ObjectOutputStream(bufferedStream);
                        oos.writeObject(tab);
                        oos.close();
                        fout.close();    
                        Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                        saveAlert.setTitle("Save game");
                        saveAlert.setHeaderText(null);
                        saveAlert.setContentText("Salvou com sucesso " + file.getName() + ".jv !");
                        saveAlert.getDialogPane().setMinWidth(200);

                        saveAlert.showAndWait();
                        System.out.println("Salvou!");
                    }
                    catch(Exception e) {                     
                        System.out.println("Não salvou: " + e);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Caixa de diálogo Exceção");
                        alert.setHeaderText("Olha, um diálogo de exceção");
                        alert.setContentText("Não foi possível encontrar o arquivo " + file);

                        // Create expandable Exception.
                        Label label = new Label("O rastreamento de pilha de exceção foi:");

                        TextArea textArea = new TextArea(e.toString());
                        textArea.setEditable(false);
                        textArea.setWrapText(true);

                        textArea.setMaxWidth(200);
                        textArea.setMaxHeight(200);
                        GridPane.setVgrow(textArea, Priority.ALWAYS);
                        GridPane.setHgrow(textArea, Priority.ALWAYS);

                        GridPane expContent = new GridPane();
                        expContent.setMaxWidth(100);
                        expContent.add(label, 0, 0);
                        expContent.add(textArea, 0, 1);

                        // Set expandable Exception into the dialog pane.
                        alert.getDialogPane().setExpandableContent(expContent);

                        alert.showAndWait();                        
                    }                    
                } 
                else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro de salva");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Não salvou!!!");

                    errorAlert.showAndWait();                       
                    System.out.println("Não salvou...");
                }                 
            }
        });
        
        menuSobre.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Label label1 = new Label(" Universidade Católica de Pernambuco \n Disciplina: Prática de Programação \n Professor: Almir Pires  \n Aluno: Filipe Luiz Rodrigues Correia");
                Image image = new Image(getClass().getResourceAsStream("/Images/LOGO_UNICAP.png"));
                label1.setGraphic(new ImageView(image));
                label1.setTextFill(Color.web("#0076a3"));
                label1.setFont(new Font("Arial", 18));
                
                Stage stage = new Stage();
                StackPane grupo = new StackPane(label1);
                Scene dialogo = new Scene(grupo, 450, 380);
                
                stage.setScene(dialogo);
                stage.show();
            }            
        });

        menuSobre2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Label label1 = new Label(" Universidade Católica de Pernambuco \n Disciplina: Prática de Programação \n Professor: Almir Pires  \n Aluno: Filipe Luiz Rodrigues Correia");
                Image image = new Image(getClass().getResourceAsStream("/Images/LOGO_UNICAP.png"));
                label1.setGraphic(new ImageView(image));
                label1.setTextFill(Color.web("#0076a3"));
                label1.setFont(new Font("Arial", 18));
                
                Stage stage = new Stage();
                StackPane grupo = new StackPane(label1);
                Scene dialogo = new Scene(grupo, 450, 380);
                
                stage.setScene(dialogo);
                stage.show();
            }            
        });        
        
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        exit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });        
        
        if(repeticoes == 3) {
            saveGame2.setDisable(true);
        }
           
        primaryStage.setTitle("Jogo da Velha- Universidade Católica de Pernambuco");
        primaryStage.setScene(layout1);
        primaryStage.setResizable(false);        
        primaryStage.show();
    }
    
/* ------------- Metodos ------------- */   
      
    public void Jogada (int simbol, MenuItem menu){ 
        tab.jogada(simbol);         
        bts[simbol].setText(Character.toString(tab.getSimbolo()));
        bts[simbol].setDisable(true);
        bts[simbol].setStyle("-fx-text-fill: black");    
        tab.vezJogador();
        labelTexto.setText(tab.getNovaMessage());        
        
        if(tab.verificarVitoria()) { 
            labelTexto.setText(tab.getNovaMessage());               
            Alert dialogoVitoria = new Alert(Alert.AlertType.CONFIRMATION); 
            dialogoVitoria.setTitle("Continua o jogo");
            dialogoVitoria.setHeaderText(tab.getPotJogador1() + "\n" + tab.getPotJogador2());
            dialogoVitoria.setContentText("Deseja jogar novamente?");
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");
            dialogoVitoria.getButtonTypes().setAll(btnSim, btnNao);
            for(int i=0; i < 9; i++){
                bts[i].setDisable(true);
                bts[i].setStyle("-fx-color: green;");
            } 
            
            dialogoVitoria.showAndWait().ifPresent(c ->  {
                if(c.getText() == "Sim"){
                    labelTexto.setText("É sua vez " + tab.getJogadorAtual().getName() + " " + tab.getJogadorAtual().getSimbolo()); 
                    tab.limpar();
                    limparGrafico(false);   
                }
                else {
                    labelTexto.setText("Fim do jogo!"); 
                    System.out.println("Fim do jogo!");
                    MenuDisable(menu);                    
                }
            });        

            repeticoes = 0;
        }

        if(tab.cheio() && !tab.verificarVitoria() && repeticoes <= 3){
            labelTexto.setText(tab.getNovaMessage());               
            for (int i=0; i < 9; i++){
                bts[i].setText(" ");
                bts[i].setFont(new Font("Calibri", 60));
                bts[i].setFocusTraversable(false);
                bts[i].setDisable(false);
            }       
            tab.limpar();
            repeticoes++;
        }
        
        if(repeticoes == 3) {  
            tab.setNovaMessage("Velha!!!");
            labelTexto.setText(tab.getNovaMessage()); 
            for(int i=0; i < 9; i++){
                bts[i].setStyle("-fx-color: red;");                
                bts[i].setDisable(true);
            }             
            System.out.println("Velha.");
            Alert dialogoVelha = new Alert(Alert.AlertType.INFORMATION); 
            dialogoVelha.setTitle("VELHA!!!");
            dialogoVelha.setHeaderText("Placa do jogo");
            dialogoVelha.setContentText(tab.getPotJogador1() + "    " + tab.getPotJogador2());  
            dialogoVelha.showAndWait(); 
            MenuDisable(menu);
        }     
        //chamada para jogar no tabuleiro (tabuleiro.jogada(simbol))
    }    
    
    public void MenuDisable(MenuItem menu){
        menu.setDisable(true);
    }
    
    public Label fontLabel(String temp){
        Label newlabel = new Label(temp);
        newlabel.setFont(new Font("Arial",32));
        return newlabel;
    }      
    
    public void limparGrafico(boolean disable) {
        for (int i=0; i < 9; i++){
            bts[i].setText(" ");
            bts[i].setFont(new Font("Calibri", 60));
            bts[i].setFocusTraversable(false);
            bts[i].setStyle("-fx-font-color:black");
            bts[i].setDisable(disable);            
        }               
    }
/**
* @param args the command line arguments
*/
    public static void main(String[] args) {
        launch(args);
    } 
}
