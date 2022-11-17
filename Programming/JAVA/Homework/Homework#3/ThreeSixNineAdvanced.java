package com.company;

public class ThreeSixNineAdvanced {
    public static void main(String[] args){
        int gameLength = 1000;
        int numPeople = 12;
        int myTurn = 3;

        PlayingGame myGame = new PlayingGame();

        for(int i = 0; i<gameLength; i++){
            if(((i+1)%numPeople) == myTurn){                      // 내가 숫자를 외치는 순번
                myGame.gameRule(i+1);
            }
        }
    }
}

class PlayingGame {
    // 3, 6, 9에 대한 "짝" 출력
    public void handClapping(){
        System.out.print("짝!");
    }
    // 5에 대한 "쿵" 출력
    public void footClapping(){
        System.out.print("쿵!");
    }
    // 3, 6, 9, 5를 제외한 숫자에 대한 숫자 출력
    public void shoutingNumber(int num){
        System.out.println(num+"!");
    }

    // 게임 진행 함수
    public void gameRule(int num){

        int handClappingCount = 0;          // 3, 6, 9 개수
        int footClappingCount = 0;          // 5 개수

        int reminderNum = 0;
        int quotientNum = 0;

        // 숫자에 3, 6, 9, 5가 있는지 확인
        if(num<10){
            reminderNum = num;
            quotientNum = 0;
        }else{
            reminderNum = num%10;
            quotientNum = num/10;
        }

        while(true){

            if(reminderNum == 3 || reminderNum == 6 || reminderNum == 9) {
                handClappingCount++;
            }
            else if(reminderNum == 5) {
                footClappingCount++;
            }

            if(quotientNum==0)
                break;

            reminderNum = quotientNum%10;
            quotientNum = quotientNum/10;

        }

        // 숫자 확인 결과에 따른 결과 출력
        if(handClappingCount == 0 && footClappingCount == 0){
            System.out.printf("%-4d -> ",num);
            shoutingNumber(num);
        }
        else {
            System.out.printf("%-4d -> ",num);
            while(handClappingCount != 0 || footClappingCount != 0){
                if(handClappingCount>=footClappingCount) {
                    if (handClappingCount != 0) {
                        handClapping();
                        handClappingCount--;
                    }

                    if (footClappingCount != 0) {
                        footClapping();
                        footClappingCount--;
                    }
                }else{
                    if (footClappingCount != 0) {
                        footClapping();
                        footClappingCount--;
                    }

                    if (handClappingCount != 0) {
                        handClapping();
                        handClappingCount--;
                    }
                }
            }
            System.out.println();
        }
    }
}
