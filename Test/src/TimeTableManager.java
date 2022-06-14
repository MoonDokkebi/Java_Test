
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TimeTableManager {
    private static ArrayList<TimeTableData> data;
    private final String[] timeTableDataList1 = {"월", "화", "수", "목", "금"};
    String[][] timeTableDataList2 = new String[31][6];

    Scanner in = new Scanner(System.in);

    public TimeTableManager() {
        data = new ArrayList<>();
    }//객체 생성

    public void timeTableMenu() {
        System.out.println("[[[[ 시간표 ]]]]");
        System.out.println(" 1. 강의 계획서 보기");
        System.out.println(" 2. 현재시간표 보기");
        System.out.println(" 3. 시간표 추가 (사용자 정의)");
        System.out.println(" 4. 강의계획서로부터 시간표 추가");
        System.out.println(" 5. 시간표 삭제");
        System.out.println(" 0. 종료");
    }

    public void showTimeTableMenu() {
        if(loadData() == -1) return; //강의계획서 불러오기
        makeDefaltTable();//기본시간표 모양 만들기

        while(true) {
            timeTableMenu();
            int menunum = in.nextInt();
            switch (menunum) {
                case 1 -> showLoadData();
                case 2 -> showNowTable();
                case 3 -> addtable_user();
                case 4 -> addtable();
                case 5 -> removeTable();
            }
        }
    }


    public void makeDefaltTable() {
        for (int i = 0; i < 30; i++) {
            timeTableDataList2[i + 1][0] = String.format("%02d:%02d", ((i * 30 + 540) / 60), ((i * 30 + 540) % 60));
        }
        //요일 넣기
        System.arraycopy(timeTableDataList1, 0, timeTableDataList2[0], 1, 5);

    }

    public void addtable() {
        int pos1 = 0;//강의계획서 위지값
        int weekpos = 0;//요일 위치값
        int weekpos2 = 0;//요일 위치값
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        int pos5 = 0;

        String startTime = null;
        String endTime = null;
        String startTime2 = null;
        String endTime2 = null;

        System.out.println("교과번호를 입력하세요 : ");
        String classnum = in.next();
        System.out.println("분반을 입력하세요 : ");
        String classnum2 = in.next();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getClassnum().equals(classnum)) {
                if (data.get(i).getClassnum2().equals(classnum2)) pos1 = i; //과목번호로 주소 찾기
            }
        }
        if (data.get(pos1).getClasstime().contains("인강")) {////////////////////////////////////////////생각해보기
            System.out.println();
        }
        for (int i = 0; i < 5; i++) {
            if (data.get(pos1).getClasstime().contains(timeTableDataList1[i])) {
                weekpos = i;
                startTime = data.get(pos1).getClasstime().substring(1, 6);
                endTime = data.get(pos1).getClasstime().substring(7, 12);

            }
            if (data.get(pos1).getClasstime2().contains(timeTableDataList1[i])) {
                weekpos2 = i;
                startTime2 = data.get(pos1).getClasstime2().substring(1, 6);
                endTime2 = data.get(pos1).getClasstime2().substring(7, 12);

            }
        }
        for (int i = 0; i < 31; i++) {
            if (Objects.equals(timeTableDataList2[i][0], startTime)) pos2 = i;
            if (Objects.equals(timeTableDataList2[i][0], endTime)) pos3 = i;
            if (Objects.equals(timeTableDataList2[i][0], startTime2)) pos4 = i;
            if (Objects.equals(timeTableDataList2[i][0], endTime2)) pos5 = i;
        }
        for (int i = pos2; i < pos3; i++) {
            timeTableDataList2[i][weekpos + 1] = "과목: " + data.get(pos1).getClassname() + " 담당교수" + data.get(pos1).getProfessor();

        }
        for (int i = pos2; i < pos3; i++) {
            timeTableDataList2[i][weekpos2 + 1] = "과목: " + data.get(pos1).getClassname() + " 담당교수" + data.get(pos1).getProfessor();
        }

    }

    public void addtable_user() {

        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;
        System.out.println("요일을 입력하세요 : ");
        String week = in.next();
        System.out.println("시작 시간을 입력하세요 : ");
        String starth = in.next();
        System.out.println("끝나는 시간을 입력하세요 : ");
        String endh = in.next();
        System.out.println("과목명을 입력하세요 : ");
        String subject = in.next();
        System.out.println("담당교수를 입력하세요 : ");
        String professor = in.next();


        for (int i = 0; i < 6; i++) {
            if (Objects.equals(timeTableDataList2[0][i], week)) pos1 = i;
        }
        for (int i = 0; i < 31; i++) {
            if (Objects.equals(timeTableDataList2[i][0], starth)) pos2 = i;
            if (Objects.equals(timeTableDataList2[i][0], endh)) pos3 = i;
        }
        for (int i = pos2; i < pos3; i++) {
            timeTableDataList2[i][pos1] = "과목: " + subject + " 담당교수" + professor;
        }
    }

    public void removeTable() {
        System.out.println("시간표 삭제");
        System.out.println("지울곳의 요일과 시간을 입력하세요 : ");
        System.out.println("지울 요일을 입력하세요 : ");
        String week = in.next();
        System.out.println("지울 시간(시작부분)을 입력하세요 : ");
        String time1 = in.next();
        System.out.println("지울 시간(끝나는부분)을 입력하세요 : ");
        String time2 = in.next();
        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;

        for (int i = 0; i < 6; i++)
            if (Objects.equals(timeTableDataList2[0][i], week)) pos1 = i;
        for (int i = 0; i < 31; i++) {
            if (Objects.equals(timeTableDataList2[i][0], time1)) pos2 = i;
            if (Objects.equals(timeTableDataList2[i][0], time2)) pos3 = i;
        }
        for (int i = pos2; i < pos3; i++) {
            timeTableDataList2[i][pos1] = null;
        }


    }

    public int loadData() {
        String file = "F:\\상민\\대학교\\3학년1학기\\Java_Test\\EveryTimeProject\\timetable.csv";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            String str = null;
            String classtime;
            String classtime2 = null;
            while ((line = buf.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                String grade = tokenizer.nextToken();
                String division = tokenizer.nextToken();
                String classnum = tokenizer.nextToken();
                String classname = tokenizer.nextToken();
                String classnum2 = tokenizer.nextToken();
                String credit = tokenizer.nextToken();
                String professor = tokenizer.nextToken();
                String a = tokenizer.nextToken();
                if (a.contains(".")) {
                    StringTokenizer st = new StringTokenizer(a, ".");
                    classtime = st.nextToken();
                    classtime2 = st.nextToken();
                } else classtime = a;
                String classroom = tokenizer.nextToken();


                TimeTableData timeTableData = new TimeTableData(grade, division, classnum, classname, classnum2, credit, professor, classtime, classtime2, classroom);

                data.add(timeTableData);
            }
            buf.close();
        } catch (FileNotFoundException e) {
            System.out.println("File open error : " + file + "을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void showLoadData() {

        System.out.println(data.toString());
    }


    public void showNowTable() {
        System.out.println(data.get(1).getClasstime());

        for (String[] strings : timeTableDataList2) {
            System.out.println(Arrays.toString(strings));
            System.out.println();
        }
    }



}
