package com.example.umyhfilian.maizerunner;

import android.graphics.Rect;


public class StagePiece {

    Rect rect;

   public int leftDistance;
   public int topDistance;
   public int rightDistance;
   public int lowerDistance;

    public StagePiece piece1;
    public StagePiece piece2;
    public StagePiece piece3;
    public StagePiece piece4;
    public StagePiece piece5;

    public StagePiece(int leftDistanceI,int topDistanceI,int rightDistanceI, int lowerDistanceI){

        leftDistance = leftDistanceI;
        topDistance = topDistanceI;
        rightDistance = rightDistanceI;
        lowerDistance = lowerDistanceI;



    }

    public StagePiece[] DemoStage() { // Uppbyggnings metod för att definera hinder, Skickas sedan till Main.

        piece1 = new StagePiece(250,10,350,800);
        piece2 = new StagePiece(550,10,650,300);
        piece3 = new StagePiece(1300,10,1400,700);
        piece4 = new StagePiece(950,700,1050,1065);
        piece5 = new StagePiece(650,650,1050,750);

        StagePiece[] DemoStage;
        DemoStage = new StagePiece[5];

        DemoStage[0] = piece1;
        DemoStage[1] = piece2;
        DemoStage[2] = piece3;
        DemoStage[3] = piece4;
        DemoStage[4] = piece5;

        return DemoStage;

    }






}
