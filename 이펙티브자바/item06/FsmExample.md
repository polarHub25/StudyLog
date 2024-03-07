### 일반 로직 : 로그인 

``` java
public class LoginProcess {
    public void login(String userId, String password) {
        if (userId.equals("user") && password.equals("pass")) {
            System.out.println("Login successful");
            // 로그인 성공 처리
        } else {
            System.out.println("Login failed");
            // 로그인 실패 처리
        }
    }
}
```

### FSM 로직 : 로그인 

``` java
public class LoginFSM {
    enum State {
        LOGGED_OUT, LOGGING_IN, LOGGED_IN, LOGIN_FAILED
    }

    private State currentState = State.LOGGED_OUT;

    public void event(String event, String userId, String password) {
        switch (currentState) {
            case LOGGED_OUT:
                if (event.equals("loginAttempt")) {
                    currentState = State.LOGGING_IN;
                    login(userId, password);
                }
                break;
            case LOGGING_IN:
                
                break;
            case LOGGED_IN:
                if (event.equals("logout")) {
                    currentState = State.LOGGED_OUT;
                    System.out.println("Logged out successfully.");
                }
                break;
            case LOGIN_FAILED:
                if (event.equals("retryLogin")) {
                    currentState = State.LOGGING_IN;
                    login(userId, password);
                }
                break;
        }
    }

    private void login(String userId, String password) {
        if (userId.equals("user") && password.equals("pass")) {
            currentState = State.LOGGED_IN;
            System.out.println("Login successful");
        } else {
            currentState = State.LOGIN_FAILED;
            System.out.println("Login failed");
        }
    }
}

```
