### 리스너 / 콜백의 메모리 누수 예시 

~~~ java
import java.util.ArrayList;
import java.util.List;

interface EventListener {
    void onEvent();
}

class EventSource {
    private final List<EventListener> listeners = new ArrayList<>();

    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    // 리스너 등록 해제 메소드 추가
    //public void unregisterListener(EventListener listener) {
    //    listeners.remove(listener);
    //}

    // 이벤트 발생 시 모든 리스너에게 알림
    public void notifyListeners() {
        for (EventListener listener : listeners) {
            listener.onEvent();
        }
    }
}

class ExampleListener implements EventListener {
    @Override
    public void onEvent() {
        System.out.println("Event received by ExampleListener!");
    }
}

public class Main {
    public static void main(String[] args) {
        EventSource source = new EventSource();
        ExampleListener listener = new ExampleListener();
        
        // 리스너 등록
        source.registerListener(listener);
        
        // 이벤트 발생, 모든 리스너에게 알림
        source.notifyListeners();

        // 리스너 등록 해제
        //source.unregisterListener(listener);
    }
}

~~~
