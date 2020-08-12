package io.dsub;

import java.util.Scanner;

/**
 * 오목 구현하기
 * <p>
 * Gomoku 클래스를 구현하여 오목을 플레이할 수 있도록 구현한다.
 * <p>
 * 오목은 다음 기능을 포함한다.
 * <p>
 * 0. 15x15 오목판을 이용한다.
 * <p>
 * 1. 두 명의 플레이어가 번갈아 돌을 놓을 수 있도록 한다.
 * 1.0 돌의 위치는 키보드로 좌표를 입력 받는다.
 * 1.1 돌을 놓을 때 둘 수 없는 곳에 돌을 놓을 경우 다시 입력 받는다.
 * 1.2 3-3은 반칙이므로 즉시 패배한다.
 * <p>
 * 2. 돌을 놓을 때 마다 현재 상태를 출력한다.
 * 2.0 흑돌은 x, 백돌은 o로 표시한다.
 * <p>
 * 3. 한 플레이어가 게임에서 승리할 경우 즉시 다음 게임을 시작한다.
 * <p>
 * 4. 각 플레이어는 승리할 때 마다 승수를 외치시오. (ex. "5번 이겼다!")
 * <p>
 * 5. q를 입력받을 경우 플레이어의 이름과 스코어를 출력하고 프로그램을 종료한다.
 * (ex. "에디슨 3 - 2 테슬라")
 */
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("제용쨩");
        Player player2 = new Player("경수쨩");

        Gomoku gomoku = new Gomoku(player1, player2);

        Scanner sc = new Scanner(System.in);

        Player curPlayer = player1;

        gomoku.initialize(); // will be moved?

        do {
            // exit logic todo: impl xtra func
            System.out.print("좌표 입력(예, 1-2) : ");
            String input = sc.nextLine();
            if (input.equals("q")) {
                break;
            }

            String[] strings = input.split("-");

            Position pos = new Position(Integer.parseInt(strings[1])-1,Integer.parseInt(strings[0])-1);
            System.out.println(pos);
            // if input is invalid .. .. . ..
            if (!Validator.validate(gomoku.getBoard(), pos)) {
                System.out.println("Invalid Input! Try Again!");
                continue;
            }

            gomoku.play(curPlayer, pos);
            System.out.println(curPlayer.getName());


            // yoon2ix
            if(Validator.chkThreeByThree(gomoku.getBoard(), pos, (curPlayer == player1)?'x':'o', 0)){
                System.out.println("3 by 3 Input!");
                break;
            }else{
                System.out.println("No 3 by 3");
            }

            // play

            // check finished
            if(Validator.chkFive(gomoku.getBoard(), pos, (curPlayer == player1)?'x':'o')) {
                curPlayer.setNumWin(curPlayer.getNumWin()+1);
                System.out.println(curPlayer.getNumWin()+"번 이겼다!");
                gomoku.isFinished();
                curPlayer = player1;
                continue;
            }

            curPlayer = (curPlayer == player1)?player2:player1;
        } while (true);

        System.out.println(player1.getName()+" "+player1.getNumWin()+" - "+player2.getNumWin()+" "+player2.getName());
    }

}
