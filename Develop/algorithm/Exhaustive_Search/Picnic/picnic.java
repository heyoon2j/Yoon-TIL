/**
 *
 * [Example]
 * 3
 * 2 1
 * 0 1
 * 4 6
 * 0 1 1 2 2 3 3 0 0 2 1 3
 * 6 10
 * 0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 *
 * [Result]
 * 1
 * 3
 * 4
 *
 */

import java.util.*;

public class picnic {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int testCase;
        int studentCount;
        int friendCouple;
        List<Integer[]> coupleList = new LinkedList<Integer[]>();

        //System.out.print("Case 입력: ");
        testCase= scanner.nextInt();
        scanner.nextLine();

        int[] result = new int[testCase];
        for(int i = 0; i < testCase; i++){
            //System.out.print("학생 수 / 커플 수: ");
            studentCount = scanner.nextInt();
            friendCouple = scanner.nextInt();
            scanner.nextLine();

            //System.out.print("짝 리스트: ");
            for(int j = 0; j < friendCouple; j++) {
                int[] couple = new int[]{scanner.nextInt(), scanner.nextInt()};
                coupleList.add(Arrays.stream(couple).boxed().toArray(Integer[]::new));
            }
            scanner.nextLine();

            result[i] = countingCouple(studentCount, coupleList);
            coupleList.clear();
        }
        for(int i = 0; i < testCase; i++) {
            System.out.println(result[i]);
        }
    }

    static int countingCouple(int studentCount, List<Integer[]> coupleList){
        int[] couple = null;
        int count = 0;
        boolean[] check = new boolean[studentCount];

        while(coupleList.size() >= studentCount/2){
            check[coupleList.get(0)[0]] = check[coupleList.get(0)[1]] = true;
            count += areFriend(coupleList, 0,check);
            check[coupleList.get(0)[0]] = check[coupleList.get(0)[1]] = false;
            coupleList.remove(0);
        }

        return count;
    }

    static int areFriend( List<Integer[]> coupleList, int index, boolean[] check){

        if (isTrue(check))  // 모두 true 이면 모두 짝, 1 반환
            return 1;

        int count = 0;
        // 아직 짝지어 줄 친구가 필요한 경우 //if(areFriend(coupleList, i, check) == 1){   // 모두가 1이 나왔 을 때
        for(int i = index+1; i < coupleList.size(); i++ ){
            if(!check[coupleList.get(i)[0]] && !check[coupleList.get(i)[1]]){
                check[coupleList.get(i)[0]] = check[coupleList.get(i)[1]] = true;
                count+=areFriend(coupleList, i, check);
                check[coupleList.get(i)[0]] = check[coupleList.get(i)[1]] = false;    // 기존을 제외하고
            }
        }

        return count;
    }

    static boolean isTrue(boolean[] check){
        for(int i = 0; i < check.length; i++){
            if(!check[i])
                return false;
        }
        return true;
    }
}