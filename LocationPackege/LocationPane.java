package LocationPackege;

import java.util.Date;

import DistrictPackege.District;
import DistrictPackege.Dlls;
import DistrictPackege.Dnode;
import MartyrPackege.Martyr;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LocationPane {
	Dlls dls;
	BorderPane root=new BorderPane();
	HBox hMain;
	VBox vLeft;
	ListView lv;
	ComboBox combo_box;
	Button add,update,delete,navigate,addMartyr,deleteMartyr,updateMartyr,searchMartyr;
	Label Ladd,Lupdate,Ldelete,Lnavigate,LaddMartyr,LdeleteMartyr,LupdateMartyr,LsearchMartyr,marInfo,alert;
	TextField tfAdd,tfupdate,tfdeltetM,tfsearch;
	GridPane gpane;
	Dnode abc;
	Lnode ptr;

	@SuppressWarnings("unchecked")
	public LocationPane(Dlls dls) {
		this.dls=dls;

		hMain=new HBox();
		vLeft=new VBox();
		gpane=new GridPane();
		lv=new ListView();
		lv.setMaxHeight(400);
		lv.setMaxWidth(400);
		String arr[]=dls.getNames();
		combo_box =new ComboBox(FXCollections.observableArrayList(arr));
		add=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		update=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));
		delete=new Button(null,new ImageView(new Image("img\\remove.png",30,30,false,false)));
		navigate=new Button(null,new ImageView(new Image("img\\navigate.png",30,30,false,false)));
		addMartyr=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		deleteMartyr=new Button(null,new ImageView(new Image("img\\remove.png",30,30,false,false)));
		updateMartyr=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));
		searchMartyr=new Button(null,new ImageView(new Image("img\\search.png",30,30,false,false)));

		Ladd=new Label("Add Location");
		Lupdate=new Label("Update Location");
		Ldelete=new Label("Delete Location");
		Lnavigate=new Label("Navigate throw Locations");
		LaddMartyr=new Label("Add Martyr");
		LdeleteMartyr=new Label("Delete Martyr");
		LupdateMartyr=new Label("Update Martyr");
		LsearchMartyr=new Label("Search Martyr");
		marInfo=new Label("");
		alert=new Label("");

		tfAdd=new TextField();
		tfupdate=new TextField();
		tfdeltetM=new TextField();
		tfsearch=new TextField();

		gpane.add(Ladd, 0, 0);
		gpane.add(tfAdd, 1, 0);
		gpane.add(add, 2, 0);
		gpane.add(Lupdate, 0, 1);
		gpane.add(tfupdate, 1, 1);
		gpane.add(update, 2, 1);
		gpane.add(Ldelete, 0, 2);
		gpane.add(delete, 2, 2);
		gpane.add(Lnavigate, 0, 3);
		gpane.add(navigate, 2, 3);
		gpane.add(LaddMartyr, 0, 4);
		gpane.add(addMartyr, 2, 4);
		gpane.add(LdeleteMartyr, 0, 5);
		gpane.add(tfdeltetM, 1, 5);
		gpane.add(deleteMartyr, 2, 5);
		gpane.add(LupdateMartyr, 0, 6);
		gpane.add(updateMartyr, 2, 6);
		gpane.add(LsearchMartyr, 0, 7);
		gpane.add(tfsearch, 1, 7);
		gpane.add(searchMartyr, 2, 7);

		gpane.setHgap(15);
		gpane.setVgap(15);
		gpane.setAlignment(Pos.CENTER);
		gpane.setPadding(new Insets(15));

		combo_box.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				try {
					lv.getItems().clear();
					lv.getItems().addAll(dls.getLocations((String)combo_box.getSelectionModel().getSelectedItem()));
				}catch (Exception e) {
				}
			}

		});

		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(combo_box.getSelectionModel().getSelectedItem()!=null) {
					if(!(tfAdd.getText().isEmpty())) {
						Dnode temp=dls.searchDistrict((String)combo_box.getSelectionModel().getSelectedItem());
						temp.ls.addFirst(new Location(tfAdd.getText()));
						dls.addLocation((String)combo_box.getSelectionModel().getSelectedItem(),temp.ls );
						lv.getItems().clear();
						lv.getItems().addAll(dls.getLocations((String)combo_box.getSelectionModel().getSelectedItem()));
						System.out.println("AAAAAAA");
						alert.setText("Location added successfully");
						alert.setTextFill(Color.BLUE);
					}
					else {
						alert.setText("Insert Valid data");
						alert.setTextFill(Color.RED);
					}
				}
				else {
					alert.setText("Choose District from COMBO_BOX");
					alert.setTextFill(Color.RED);
				}
			}
		});

		update.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(combo_box.getSelectionModel().getSelectedItem()!=null) {
					if(lv.getSelectionModel().getSelectedItem()!=null) {
						if(!(tfupdate.getText().isEmpty())) {
							Dnode temp=dls.searchDistrict((String)combo_box.getSelectionModel().getSelectedItem());
							temp.ls.updateLoc((String)lv.getSelectionModel().getSelectedItem(), tfupdate.getText());
							lv.getItems().clear();
							lv.getItems().addAll(dls.getLocations((String)combo_box.getSelectionModel().getSelectedItem()));
							alert.setText("Location updated successfully");
							alert.setTextFill(Color.BLUE);
						}
						else {
							alert.setText("Insert valid data");
							alert.setTextFill(Color.RED);
						}
					}
					else {
						alert.setText("Select a Location to update");
						alert.setTextFill(Color.RED);
					}
				}
				else {
					alert.setText("Choose District from COMBO_BOX");
					alert.setTextFill(Color.RED);
				}
			}
		});

		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(combo_box.getSelectionModel().getSelectedItem()!=null) {
						if(lv.getSelectionModel().getSelectedItem()!=null) {
							Dnode temp=dls.searchDistrict((String)combo_box.getSelectionModel().getSelectedItem());
							temp.deleteLoc((String)lv.getSelectionModel().getSelectedItem());
							lv.getItems().clear();
							lv.getItems().addAll(dls.getLocations((String)combo_box.getSelectionModel().getSelectedItem()));
							alert.setText("Location Deleted successfully");
							alert.setTextFill(Color.BLUE);
						}
						else {
							alert.setText("Select a location to delete");
							alert.setTextFill(Color.RED);
						}
					}
					else {
						alert.setText("Choose District from COMBO_BOX");
						alert.setTextFill(Color.RED);
					}
				}catch(Exception ex) {

				}
			}
		});

		addMartyr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage stage=new Stage();
				stage.setScene(new Scene(AddM((String)combo_box.getSelectionModel().getSelectedItem(),(String)lv.getSelectionModel().getSelectedItem()),400,400));
				stage.setTitle("Add Martyr");
				stage.getIcons().add(new Image("img\\add.png",30,30,false,false));
				stage.show();
			}
		});

		deleteMartyr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Dnode temp=dls.searchDistrict((String)combo_box.getSelectionModel().getSelectedItem());
				temp.ls.deleteMartyr((String)lv.getSelectionModel().getSelectedItem(), tfdeltetM.getText());
			}
		});

		updateMartyr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage stage=new Stage();
				stage.setScene(new Scene(getUpateM((String)combo_box.getSelectionModel().getSelectedItem(),(String)lv.getSelectionModel().getSelectedItem()),700,450));
				stage.setTitle("Update Martyr");
				stage.getIcons().add(new Image("img\\update.png",30,30,false,false));
				stage.show();
			}

		});

		searchMartyr.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(!tfsearch.getText().isBlank()) {
					if((String)combo_box.getSelectionModel().getSelectedItem()!=null) {
						if((String)lv.getSelectionModel().getSelectedItem()!=null) {
							Dnode temp=dls.searchDistrict((String)combo_box.getSelectionModel().getSelectedItem());
							Martyr m=temp.ls.searchMartyr((String)lv.getSelectionModel().getSelectedItem(), tfsearch.getText());
							if(m!=null) {
								marInfo.setText(" Martyr Name: "+m.getmName()+"\n Martyr age: "+m.getAge()+"\n Martyr Date: "+m.getDate()+"\n Martyr Gender: "+m.getGender());
							}else {
								marInfo.setText("Martyr is not exist");
							}
						}
					}
				}
			}
		});

		navigate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				alert.setText("");
				Stage stage=new Stage();
				stage.setScene(new Scene(getNav(),700,550));
				stage.setTitle("Navigate throw Locations");
				stage.getIcons().add(new Image("img\\navigate.png",30,30,false,false));
				stage.show();
			}
		});

		combo_box.setOnShowing(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				String arr[]=dls.getNames();
				combo_box.setItems(FXCollections.observableArrayList(arr));
			}
		});



		vLeft.setAlignment(Pos.CENTER);
		vLeft.setSpacing(10);
		hMain.setAlignment(Pos.CENTER);
		hMain.setSpacing(15);
		vLeft.getChildren().addAll(combo_box,gpane,marInfo,alert);
		hMain.getChildren().addAll(vLeft,lv);
		root.setCenter(hMain);
		HBox top=new HBox(new Label("Location Screen"));
		top.setAlignment(Pos.CENTER);
		top.setPadding(new Insets(15));
		root.setTop(top);
	}



	public LocationPane() {
		// TODO Auto-generated constructor stub
	}



	public BorderPane getUpateM(String Dname,String Lname) {
		BorderPane root=new BorderPane();
		HBox hbox=new HBox();
		GridPane gpane=new GridPane();
		ListView lv=new ListView();

		Dnode temp=dls.searchDistrict(Dname);
		lv.getItems().addAll(temp.ls.getMar(Lname));
		lv.setMaxHeight(400);

		Label newName=new Label("Martyr new name");
		Label age=new Label("Martyr age");
		Label date=new Label("Martyre date");
		Label gender=new Label("Martyre gender");

		TextField tf1=new TextField();
		TextField tf2=new TextField();
		TextField tf3=new TextField();
		TextField tf4=new TextField();

		Button bt=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));

		gpane.add(newName, 0, 2);
		gpane.add(tf1, 1, 2);
		gpane.add(age, 0, 3);
		gpane.add(tf2, 1, 3);
		gpane.add(date, 0, 4);
		gpane.add(tf3, 1, 4);
		gpane.add(gender, 0, 5);
		gpane.add(tf4, 1, 5);
		gpane.add(bt, 2, 5);
		gpane.setHgap(15);
		gpane.setVgap(15);
		gpane.setAlignment(Pos.CENTER);


		bt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				temp.ls.updateMartyr(Lname, (String)lv.getSelectionModel().getSelectedItem(), tf1.getText(), new Date(tf3.getText()), Integer.parseInt(tf2.getText()), tf4.getText());
				lv.getItems().clear();
				lv.getItems().addAll(temp.ls.getMar(Lname));
			}
		});


		hbox.getChildren().addAll(gpane,lv);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(15);

		root.setCenter(hbox);
		return root;
	}



	public BorderPane getRoot() {
		return root;
	}

	public BorderPane AddM(String district,String location) {
		BorderPane root=new BorderPane();
		VBox vbox=new VBox();
		GridPane gpane=new GridPane();
		Label name=new Label("Martyr name");
		Label age=new Label("Martyr age");
		Label date=new Label("Martyre date");
		Label gender=new Label("Martyre gender");
		Label error=new Label("");

		TextField tf1=new TextField();
		TextField tf2=new TextField();
		TextField tf3=new TextField();
		TextField tf4=new TextField();

		Button bt=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));


		gpane.add(name, 0, 0);
		gpane.add(tf1, 1, 0);
		gpane.add(age, 0, 1);
		gpane.add(tf2, 1, 1);
		gpane.add(date, 0, 2);
		gpane.add(tf3, 1, 2);
		gpane.add(gender, 0, 3);
		gpane.add(tf4, 1, 3);
		gpane.add(bt, 2, 3);
		gpane.add(error, 0, 4);

		gpane.setHgap(15);
		gpane.setVgap(15);
		gpane.setAlignment(Pos.CENTER);

		bt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					Dnode temp=dls.searchDistrict(district);
					Lnode ptr=temp.ls.searchLocation(location);
					Date date=new Date();
					String ds=tf3.getText();
					String d[]=ds.split("/");

					date.setDate(Integer.parseInt(d[1]));
					date.setMonth(Integer.parseInt(d[0]));
					date.setYear(Integer.parseInt(d[2]));
					date.setHours(0);
					date.setMinutes(0);
					date.setSeconds(0);

					temp.ls.addMartyr(new Martyr(tf1.getText(), date,Integer.parseInt(tf2.getText()), (((Location)ptr.data)), (((District)temp.data)), tf4.getText()), (String)lv.getSelectionModel().getSelectedItem());
					error.setText("Martyr added successfuly");
					error.setTextFill(Color.BLACK);
				}catch (Exception e) {
					error.setText("enter valid data");
					error.setTextFill(Color.RED);
				}
			}
		});

		vbox.getChildren().addAll(gpane);
		vbox.setAlignment(Pos.CENTER);
		root.setCenter(vbox);

		return root;
	}

	@SuppressWarnings("unchecked")
	public BorderPane getNav() {
		BorderPane root =new BorderPane();
		HBox hbox=new HBox();
		VBox vbox=new VBox();
		ListView lv=new ListView();
		lv.setMaxHeight(400);
		lv.setMaxWidth(400);
		GridPane gpane=new GridPane();
		Button next=new Button("Next");
		HBox bar=new HBox();
		bar.getChildren().addAll(next);
		bar.setAlignment(Pos.CENTER);
		bar.setSpacing(15);
		String arr[]=dls.getNames();
		ComboBox combo=new ComboBox(FXCollections.observableArrayList(arr));
		Label lname=new Label("Location name:");
		Label lname1=new Label("");
		Label lTotalM=new Label("Location Total Martyr:");
		Label lTotalM1=new Label("");
		Label lTotalF=new Label("Loction Total Female:");
		Label lTotalF1=new Label("");
		Label lAgeAvg=new Label("Location Total Martyr ages:");
		Label lAgeAvg1=new Label("");
		Label lOldest=new Label("Location Oldest Martyr:");
		Label lOldest1=new Label("");
		Label lYoungest=new Label("Location Youngest Martyr:");
		Label lYoungest1=new Label("");

		gpane.add(lname, 0, 0);
		gpane.add(lname1, 1, 0);
		gpane.add(lTotalM, 0, 1);
		gpane.add(lTotalM1, 1, 1);
		gpane.add(lTotalF, 0, 2);
		gpane.add(lTotalF1, 1, 2);
		gpane.add(lAgeAvg, 0, 3);
		gpane.add(lAgeAvg1, 1, 3);
		gpane.add(lOldest, 0, 4);
		gpane.add(lOldest1, 1, 4);
		gpane.add(lYoungest, 0, 5);
		gpane.add(lYoungest1, 1, 5);

		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(15);
		gpane.setVgap(20);

		vbox.getChildren().addAll(combo,gpane,bar);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(15);

		hbox.getChildren().addAll(vbox,lv);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(50);		

		combo.setOnAction(new EventHandler<Event>(){
			@Override
			public void handle(Event arg0) {
				Dnode temp=dls.searchDistrict((String)combo.getSelectionModel().getSelectedItem());
				ptr=temp.ls.first;
				lv.getItems().clear();
				ptr.sortByAge();
				lv.getItems().addAll(temp.ls.getMar(((Location)ptr.data).getlName()));
				lname1.setText(((Location)ptr.data).getlName());
				lTotalM1.setText (String.valueOf(temp.ls.TotalMale(((Location)ptr.data).getlName())));
				lTotalF1.setText (String.valueOf(temp.ls.TotalFemale(((Location)ptr.data).getlName())));
				lAgeAvg1.setText (String.valueOf(temp.ls.ageAVG(((Location)ptr.data).getlName())));
				if(temp.ls.oldest(((Location)ptr.data).getlName())!=null) {
					lOldest1.setText (((Martyr)(temp.ls.oldest(((Location)ptr.data).getlName()))).getmName());
				}
				else {
					lOldest1.setText("Null");
				}
				if((temp.ls.youngest(((Location)ptr.data).getlName())!=null)){
					lYoungest1.setText (((Martyr)(temp.ls.youngest(((Location)ptr.data).getlName()))).getmName());
				}
				else {
					lYoungest1.setText("Null");
				}
				next.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						ptr=ptr.next;
						lv.getItems().clear();
						ptr.sortByAge();
						lv.getItems().addAll(temp.ls.getMar(((Location)ptr.data).getlName()));
						lname1.setText(((Location)ptr.data).getlName());
						lTotalM1.setText (String.valueOf(temp.ls.TotalMale(((Location)ptr.data).getlName())));
						lTotalF1.setText (String.valueOf(temp.ls.TotalFemale(((Location)ptr.data).getlName())));
						lAgeAvg1.setText (String.valueOf(temp.ls.ageAVG(((Location)ptr.data).getlName())));
						if(temp.ls.oldest(((Location)ptr.data).getlName())!=null) {
							lOldest1.setText (((Martyr)(temp.ls.oldest(((Location)ptr.data).getlName()))).getmName());
						}
						else {
							lOldest1.setText("Null");
						}
						if((temp.ls.youngest(((Location)ptr.data).getlName())!=null)){
							lYoungest1.setText (((Martyr)(temp.ls.youngest(((Location)ptr.data).getlName()))).getmName());
						}
						else {
							lYoungest1.setText("Null");
						}
					}
				});


			} 
		});

		root.setCenter(hbox);
		return root;
	}


	public ComboBox getCombo_box() {
		return combo_box;
	}



}
