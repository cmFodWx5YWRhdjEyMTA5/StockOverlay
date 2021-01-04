package Model;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.Serializable;

public class Stock implements Serializable {
    // FireStore(Firebase) 접속용 Instance
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String name; // 종목명
    private String stockCode; // 종목코드
    private String detailCode; // 업종코드

    public Stock(String name) {
        this.name = name;

        // 종목명에 맞는 데이터를 FireStore DB 중 Stock 컬렉션에서 불러온다.
        final DocumentReference docRef = db.collection("Stock").document(this.name);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                // 불러오기 실패시
                if (e != null) {
                    Log.w("Failed", "Listen failed.", e);
                    return;
                }

                // 불러오기 성공시
                if (snapshot != null && snapshot.exists()) {
                    // 데이터가 null 이 아닐때
                    stockCode = snapshot.getData().get("code").toString();
                    detailCode = snapshot.getData().get("detail_code").toString();
                    Log.d("This stock's data", "Stock Code: " + stockCode + ", Detail Code: " + detailCode);
                } else {
                    // 데이터가 null 일때
                    Log.d("Null", "Current data: null");
                }
            }
        });
    }

    // 해당 종목의 현재가 메서드
    public int currentPrice(String code) {
        int price = 0;

        return price;
    }

    // 전일 종가 대비 등락 동향(▲ 또는 - 또는 ▼) 메서드
    public int change(String code) {
        // 1 = ▲, 0 = -, -1 = ▼
        int cFlag = 0;

        return cFlag;
    }

    // 전일 종가 대비 등락 가격 계산 메서드
    public int changePrice(String code) {
        int cPrice = 0;

        return cPrice;
    }

    // 전일 종가 대비 등락률 계산 메서드
    public double changeRate(String code) {
        double cRate = 0.0;

        return cRate;
    }

    // 종목명에서 종목코드로 바꾸는 메서드
    public String nameToCode(String name) {
        String code = "";

        return code;
    }

    // 종목코드에서 종목명으로 바꾸는 메서드
    public String codeToName(String code) {
        String name = "";

        return name;
    }
}