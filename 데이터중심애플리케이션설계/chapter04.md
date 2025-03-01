#### 실시간 데이터 처리
- Tibco 라이브러리 활용해서 실시간 데이터 전달받음
- 메시지 기반의 비동기 통신  ( tibco 자체가 설비와 직접 통신은 아니고, 설비가 중간 브로커에 데이터를 전송하면 브로커에서 tibco 리스너가 브로커 데이터를 받아 처리
- 비동기 방식으로 큐 기반의 메시징 구조
- 리스너를 등록
- session, connection 닫히면 메시지 받을수없음
#### LSM Tree 
- 쓰기 성능을 최적화하기 위해 설계된 데이터 구조
- 원리
  + 쓰기 연산을 모두 순차적으로 처리하여 랜덤 쓰기 비용 줄이고, 나중에 데이터를 Compaction하는 방식으로 최적화
  + 데이터를 먼저 메모리에 저장하고 (Memtable) 일정 크기가 되면 디스크로 플러시
  + Compaction ( 압축 ) 을 통해 오래된 데이터 삭제하고 최신 데이터만 유지
  + Memtable : TreeMap사용 -> 정렬된 상태로 데이터를 유지하는 메모리 내 저장소 / 일정 크기 이상이 되면 SSTable로 Flush
  + SStable(Sorted String Table) : 디스크에 저장된 정렬된 데이터파일 / Memtable이 가득 차면 새로운 SSTable 생성하여 디스크에 저장
  + Compaction : SSTable이 많아지면 오래된 파일을 병함하여 최신 데이터만 남김
  + 
``` java
import java.io.*;
import java.util.*;

public class LSMTree {
    private static final int MEMTABLE_LIMIT = 5; // MemTable이 가득 차면 Flush 수행
    private TreeMap<String, String> memTable = new TreeMap<>(); // 메모리 테이블
    private List<File> ssTables = new ArrayList<>(); // SSTable 목록
    private int sstableCounter = 0; // SSTable 파일 번호

    /**
     * 데이터를 삽입 (MemTable에 저장)
     */
    public void put(String key, String value) {
        memTable.put(key, value);
        if (memTable.size() >= MEMTABLE_LIMIT) { // 메모리 크기 넘으면 flush 호출하여 sstable로 저장
            flush();
        }
    }

    /**
     * 데이터를 조회 (MemTable -> SSTable 순서로 검색)
     */
    public String get(String key) {
        // 1. 먼저 MemTable에서 검색
        if (memTable.containsKey(key)) {
            return memTable.get(key);
        }
        // 2. MemTable에 없으면 SSTable에서 검색
        for (File sstable : ssTables) {
            String value = searchSSTable(sstable, key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * MemTable을 SSTable로 저장 (Flush 과정)
     */
    private void flush() {
        try {
            File sstableFile = new File("sstable_" + sstableCounter++ + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(sstableFile))) {
                for (Map.Entry<String, String> entry : memTable.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            }
            ssTables.add(sstableFile);
            memTable.clear(); // MemTable 초기화
            System.out.println("Flushed MemTable to " + sstableFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SSTable에서 특정 key 검색
     */
    private String searchSSTable(File sstable, String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sstable))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] kv = line.split(",");
                if (kv[0].equals(key)) {
                    return kv[1]; // 값 반환
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Compaction: 여러 개의 SSTable을 병합
     */
    public void compact() {
        TreeMap<String, String> mergedData = new TreeMap<>();

        // 모든 SSTable을 읽고 최신 데이터로 병합
        for (File sstable : ssTables) {
            try (BufferedReader reader = new BufferedReader(new FileReader(sstable))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] kv = line.split(",");
                    mergedData.put(kv[0], kv[1]); // 최신 데이터 유지
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 새로운 SSTable 생성 (병합된 데이터 저장)
        ssTables.clear();
        try {
            File newSstable = new File("sstable_compacted.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newSstable))) {
                for (Map.Entry<String, String> entry : mergedData.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            }
            ssTables.add(newSstable);
            System.out.println("Compacted all SSTables into " + newSstable.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 테스트 실행 코드
     */
    public static void main(String[] args) {
        LSMTree lsmTree = new LSMTree();

        // 데이터 삽입
        lsmTree.put("apple", "red");
        lsmTree.put("banana", "yellow");
        lsmTree.put("cherry", "red");
        lsmTree.put("date", "brown");
        lsmTree.put("elderberry", "black"); // 5개 -> Flush 발생

        lsmTree.put("fig", "purple");
        lsmTree.put("grape", "green");

        // 데이터 조회
        System.out.println("Get apple: " + lsmTree.get("apple"));
        System.out.println("Get grape: " + lsmTree.get("grape"));
        System.out.println("Get mango (not exist): " + lsmTree.get("mango"));

        // Compaction 실행
        lsmTree.compact();
    }
}

```

### 4장 부호화와 발전
- 부호화(Encoding)
  + 데이터를 저장하거나 네트워크를 통해 전송하기 위해 특정 형식으로 변환하는 과정
  + 데이터 저장, API 통신, 메시지 큐 등
  + 서로 다른 시스템 간의 데이터 교환 가능
- 데이터 부호화 형식
  + 문지 기반 : JSON, XML, CSV
  + 바이너리 기반 포맷 : Thrift, Protocol Buffers, Avro
 
- 스키마 : 데이터 구조를 정의하는 규칙
- 스키마 변경 시 호환성을 유지하는것이 핵심
- 데이터 호환성 전략
  + Backward Compatibility : 새로운 시스템이 예전 데이터를 읽을 수 있음
  + Forward Compatibility : 이전 버전의 시스템이 최신 데이터를 읽을 수 있음
  + Full Compatibility : 신규 & 기존 시스템 모두 읽을 수 있음
    
