import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    public static ArrayList<User> userdata;
    public static User currentUser;

    Scanner in = new Scanner(System.in);

    public UserManager(){userdata =new ArrayList<>();}

    public void register() { //회원가입
        System.out.println("회원가입");
        System.out.println("학번을 입력하세요");
        String studentCode = in.next();
        System.out.println("이름을 입력하세요");
        String username = in.next();
        System.out.println("ID를 입력하세요");
        String id = in.next();
        System.out.println("비밀번호를 입력하세요");
        String password = in.next();
        System.out.println("비밀번호 한번더 입력하세요");
        String password2 = in.next();


        if(testSc(studentCode)){

        }
        else if (testId(id)) // 아이디가 중복되었으면
        {
            System.out.println("이미 사용중인 아이디 입니다. \n");
        } else if(password.equals(password2)) {
            userdata.add(new User(username,id, password, studentCode));
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("비밀번호를 다시 입력하시오.");
        }
    }
    public boolean testId(String id) {
        boolean check = true;
        User member = findId(id);
        if (member == null)
            check = false;
        return check;

    }

    private User findId(String id) {
        for(User member : userdata) {
            if(member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
    public boolean testSc(String studentCode) {
        boolean check = true;
        User member = findSc(studentCode);
        if (member == null)
            check = false;
        return check;

    }

    private User findSc(String studentCode) {
        for(User member : userdata) {
            if(member.getStudentcode().equals(studentCode)) {
                return member;
            }
        }
        return null;
    }




    public boolean login() {
        System.out.println("로그인");
        System.out.println("ID를 입력하세요");
        String ID = in.next();
        System.out.println("비밀번호를 입력하세요");
        String Password = in.next();


        boolean check = true;
        for (int i = 0; i <userdata.size() ; i++) {
            if (userdata.get(i).getId().equals(ID)//등록된 데이터가 있을때
                    && userdata.get(i).getPassword().equals(Password)) {
                System.out.println("로그인에 성공하였습니다. \n");
                check = false;
                currentUser = userdata.get(i);
                break;
            } else if (!userdata.get(i).getId().equals(ID)) {
                System.out.println("존재하지 않는 아이디입니다. \n");
            } else if (userdata.get(i).getId().equals(ID) && !userdata.get(i).getPassword().equals(Password)) {
                System.out.println("암호가 잘못되었습니다. \n");
            }
        }

        return check;
    }
}
