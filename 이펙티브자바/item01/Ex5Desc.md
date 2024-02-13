### 예제5 정적 팩터리 메소드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다. 
~~~ Java
package item1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // Driver class 인스턴스화
        Class.forName("com.mysql.cj.jdbc.Driver");

        // JDBC URL, 사용자명, 암호 설정
        String url = "url";
        String username = "username";
        String password = "password";

        try {
            
            // 어떤 Connection의 구현체가 반환될지 정적 팩토리 메소드에서는 지정되지 않음
            // DriverManager 클래스의 getConnection 메서드를 호출하여 MySQL 데이터베이스에 연결합니다.
            Connection connection = DriverManager.getConnection(url, username, password);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
~~~
