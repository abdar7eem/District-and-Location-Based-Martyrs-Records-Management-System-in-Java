package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import DistrictPackege.District;
import DistrictPackege.DistrictPane;
import DistrictPackege.Dlls;
import DistrictPackege.Dnode;
import LocationPackege.Lnode;
import LocationPackege.Location;
import LocationPackege.LocationPane;
import MartyrPackege.Martyr;
import MartyrPackege.Mnode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	public static Dlls dls=new Dlls();
	TabPane tabPane=new TabPane();
	Tab tab1=new Tab("District Screen");
	Tab tab2=new Tab("Location Screen");
	VBox vbox=new VBox();
	FileChooser fileChooser=new  FileChooser();
	Stage stage=new Stage();
	File f;
	boolean bl=false;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage.getIcons().add(new Image("img\\FileChooser.png"));
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color:#2aae1a;");
			Button Choose=new Button("Choose File");
			Choose.setPrefWidth(100);
			Choose.setStyle("-fx-background-color:#41d3f6;");

			vbox=new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(15);
			try {
				//***** important check image path before run *****
				vbox.getChildren().addAll(new ImageView(new Image("\\img\\FileChooser.png",50,50,false,false)),Choose);
			}catch(Exception ex) {
				vbox.getChildren().addAll(new Label("Check image path"),Choose);
			}
			Choose.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					fileChooser=new FileChooser();
					f=fileChooser.showOpenDialog(primaryStage);	
					stage.close();
					stage=getStage();
					stage.show();
				}
			});
			root.setCenter(vbox);
			stage.setScene(new Scene(root,400,400));
			stage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Stage getStage() {
		try {
			read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cant find file");
			System.exit(0);
		}

		if(bl) {
			System.out.println("Choose Correct File");
			System.exit(0);
		}
		Stage primaryStage=new Stage();
		BorderPane root = new BorderPane();

		DistrictPane dp=new DistrictPane(dls);
		LocationPane lp=new LocationPane(dls);
		tabPane.tabMinWidthProperty().bind(root.widthProperty().divide(2.1));
		dp.getLoad().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				selectTab2();
				lp.getCombo_box().getSelectionModel().select(0);
			}
		});


		tab1.setContent(dp.getRoot());
		tab2.setContent(lp.getRoot());

		tab1.setClosable(false);
		tab2.setClosable(false);
		tabPane.getTabs().addAll(tab1,tab2);

		MenuBar mb=new MenuBar();
		Menu m=new Menu("File");
		MenuItem mi=new MenuItem("Save");
		m.getItems().add(mi);
		mb.getMenus().add(m);
		root.setTop(mb);

		mi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fsave=new FileChooser();
				File f=fsave.showSaveDialog(primaryStage);
				save(f);
			}
		});

		root.setCenter(tabPane);
		Scene scene = new Scene(root,1100,650);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Data Structur - Phase1");
		primaryStage.getIcons().add(new Image("img\\FileChooser.png",30,30,false,false));
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void read() throws FileNotFoundException {

		Scanner input=null;
		File file=f;
		try {
			input=new Scanner(file);
		}catch(Exception ex) {

		}
		input.nextLine();
		while(input.hasNextLine()) {

			String line=input.nextLine();
			String []tokens=line.split(",");

			if(tokens[2].equals("")) {
				tokens[2]="0";
			}

			Date date=new Date();
			String ds=tokens[1];
			String d[]=ds.split("/");

			date.setDate(Integer.parseInt(d[1]));
			date.setMonth(Integer.parseInt(d[0]));
			date.setYear(Integer.parseInt(d[2]));
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);

			Dnode temp=dls.searchDistrict(tokens[4]);

			if(temp!=null) {
				Lnode ptr=temp.ls.searchLocation(tokens[3]);
				if(ptr!=null) {
					temp.ls.addMartyr(new Martyr(tokens[0],date,Integer.parseInt(tokens[2]),((Location)ptr.data),((District)temp.data),tokens[5]),tokens[3]);
				}
				else {
					temp.ls.addFirst(new Location(tokens[3]));
					dls.addLocation(tokens[4], temp.ls);
					Lnode abc=temp.ls.searchLocation(tokens[3]);
					temp.ls.addMartyr(new Martyr(tokens[0],date,Integer.parseInt(tokens[2]),((Location)abc.data),((District)temp.data),tokens[5]),tokens[3]);
				}
			}
			else {
				dls.addSorted(new District (tokens[4]));
				Dnode a=dls.searchDistrict(tokens[4]);
				a.ls.addFirst(new Location(tokens[3]));
				dls.addLocation(tokens[4], a.ls);
				Lnode ptr=a.ls.searchLocation(tokens[3]);
				a.ls.addMartyr(new Martyr(tokens[0],date,Integer.parseInt(tokens[2]),((Location)ptr.data),((District)a.data),tokens[5]),tokens[3]);
			}
		}

	}

	public void save(File f) {
		PrintWriter pw = null;

		try {
			pw=new PrintWriter(f);
			Dnode temp=dls.first;
			pw.print("Name,event,Age,location,District,Gender\n");
			for(int i=0;i<dls.getSize()+1;i++) {
				Lnode ptr=temp.ls.first;
				for(int j=0;j<temp.Lcount+1;j++) {
					Mnode abc=ptr.Mnext;
					for(int k=0;k<ptr.CountMartyr;k++) {
						pw.print(((Martyr)abc.data).toString() +"\n");
						abc=abc.next;	
					}
					ptr=ptr.next;
				}
				temp=temp.next;
			}
		} catch (Exception e) {

		}
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getTab1() {
		return tab1;
	}

	public Tab getTab2() {
		return tab2;
	}

	public void selectTab2() {
		tabPane.getSelectionModel().select(1);
	}


}
