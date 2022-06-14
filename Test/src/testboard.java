import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


    public class testboard {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Board board = new Board();

            board.setScanner(sc);
            board.start();
            sc.close();
        }
    }

    class Board {
        Scanner sc;
        Article[] articles = new Article[100];
        int articleLastIndex = -1;
        int articleListNum = 0;

        Member[] members = new Member[101];
        int memberLastIndex = 0;
        boolean joinId; // 회원가입 판별
        Member logIdPw; // 로그인 판별
        boolean statelogin; // 로그인, 로그아웃 판별
        int adminMatche; // 관리자 판별
        int joinNm = 0; // 회원 넘버

        // 스캐너 통로
        void setScanner(Scanner sc) {
            this.sc = sc;
        }

        int getArticlesCount() {
            return articleLastIndex + 1;
        }

        // 시작 함수
        void start() {
            help();

            while (true) {
                System.out.println("");
                System.out.print("게시판 명령어 : ");
                String cmd = sc.next().trim();
                System.out.println("");
                if (cmd.equals("help")) {
                    sc.nextLine();
                    help();
                } else if (cmd.equals("exit")) {
                    sc.nextLine();
                    exit();
                    break;
                } else if (cmd.equals("list")) {
                    System.out.println("== 게시물 리스트 ==");
                    sc.nextLine();
                    list();
                } else if (cmd.equals("add")) {
                    System.out.println("== 게시물 추가 ==");
                    sc.nextLine();
                    add();
                } else if (cmd.equals("detail")) {
                    System.out.println("== 게시물 상세보기 ==");
                    System.out.println("어떤 게시물을 보시겠습니까?");
                    System.out.print("> ");
                    int detNm = sc.nextInt();
                    sc.nextLine();
                    detail(detNm);
                } else if (cmd.equals("delete")) {
                    System.out.println("== 게시물 삭제 ==");
                    System.out.println("어떤 게시물을 삭제겠습니까?");
                    System.out.print("> ");
                    int delNm = sc.nextInt();
                    sc.nextLine();
                    delete(delNm);
                } else if (cmd.equals("modify")) {
                    System.out.println("== 게시물 수정 ==");
                    System.out.println("몇번째 게시물을 수정하시겠습니까?");
                    System.out.print("> ");
                    int modiNm = sc.nextInt();
                    sc.nextLine();
                    modify(modiNm);
                } else if (cmd.equals("join")) {
                    System.out.println("== 회원가입 ==");
                    sc.nextLine();
                    join();
                } else if (cmd.equals("login")) {
                    System.out.println("== 로그인 ==");
                    sc.nextLine();
                    login();
                } else if (cmd.equals("logout")) {
                    System.out.println("== 로그아웃 ==");
                    sc.nextLine();
                    logout();
                } else {
                    System.out.println("올바른 명령어를 입력해 주세요.");
                }
            }
        }

        // 게시물 리스트
        void list() {
            int article = getArticlesCount();

            if (article == 0) {
                System.out.println("게시물이 없습니다.");
            } else {
                System.out.printf("번호 |          날짜         | 조회수 |  제목  | 작성자\n");
                for (int i = 0; i <= articleLastIndex; i++) {
                    System.out.printf("%d    |  %s  |   %d    |   %s   | %s\n", articles[i].num, articles[i].date,
                            articles[i].view, articles[i].title, articles[i].memberId);
                }
            }
        }

        // 게시물 추가
        void add() {
            Article article = new Article();

            if (statelogin || adminMatche == 1) {
                articleLastIndex++;
                article.num = articleListNum + 1;
                article.date = getNowDate();

                if (adminMatche == 1) {
                    article.memberNum = 0;
                } else {
                    article.memberNum = logIdPw.mNum;
                }

                System.out.print("제목 : ");
                article.title = sc.nextLine().trim();
                System.out.print("내용 : ");
                article.body = sc.nextLine().trim();
                article.memberId = logIdPw.loginId;
                System.out.printf("%d번 게시물이 추가되었습니다.\n", articleListNum + 1);
                articleListNum++;

                articles[articleLastIndex] = article;

            } else {
                System.out.println("로그인 후 이용하실 수 있습니다.");
            }


            String articleJson = article.toJson();
            String fileName = articles[articleLastIndex].num + ".txt";
            try {
                OutputStream output = new FileOutputStream(fileName);
                output.write(articleJson.getBytes());
            } catch (Exception e) {
                e.getStackTrace();
            }

        }

        // 게시물 상세
        void detail(int num) {
            Article article = getArticleByNum(num);

            if (statelogin || adminMatche == 1) {
                if (article == null) {
                    System.out.printf("%d번에 해당하는 게시물이 없습니다.\n", num);
                } else {
                    article.view++;
                    System.out.printf("번호 : %d\n", article.num);
                    System.out.printf("날짜 : %s\n", article.date);
                    System.out.printf("제목 : %s\n", article.title);
                    System.out.printf("내용 : %s\n", article.body);
                    System.out.printf("작성자 : %s\n", logIdPw.name);
                    System.out.printf("조회수 : %d\n", article.view);
                }
            } else {
                System.out.println("로그인 후 이용하실 수 있습니다.");
            }
        }

        // 게시물 수정
        void modify(int num) {
            Article article = getArticleByNum(num);

            if (statelogin || adminMatche == 1) {
                if (article == null) {
                    System.out.printf("%d번에 해당하는 게시물이 없습니다.\n", num);
                } else if (logIdPw.mNum == article.memberNum || adminMatche == 1) {
                    System.out.print("수정하실 제목을 입력해주세요 : ");
                    article.title = sc.nextLine();
                    System.out.print("수정하실 내용을 입력해주세요 : ");
                    article.body = sc.nextLine();
                    article.date = getNowDate();
                    System.out.println("게시물 수정이 완료되었습니다.");
                } else {
                    System.out.println("작성자만 수정 가능합니다.");
                }
            } else {
                System.out.println("로그인 후 이용하실 수 있습니다.");
            }
        }

        // 게시물 삭제
        void delete(int num) {
            Article article = getArticleByNum(num);
            int index = -1;

            for (int i = 0; i <= articleLastIndex; i++) {
                Article a = articles[i];

                if (a.num == num) {
                    index = i;
                    break;
                }
            }

            if (statelogin || adminMatche == 1) {
                if (index == -1) {
                    System.out.printf("%d번에 해당하는 게시물이 없습니다.\n", num);
                } else if (logIdPw.mNum == article.memberNum || adminMatche == 1) {
                    for (int i = index; i <= articleLastIndex - 1; i++) {
                        articles[i] = articles[i + 1];
                    }
                    System.out.println("게시물 삭제가 완료되었습니다.");
                    articleLastIndex--;
                } else {
                    System.out.println("작성자만 수정 가능합니다.");
                }
            } else {
                System.out.println("로그인 후 이용하실 수 있습니다.");
            }
        }

        // 회원가입
        void join() {
            System.out.println("환영합니다! 지금부터 회원가입을 진행하겠습니다.");
            Member member = new Member();

            do {
                joinId = false;
                System.out.print("ID : ");
                member.loginId = sc.next().trim();

                for (int i = 1; i <= memberLastIndex; i++) {
                    if (members[i].loginId.equals(member.loginId)) {
                        System.out.println("이미 존재하는 아이디 입니다.");
                        joinId = true;
                    }
                }
            } while (joinId);
            System.out.print("PW : ");
            member.loginPw = sc.next().trim();
            System.out.print("Name : ");
            member.name = sc.next().trim();
            member.mDate = getNowDate();
            joinNm++;
            member.mNum = joinNm;
            System.out.println("회원가입이 완료되었습니다.");

            memberLastIndex++;
            members[memberLastIndex] = member;

            String memberJson = member.toJson();
            String fileName = members[memberLastIndex].name + ".txt";
            try {
                OutputStream output = new FileOutputStream(fileName);
                output.write(memberJson.getBytes());
            } catch (Exception e) {
                e.getStackTrace();
            }

            try {
                // 바이트 단위로 파일읽기
                String filePath = fileName; // 대상 파일
                FileInputStream fileStream = null; // 파일 스트림

                fileStream = new FileInputStream(filePath);// 파일 스트림 생성
                // 버퍼 선언
                byte[] readBuffer = new byte[fileStream.available()];
                while (fileStream.read(readBuffer) != -1) {
                }
                String rs = new String(readBuffer);

                Member member2 = new Member();
                member2.applyJson(rs);

                System.out.println(member2);

                fileStream.close(); // 스트림 닫기
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        // 관리자 판별
        boolean adminMatchedId(String id, String pw) {
            Member member = new Member();
            if (id.equals("admin") && pw.equals("ad123")) {
                member.mNum = 0;
                member.loginId = id;
                member.loginPw = pw;
                member.name = "admin";
                member.mDate = getNowDate();
                members[0] = member;
                logIdPw = members[0];
                statelogin = true;
                return true;
            }
            return false;
        }

        // 로그인 함수
        boolean anyMatchedMember(String id, String pw) {
            for (int i = 1; i <= memberLastIndex; i++) {
                if (members[i].loginId.equals(id) && members[i].loginPw.equals(pw)) {
                    logIdPw = members[i];
                    return true;
                }
            }
            return false;
        }

        // 로그인 아이디 판별
        boolean anyMatchedId(String id) {
            for (int i = 1; i <= memberLastIndex; i++) {
                if (members[i].loginId.equals(id)) {
                    return false;
                }
            }
            return true;
        }

        // 로그인
        void login() {
            if (logIdPw != null) {
                System.out.println("현재 로그인 상태입니다.");
                return;
            }

            System.out.print("ID : ");
            String id = sc.next().trim();
            System.out.print("PW : ");
            String pw = sc.next().trim();

            if (adminMatchedId(id, pw)) {
                adminMatche = 1;
                statelogin = true;
                System.out.println("게시판 관리자 admin님이 로그인 하셨습니다.");
                return;
            }
            if (anyMatchedId(id)) {
                System.out.println("존재하지 않는 아이디 입니다.");
            } else if (anyMatchedMember(id, pw)) {
                System.out.printf("%s님이 로그인 하셨습니다.\n", logIdPw.name);
                statelogin = true;
            } else {
                System.out.println("ID 또는 PW가 맞지 않습니다.");
                login();
            }

        }

        // 로그아웃
        void logout() {
            if (!statelogin) {
                System.out.println("로그인을 먼저 해주세요.");
            } else {
                System.out.printf("%s님이 로그아웃 하셨습니다.\n", logIdPw.name);
                statelogin = false;
                adminMatche = 0;
                logIdPw = null;
            }
        }

        // 시작 함수
        void help() {
            System.out.println("== 명령어 리스트 ==");
            System.out.println("help : 명령어 리스트");
            System.out.println("list : 게시물 리스트");
            System.out.println("add : 게시물 추가");
            System.out.println("detail : 게시물 상세");
            System.out.println("delete : 게시물 삭제");
            System.out.println("modify : 게시물 수정");
            System.out.println("join : 회원가입");
            System.out.println("login : 로그인");
            System.out.println("logout : 로그아웃");
            System.out.println("exit : 게시판 종료");
        }

        // 종료 함수
        void exit() {
            System.out.println("== 게시판 종료 ==");
        }

        // 가장 마지막에 저장된 객체 리모컨
        Article getLastIndex() {
            if (getArticlesCount() > 0) {
                return articles[articleLastIndex];
            }
            return null;
        }

        // Num에 해당하는 객체 리모컨
        Article getArticleByNum(int num) {
            for (int i = 0; i <= articleLastIndex; i++) {
                if (articles[i].num == num) {
                    return articles[i];
                }
            }
            return null;
        }

        // 현재 날짜
        String getNowDate() {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String dateStr = date.format(cal.getTime());
            return dateStr;
        }

    }

    class Article extends Object {
        int num;
        int view;
        int memberNum;
        String title;
        String date;
        String body;
        String memberId;

        public Article (){

        }
        public Article(int num, int view, int memberNum, String title, String date, String body, String memberId) {
            super();
            this.num = num;
            this.view = view;
            this.memberNum = memberNum;
            this.title = title;
            this.date = date;
            this.body = body;
            this.memberId = memberId;
        }

        public void applyJson(String rs) {
            rs = rs.replace("{", "");
            rs = rs.replace("}", "");
            String[] bits = rs.split(",");

            for (int i = 0; i < bits.length; i++) {
                String[] fieldBits = bits[i].split(":");

                String fieldName = fieldBits[0];

                fieldName = fieldName.replace("\"", "");
                String fieldValue = fieldBits[1];

                if (fieldName.equals("id")) {
                    this.num = Integer.parseInt(fieldValue);
                } else if (fieldName.equals("regDate")) {
                    this.date = fieldValue;
                } else if (fieldName.equals("title")) {
                    this.title = fieldValue;
                } else if (fieldName.equals("body")) {
                    this.body = fieldValue;
                }
            }
        }

        @Override
        public String toString() {
            return "Article [id=" + num + ", regDate=" + date + ", title=" + title + ", body=" + body + "]";
        }

        public String toJson() {
            String rs = String.format("{\"id\":%d,\"regDate\":%s,\"title\":%s,\"body\":%s}", num, date, title, body);

            return rs;
        }


        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    }

    class Member{
        int mNum;
        String loginId;
        String loginPw;
        String mDate;
        String name;
        public String toJson() {
            String rs = String.format("{\"num\":%d,\"regDate\":%s,\"loginId\":%s,\"loginPw\":%s,\"\"name\":%s}", mNum, mDate, loginId, loginPw,name);

            return rs;
        }
        @Override
        public String toString() {
            return "Article [id=" + mNum + ", regDate=" + mDate + ", loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + "]";
        }

        public void applyJson(String rs) {
            rs = rs.replace("{", "");
            rs = rs.replace("}", "");
            String[] bits = rs.split(",");

            for (int i = 0; i < bits.length; i++) {
                String[] fieldBits = bits[i].split(":");

                String fieldName = fieldBits[0];

                fieldName = fieldName.replace("\"", "");
                String fieldValue = fieldBits[1];

                if (fieldName.equals("num")) {
                    this.mNum = Integer.parseInt(fieldValue);
                } else if (fieldName.equals("regDate")) {
                    this.mDate = fieldValue;
                } else if (fieldName.equals("loginId")) {
                    this.loginId = fieldValue;
                } else if (fieldName.equals("loginPw")) {
                    this.loginPw = fieldValue;
                } else if (fieldName.equals("name")) {
                    this.name = fieldValue;
                }
            }
        }

    }

