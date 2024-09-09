package DistrictPackege;



import LocationPackege.LocationPane;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

public class DistrictPane {
	Main main=new Main();
	LocationPane lp=new LocationPane();
	Dlls dls;
	BorderPane root=new BorderPane();
	HBox hMain;
	VBox vLeft;
	ListView lv;
	Button add,update,delete,navigate,getTotal,load;
	Label Ladd,Lupdate,Ldelete,Lnavigate,LgetTotal,Lload,alert;
	TextField tfAdd,tfupdate,tfgetTotal2;
	DatePicker dapick;
	GridPane gpane;
	Dnode abc;

	public DistrictPane(Dlls dls) {

		this.dls=dls;

		hMain=new HBox();
		vLeft=new VBox();
		gpane=new GridPane();
		lv=new ListView();
		lv.setMaxHeight(400);
		lv.setMaxWidth(400);

		setLv();

		add=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		update=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));
		delete=new Button(null,new ImageView(new Image("img\\remove.png",30,30,false,false)));
		navigate=new Button(null,new ImageView(new Image("img\\navigate.png",30,30,false,false)));
		getTotal=new Button(null,new ImageView(new Image("img\\avg.png",30,30,false,false)));
		load=new Button(null,new ImageView(new Image("img\\load.png",30,30,false,false)));

		Ladd=new Label("Add District");
		Lupdate=new Label("Update District");
		Ldelete=new Label("Delete District");
		Lnavigate=new Label("Navigate throw districts");
		LgetTotal=new Label("Get Total Number of Martyrs");
		LgetTotal=new Label("Get Total Number of Martyrs");
		Lload=new Label("Load first Location to Location Screen");
		alert=new Label("");

		tfAdd=new TextField();
		tfupdate=new TextField();
		tfgetTotal2=new TextField();
		tfgetTotal2.setEditable(false);

		dapick=new DatePicker();
		dapick.setEditable(false);

		gpane.add(Ladd, 0, 0);
		gpane.add(tfAdd, 1, 0);
		gpane.add(add, 2, 0);
		gpane.add(Lupdate, 0, 1);
		gpane.add(tfupdate, 1, 1);
		gpane.add(update, 2, 1);
		gpane.add(Ldelete, 0, 2);
		gpane.add(delete, 1, 2);
		gpane.add(Lnavigate, 0, 3);
		gpane.add(navigate, 1, 3);
		gpane.add(LgetTotal, 0, 4);
		gpane.add(getTotal, 1, 4);
		gpane.add(dapick, 2, 4);
		gpane.add(tfgetTotal2, 3, 4);
		gpane.add(Lload, 0, 5);
		gpane.add(load, 1, 5);

		gpane.setHgap(15);
		gpane.setVgap(15);
		gpane.setAlignment(Pos.CENTER);
		gpane.setPadding(new Insets(15));

		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				if(!(tfAdd.getText().isBlank())) {
					if(dls.CheckDistrict(tfAdd.getText())) {
						dls.addSorted(new District(tfAdd.getText()));
						setLv();
						alert.setText("District added successfully");
						alert.setTextFill(Color.BLUE);
					}
					else {
						alert.setText("Can`t add same District");
						alert.setTextFill(Color.RED);
					}

				}
				else {
					alert.setText("Enter Valid District name");
					alert.setTextFill(Color.RED);
				}
			}
		});

		update.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(!(tfAdd.getText().isBlank())) {
					if(lv.getSelectionModel().getSelectedItem()!=null) {
						String str=String.valueOf(lv.getSelectionModel().getSelectedItem());
						dls.updateDistrict(str, tfupdate.getText());
						setLv();
						alert.setText("District updated successfully");
						alert.setTextFill(Color.BLUE);
					}
					else {
						alert.setText("Choose District to update");
						alert.setTextFill(Color.RED);
					}
				}
				else {
					alert.setText("Enter valid data");
					alert.setTextFill(Color.RED);
				}
			}
		});

		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(lv.getSelectionModel().getSelectedItem()!=null) {
					String str=String.valueOf(lv.getSelectionModel().getSelectedItem());
					dls.deleteDistrict(str);
					setLv();
					alert.setText("District Deleted successfuly");
					alert.setTextFill(Color.BLUE);
				}
				else {
					alert.setText("Choose District to delete it");
					alert.setTextFill(Color.RED);
				}
			}
		});

		navigate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				alert.setText("");
				Stage stage=new Stage();
				stage.setScene(new Scene(getNav(),500,400));
				stage.setTitle("Navigate throw Districts");
				stage.getIcons().add(new Image("img\\navigate.png",30,30,false,false));
				stage.show();
			}
		});

		getTotal.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				alert.setText("");
				try {
					if(lv.getSelectionModel().getSelectedItem()!=null) {
						String str=String.valueOf(lv.getSelectionModel().getSelectedItem());
						int year=dapick.getValue().getYear();
						int month=dapick.getValue().getMonthValue();
						int day=dapick.getValue().getDayOfMonth();
						String date=month+"/"+day+"/"+year;
						tfgetTotal2.setText(String.valueOf(dls.TotalDate(str, date)));
					}
					else {
						alert.setText("Choose a District to get Total Martyr in this date");
						alert.setTextFill(Color.RED);
					}
				}catch(Exception ex) {
					alert.setText("Pick a date");
					alert.setTextFill(Color.RED);
				}
			}
		});

		load.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				alert.setText("");
				main.selectTab2();
				
			}
		});



		vLeft.setAlignment(Pos.CENTER);
		vLeft.setSpacing(15);
		hMain.setAlignment(Pos.CENTER);
		hMain.setSpacing(15);
		vLeft.getChildren().addAll(gpane,alert);
		hMain.getChildren().addAll(vLeft,lv);
		root.setCenter(hMain);
		HBox top=new HBox(new Label("District Screen"));
		top.setAlignment(Pos.CENTER);
		top.setPadding(new Insets(15));
		root.setTop(top);
	}

	private void setLv() {
		lv.getItems().clear();
		for(int i=0;i<getArr().length;i++) {
			if(getArr()[i]!=null) {
				lv.getItems().addAll(getArr()[i]);
			}
		}
	}

	public BorderPane getRoot() {
		return root;
	}



	public BorderPane getNav() {
		abc=dls.first;

		BorderPane root=new BorderPane();
		Button prev=new Button("Prev");
		Button next=new Button("next");
		prev.setDisable(true);
		HBox hbox=new HBox();
		VBox vbox=new VBox();
		Label maxDate1 = new Label();
		GridPane gpane=new GridPane();
		Label dName=new Label("District Name: ");
		Label Lcount=new Label("District Locations: ");
		Label dTotalMale=new Label("District Total Males: ");
		Label dTotaleFemale=new Label("District Total Females: ");
		Label ageAvg=new Label("Average Maertrs ages: ");
		Label maxDate=new Label("The date that has the Maximum number of martyrs: ");

		Label dName1=new Label(((District)abc.data).getdName());
		Label Lcount1=new Label(String.valueOf(abc.Lcount));
		Label dTotalMale1=new Label(String.valueOf(dls.TotalMale(((District)abc.data).getdName())));
		Label dTotaleFemale1=new Label(String.valueOf(dls.TotalFemale(((District)abc.data).getdName())));
		Label ageAvg1=new Label(String.valueOf(dls.ageAvg(((District)abc.data).getdName())));
		if(dls.maxDate(((District)abc.data).getdName())!=null){
			maxDate1.setText(String.valueOf(dls.maxDate(((District)abc.data).getdName()).getMonth()+"/"+dls.maxDate(((District)abc.data).getdName()).getDate()+"/"+dls.maxDate(((District)abc.data).getdName()).getYear()));
		}
		else {
			maxDate1.setText("0");
		}
		gpane.add(dName, 0, 0);
		gpane.add(dName1, 1, 0);
		gpane.add(Lcount, 0, 1);
		gpane.add(Lcount1, 1, 1);
		gpane.add(dTotalMale, 0, 2);
		gpane.add(dTotalMale1, 1, 2);
		gpane.add(dTotaleFemale, 0, 3);
		gpane.add(dTotaleFemale1, 1, 3);
		gpane.add(ageAvg, 0, 4);
		gpane.add(ageAvg1, 1, 4);
		gpane.add(maxDate, 0, 5);
		gpane.add(maxDate1, 1, 5);
		gpane.setHgap(15);
		gpane.setVgap(15);
		gpane.setAlignment(Pos.CENTER);	

		hbox.getChildren().addAll(prev,next);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(15);

		vbox.getChildren().addAll(gpane,hbox);
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);

		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prev.setDisable(false);
				try {
					if(abc.next!=dls.first) {
						abc=abc.next;
						dName1.setText(((District)abc.data).getdName());
						Lcount1.setText(String.valueOf(abc.Lcount));
						dTotalMale1.setText(String.valueOf(dls.TotalMale(((District)abc.data).getdName())));
						dTotaleFemale1.setText(String.valueOf(dls.TotalFemale(((District)abc.data).getdName())));
						ageAvg1.setText(String.valueOf(dls.ageAvg(((District)abc.data).getdName())));
						maxDate1.setText(String.valueOf(dls.maxDate(((District)abc.data).getdName()).getMonth()+"/"+dls.maxDate(((District)abc.data).getdName()).getDate()+"/"+dls.maxDate(((District)abc.data).getdName()).getYear()));

					}
					else {
						next.setDisable(true);
					}
				}catch(Exception ex) {

				}
			}
		});

		prev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				next.setDisable(false);
				if(abc!=dls.first) {
					if(abc==null) {
						abc=dls.last;
					}
					else {
						abc=abc.prev;
					}
					dName1.setText(((District)abc.data).getdName());
					Lcount1.setText(String.valueOf(abc.Lcount));
					dTotalMale1.setText(String.valueOf(dls.TotalMale(((District)abc.data).getdName())));
					dTotaleFemale1.setText(String.valueOf(dls.TotalFemale(((District)abc.data).getdName())));
					ageAvg1.setText(String.valueOf(dls.ageAvg(((District)abc.data).getdName())));
					maxDate1.setText(String.valueOf(dls.maxDate(((District)abc.data).getdName()).getMonth()+"/"+dls.maxDate(((District)abc.data).getdName()).getDate()+"/"+dls.maxDate(((District)abc.data).getdName()).getYear()));
				}
				else {
					prev.setDisable(true);
				}
			}
		});

		root.setCenter(vbox);

		return root;

	}


	public String[] getArr() {
		String []arr=new String[dls.count+1];

		for(int i=0;i<arr.length;i++) {
			arr[i]=dls.getNames()[i];
		}

		return arr;
	}

	public Dlls getDls() {
		return dls;
	}

	public Button getLoad() {
		return load;
	}



}
