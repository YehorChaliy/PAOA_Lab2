package org.example;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main extends Application {


    public List<List<Integer>> algorithm() throws Exception {

        List<Double> muls = new ArrayList<>();
        List<Integer> point = new ArrayList<>();
        List<List<Integer>> coordinates = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        ////////////////////////////////////////////////

        System.out.println("Введіть кількість точок: ");
        int amount = scanner.nextInt();
        if (amount < 2){
            System.out.println("Має бути від двох точок до будь-якої кількості!");
            throw new Exception();
        }

        System.out.println("Координати мають бути в межах [-9; 9]");
        for (int i=0;i<amount;i++){
            System.out.println("Вкажіть координати точки "+(i+1)+" по X:");
            int x= scanner.nextInt();
            if (x < -9 || x > 9){
                System.out.println("Зверніть увагу! Координати мають бути в межах [-9; 9]");
                throw new Exception();
            }
            System.out.println("Вкажіть координати точки "+(i+1)+" по Y:");
            int y = scanner.nextInt();
            if (y < -9 || y > 9){
                System.out.println("Зверніть увагу! Координати мають бути в межах [-9; 9]");
                throw new Exception();
            }
            List<Integer> xy = new ArrayList<>();
            xy.add(x);
            xy.add(y);
            coordinates.add(xy);
        }

        ////////////////////////////////////////////////

        System.out.println("Вкажіть точку на площині: ");
        System.out.println("X: ");
        int x = scanner.nextInt();
        System.out.println("Y: ");
        int y = scanner.nextInt();
        point.add(x);
        point.add(y);
        for (int i=0;i<amount;i++){
            List<Integer> second;
            if(i==amount-1){
                second=coordinates.get(0);
            }else {
                second = coordinates.get(i+1);
            }
            List<Integer> first = coordinates.get(i);
            double edgeX = second.get(0)-first.get(0);
            double edgeY = second.get(1)-first.get(1);
            double vectorToRandomPointX= point.get(0)-first.get(0);
            double vectorToRandomPointY= point.get(1)-first.get(1);
            muls.add((vectorToRandomPointX*edgeX)+(vectorToRandomPointY*edgeY));
        }
        int flag=1;
        for (Double scalar:muls){
            if(scalar<0){
                flag=0;
            }
        }
        if(flag==1){
            System.out.println("Точка знаходиться всередині багатокутника.");
        }else{
            System.out.println("Точка знаходиться зовні багатокутника.");
        }
        coordinates.add(point);
        return coordinates;
    }

    ////////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {

        Path path = new Path();
        Circle point = new Circle();
        List<Node> nodes = new ArrayList<>();
        List<List<Integer>> coordinates = algorithm();
        MoveTo moveToFirstPoint = new MoveTo(500+(coordinates.get(0).get(0)*50),1000-(500+(coordinates.get(0).get(1)*50)));
        path.getElements().add(moveToFirstPoint);
        LineTo line;


        for (int i=1;i<coordinates.size()-1;i++){

            Circle circle = new Circle();
            circle.setCenterX(500+(coordinates.get(i).get(0) * 50));
            circle.setCenterY(1000-(500+(coordinates.get(i).get(1) * 50)));
            circle.setRadius(5);
            circle.setFill(Color.BLUE);
            nodes.add(circle);
            line = new LineTo(500+(coordinates.get(i).get(0) * 50), 1000-(500+(coordinates.get(i).get(1) * 50)));
            path.getElements().add(line);
        }
        line = new LineTo(500+(coordinates.get(0).get(0)*50),1000-(500+(coordinates.get(0).get(1)*50)));
        Circle circle = new Circle();
        circle.setCenterX(500+(coordinates.get(0).get(0) * 50));
        circle.setCenterY(1000-(500+(coordinates.get(0).get(1) * 50)));
        circle.setRadius(5);
        circle.setFill(Color.BLUE);
        nodes.add(circle);

        point.setCenterX(500+(coordinates.get(coordinates.size()-1).get(0)*50));
        point.setCenterY(1000-(500+(coordinates.get(coordinates.size()-1).get(1)*50)));
        point.setRadius(5);
        point.setFill(Color.RED);
        nodes.add(point);

        path.getElements().add(line);
        nodes.add(path);

        //// axes lines ////
        Path path1 = new Path();
        Path path2 = new Path();
        MoveTo axisX = new MoveTo(0,500);
        MoveTo axisY = new MoveTo(500,0);
        LineTo lineX = new LineTo(1000, 500);
        LineTo lineY = new LineTo(500, 1000);
        path1.getElements().add(axisX);
        path2.getElements().add(axisY);
        path1.getElements().add(lineX);
        path2.getElements().add(lineY);

        nodes.add(path1);
        nodes.add(path2);

        Group root = new Group(nodes);
        Scene scene = new Scene(root,1000,1000);
        stage.setTitle("Багатокутник");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        System.out.println("****************************");
        System.out.println(" ");

        try {
            launch(args);
        }catch (Exception exception){
            System.out.println("Error!");
        }

        System.out.println(" ");
        System.out.println("****************************");
    }
}
