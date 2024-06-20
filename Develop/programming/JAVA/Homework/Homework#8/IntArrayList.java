/**
 * List 인터페이스를 구현하여 IntArrayList 클래스를 완성하시오.
 *
 * List는 순서가 있는 연속된 값으로, List 인터페이스에 선언되어 있는 메소드의 기능은 아래와 같다.
 *
 * - append(): List의 마지막에 value를 삽입한다.
 * - prepend(): List의 시작점에 value를 삽입한다. 기존의 데이터를 한 칸씩 뒤로 옮긴다.
 * - insert(): index에 value를 삽입한다. 기존 데이터를 한 칸씩 뒤로 옮긴다.
 * - remove(): index의 value를 삭제한다. 기존 데이터를 한 칸씩 앞으로 당긴다.
 * - get(): index의 value를 반환한다.
 * - length(): List의 길이를 출력한다.
 *
 * IntArrayList는 int []를 이용하여 List를 구현한다.
 * - 생성자에서는 capacity를 입력받으며, 배열의 크기가 부족할 때마다 2배씩 증가시킨다.
 */

interface List {
    public void append(int value);
    public void prepend(int value);
    public void insert(int index, int value);
    public void remove(int index);
    public int get(int index);
    public void length();
}

public class IntArrayList implements List{

    private int capacity;           // int[] 전체 크기
    private int[] elementData;      // int[] 데이터
    private int size;               // int[] 포함하고 있는 데이터 량

    IntArrayList(int capacity){
        this.size = 0;

        if(capacity > 0){
            this.capacity = capacity;
            elementData = new int[capacity];
        }else if(capacity == 0){
            this.capacity = 0;
            elementData = null;
        }else{
            throw new IllegalArgumentException("Illegal Capacity: "+ capacity);
        }
    }

    private void grow(){
        this.capacity *= 2;
        int[] tempElementData = new int[capacity];
        System.arraycopy(elementData, 0,tempElementData, 0, size);
        elementData = tempElementData;
    }

    @Override
    public void append(int value) {
        if(size == capacity) {
            grow();
        }
        elementData[size++] = value;
//        size++;
    }

    @Override
    public void prepend(int value) {
        if(size == capacity) {
            grow();
        }
        for(int i = size; i > 0; i--){
            elementData[i] = elementData[i-1];
        }
        elementData[0] = value;
        size++;
    }

    @Override
    public void insert(int index, int value) {
        if(index > size) {
            throw new IndexOutOfBoundsException(index + " is Out of Bound");
        }

        if(size == capacity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void remove(int index) {
        if(index >= size){
            throw new IndexOutOfBoundsException(index+" is Out of Bound");
        }else {
            for (int i = index; i < size; i++) {
                elementData[i] = elementData[i + 1];
            }
            elementData[size--] = 0;
//            size--;
        }
    }

    @Override
    public int get(int index) {
        if(index >= size){
            throw new IndexOutOfBoundsException(index+" is Out of Bound");
        }else {
            return elementData[index];
        }
    }

    @Override
    public void length() {
        System.out.println(size);
        /* Test Code
        System.out.println("size: "+size+" "+"capacity: "+capacity);
        for(int i = 0; i < size; i++)
            System.out.print(get(i) +" ");
        System.out.println("");
         */
    }
}

/* Test Code
class TestList{
    public static void main(String[] args) {
        List list = new IntArrayList(3);
        list.append(1);
        list.length();

        list.append(2);
        list.length();

        list.append(3);
        list.length();

        list.prepend(5);
        list.length();

        list.prepend(6);
        list.length();

        list.insert(3, 4);
        list.length();

        list.insert(0, 7);
        list.length();

        list.insert(7, 10);
        list.length();

        list.remove(2);
        list.length();

        list.remove(0);
        list.length();

        list.remove(4);
        list.length();
    }
}

 */