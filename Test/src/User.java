class User {
    private String ussername;  //학년
    private String id;//이수구분
    private String password;//교과번호
    private String studentcode;//과목이름

    public User(String ussername, String id, String password, String studentcode) {
        super();
        this.ussername = ussername;
        this.id = id;
        this.password = password;
        this.studentcode = studentcode;
    }

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }


    public String getStudentcode() {
        return studentcode;
    }

    @Override
    public String toString() {
        return "ussername='" + ussername;

    }
}
