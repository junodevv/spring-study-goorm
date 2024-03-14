package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean; // 종료 콜백 빈
import org.springframework.beans.factory.InitializingBean;// 초기화 콜백 빈

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자호출, url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    // Bean이 생기고 의존관계주입(초기화)이끝나면 호출되는 메서드
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

     // 빈이 종료될때 호출
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
