import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * I/O와 스트림
 * - I/O -> Input/Output (입출력)
 * - Java의 I/O 방식은 Node -Stream
 *   - Node : 데이터의 소스 또는 데이터의 목적지
 *   - 노드는 키보드(입력), 모니터(출력), 파일(입출력) 등을 말한다.
 *   - Stream : 노드로부터 데이터를 주고 받는 통로
 *     - 입력으로 사용되는 스트림과 출력으로 사용되는 스트림은 별개
 *     - 입출력을 함께 하는 것은 채널(Channel) - NIO(New Input/Output)
 */


public class InputOutputStream {
    public static void main(String[] args) throws IOException, URISyntaxException {

        // Steam/Reader 등을 사용하는 이유는
        // 노드(입출력 장치/파일/메모리 등)으로부터 데이터를 읽고 쓰는 기본적인 방식
        // 보통은 컴퓨터를 사용하지만, Embedded machine의 경우 Stream을 사용하는 경우가 많다.
        // 그래서 Scanner가 편리하기는 하지만, Stream/Reader의 이해가 필요하다.

        // Stream - byte 단위로 읽고 쓰는 특징
        // byte 단위로 읽어서 int로 출력
        // 한글은 2byte이기 때문에 byte 단위로 끊으면 읽고 쓸수가 없다.
        try (InputStream input = System.in) {
            int read = -1;
            byte[] bytes = new byte[512];
            while((read = input.read(bytes)) != 0) {
                System.out.println(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reader는 char 단위로 읽어서 int로 반환
        // Stream을 이용하여 Reader를 초기화할 수 있다.
        try (InputStreamReader reader = new InputStreamReader(System.in)){
            int read = -1;
            char[] chars = new char[512];
            while((read = reader.read(chars)) != 0) {
                System.out.println(chars.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Stream의 mark/reset 기능
        // mark : mark를 하여, 이후 입력을 저장한다.
        // reset : reset을 하게 되면, mark한 byte로 다시 이동한다.

        // markSupported() : mark/reset 가능 여부 메서드
        System.out.println(System.in.markSupported());

        try (InputStream input = System.in) {
            int read = -1;
            while((read = input.read()) != 'q') {

                if ((char)read == 'm') {
                   input.mark(32);
                }

                if ((char)read == 'r') {
                    input.reset();
                }

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // OutputStream / Writer
        // 메모리를 Node로 하는 입출력
        // 입력 Node : char array
        // 출력 Node : char array
        char[] memory = "메모리 입출력 테스트 입력".toCharArray();
        char[] cbuf = new char[4];

        try (CharArrayReader reader = new CharArrayReader(memory);
            CharArrayWriter writer = new CharArrayWriter();) {
            int read = 0;
            while ((read = reader.read(cbuf)) > 0) {
                writer.write(cbuf, 0, read);
            }
            System.out.println(writer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // File
//        String filePath = "D:\\Temp\\MyTemp";
        String filePath = "C:" + File.separator + "Temp" + File.separator + "MyTemp";
        System.out.println(filePath);

        File fileOne = new File(filePath);  // path만 표현
        // boolean mkdir() : 마지막 Directory만 생성
        // boolean mkdirs() : -r 옵션이 추가된 것, 경로 상에 있는 모든 Directory 생성
        System.out.println(fileOne.mkdirs());

        File fileTwo = new File(filePath, "file2.txt");
        fileTwo.createNewFile();

        File fileTree = new File(fileOne, "file3.txt");
        fileTree.createNewFile();

        File fileFour = new File(new URI("file:///d:/Temp/MyTemp/file4.txt"));
        fileFour.createNewFile();
        fileFour.deleteOnExit();    // Temp 파일을 사용할 때 유용, 프로그램이 종료되면 파일이 삭제 됨

        System.out.println(fileTwo.getName());      // File Name
        System.out.println(fileTwo.getParent());    // Path Name
        System.out.println(fileTwo.isAbsolute());   // Absolute Path를 사용 하는지?
        System.out.println(fileTwo.getAbsolutePath());
        System.out.println(fileTwo.getCanonicalPath()); // .. 등을 모두 배제한 Path

        System.out.println(fileTwo.isDirectory());  // Directory 인지
        System.out.println(fileTwo.isFile());       // File 인지

        System.out.println(Arrays.toString(fileOne.list()));    // 해당 Path의 리스트 반환, String Array로 출력
        System.out.println(Arrays.toString(fileOne.listFiles()));   // File 객체 Array로 출력


        File srcFile = new File(fileOne, "src.txt");
        File dstFile = new File(fileOne, "dst.txt");
        dstFile.createNewFile();

        // Stream을 이용한 파일 복사
        // append = true로 설정하면 이어서 작성 (txt, ini, properties, ...)
        // binary 파일에는 잘 사용하지 않음
        // binary 파일 - 문자열로 작성된 것이 아닌, decoding이 된 상태의 파일
        // 그림파일, 동영상 파일, exe 파일
        try (FileInputStream src = new FileInputStream(srcFile);
             FileOutputStream dst = new FileOutputStream(dstFile, true);) {
            int read = -1;
            byte[] buff = new byte[2];
            while ((read = src.read()) != -1) {
                dst.write(buff, 0, read);
            }
        } catch (IOException e) {

        }

        // 보조 스트림
        // Node에 직접 연결되지 않고, 스트림에 부가적으로 사용되는 스트림
        // 보조 스트림을 연쇄적으로 생성 가능 -> Stream Chaining

        // InputStreamReader - byte -> char 스트림
        // OuputStreamWriter

        // 반응성이 중요하지 않은 경우(파일 입출력, 다운로드, 업로드)
        // BufferedReader        - Stream에 Buffering을 적용하여 스트림 Throughput 향상
        // BufferedWriter          throughput : 평균 전송 량 // 파일의 입출력, 계속 파일을 읽고 쓰는 경우 큰 오버헤드가 발생
        // BufferedInputStream     delay : 버퍼링을 쓸 경우 오히려 안 좋아짐 // 게임, 반응성이 중요한 경우
        // BufferedOutputStream

        File src = new File("C:/Windows/explorer.exe");
        File dst = new File("D:/Temp/MyTemp/explorer.exe");

        try (FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(dst);
            BufferedInputStream buffIn = new BufferedInputStream(in);
            BufferedOutputStream buffOut = new BufferedOutputStream(out);) {

            long start = System.nanoTime();


        } catch (IOException e) {

        }

        // DataInputStream      - 기본형 데이터(Primative Type)을 전송하기 위한 스트림
        // DataOutputStream       Stream, Reader(Writer)는 byte/char형
                                // readBoolean, readByte, readShort ... readUTF
                                // writeBoolean, writeByte, writeShort ... writeUTF
        // Data 보조 Stream 같은 경우, Type에 맞게 읽어야 된다.


        // 객체 직렬화를 위한 인터페이스 - Serializable
        // Serialization : 객체를 일열로 바꾸는 것
        // Deserialization : 일열을 다시 객체로 바꾸는 것
        // HAS-A 관계인 경우, 관계 Class 들도 모두 Serialization을 해야 한다.
        class Foo implements Serializable {
            static final long serialVersionUID = 1L; //클래스 버전 관리, UID: Unique Identity
            // 클래스 버전을 따로 관리하는 이유는 클래스 내용 자체가 바뀔 수 있기 때문에
            // 객체를 저장할 때와 불러올 때 같은지 체크하여
            // serialVersionUID가 일치하지 않으면 실패

            int userID;
            String userName;

            transient String passWord;
            // transient : Serialize에 포함하지 않음
            // Password는 중요 정보이기 때문에 포함시키지 않기 위해서

            public Foo() {}

            public Foo(String userName, int userID, String passWord) {
                this.userName = userName;
                this.userID = userID;
                this.passWord = passWord;
            }

            @Override
            public String toString() {
                return userName + " " + userID + " " + passWord;
            }
        }

        Foo foo = new Foo("hayoon", 1234, "1234");

        File dstFoo = new File("D:/Temp/MyTemp/obj.data");

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dst));
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(dst));) {
            out.writeObject(foo);
            Object read = in.readObject();
            if (read != null && read instanceof Foo) {
                Foo readFoo = (Foo)read;
                System.out.println(readFoo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 부모클래스는 Serializable 하지 않고,
        // 자식클래스를 Serializable 하게 구현하기
        class ParentFoo {
            int memVarOne;
            double memVarTwo;
        }

        class ChildFoo extends ParentFoo implements Serializable {
            int childMember;

            private void writeObject(ObjectOutputStream out) throws IOException {
                // 자식 객체를 쓰기 전에 부모 객체를 써준다.
                out.writeInt(memVarOne);
                out.writeDouble(memVarTwo);
                out.defaultWriteObject();
            }

            private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
                memVarOne = in.readInt();
                memVarTwo = in.readDouble();
                in.defaultReadObject();
            }
        }

        System.out.println("입력 ");
        try {
            int i;
            InputStreamReader isr = new InputStreamReader(System.in);
            while ((i = isr.read()) != '끝') {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("src/input.txt")) {
            int i = 0;
            while ((i = fis.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
