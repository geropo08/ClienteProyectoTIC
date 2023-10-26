package uy.edu.um.proyectotic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import uy.edu.um.proyectotic.controladores.loginController;

public class JavaFXMain extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
            .sources(ProyectoticApplication.class).run(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
		//primaryStage.getIcons().add(new Image(JavaFXMain.class.getResourceAsStream("iconoAvion.png")));

		FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
		Parent root = fxWeaver.loadView(loginController.class);
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();



	}
	@Override
	public void stop() {
    	this.applicationContext.close();
    	Platform.exit();
		
	}
    
}

