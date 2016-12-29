# Proxy Pattern 자바버전
Proxy Pattern의 공통적인 특징 : Client 객체가 어떤 객체(Subject)의 메소드를 통해 원하는 값이나 행동을 하고자 함

## 1. Proxy 기본형  
Proxy 기본형을 토대로 다른 종류의 Proxy를 만들 수 있음

```java
public interface Subject {
    public Object action();
}

public class RealSubject implements Subject {
        public RealSubject(){}
        @Override
        public Object action() {
            /* *  Client가 실제로 호출 하고 싶은 어떤 메소드 * */
            return null;
        }
}

/* Proxy 기본형*/
public class Proxy implements Subject {
    private RealSubject realSubject;    // Proxy에서 RealSubject를 가지고 있음
    public Proxy() {
        realSubject = new RealSubject();
    }
    /**
     * Proxy의 action은 RealSubject의 action을 호출
     * @return
     */
    @Override
    public Object action() {
        return realSubject.action();
    }
}
```

위의 Proxy 기본형에서 다른 Proxy를 만들 때 변하는 것은 크게 2가지가 있다.  
- RealSubject의 생성 시점
- Proxy 객체의 action()에서 하는 일

## 2. 동적 생성 Proxy
프록시 객체가 실제 객체를 생성하지 않고 시작한다.  
action(실제 요청)이 들어왔을 때, 객체를 생성한다.  
객체 생성에 많은 자원이 소모되지만, 사용 빈도가 낮을 때 사용한다.  

```java
class DynamicProxy implements Subject {
    private RealSubject realSubject;
    public DynamicProxy() {}
    @Override
    public Object action() {
        if(realSubject == null) {
            realSubject = new RealSubject();
        }
        return realSubject.action();
    }
}
```

## 3. 자원 관리 Proxy
Subject 에서도 자원 생성 비용이 많이 드는 것만 담당하는 Proxy(동적 Proxy와 흡사)  

```java
class ResourceManagementProxy implements Subject {
    private RealSubject realSubject;
    private String dvdName;     // 위의 RealSubject에도 있다고 가정
    public ResourceManagementProxy() {}
    @Override
    public Object action() {
        return realSubject.action();
    }

    /* RealSubject에서 자원 생성을 하는 메소드를 오버라이드 한다(위의 RealSubject에는 없다)
    *  동적 Proxy는 객체(RealSubject)를 생성하는 자원 비용을 줄이는 것이지만, 자원 관리 Proxy는 객체(RealSubject)에서 자원을 생성하는 비용을 줄이는 것.
    * */
    public void readDvd(String dvdName) {
        if(this.dvdName.equals(dvdName)) {
            return ;
        } else {
            this.dvdName = dvdName;
            realSubject.readDvd(dvdName);
        }
    }
}
```

## 4. 가상 Proxy
실제 객체가 존재하지 않을 때 사용한다.  
인터페이스만 정의되어있고, 구현이 아직 되어있지 않을 때 사용한다.  
가상 Proxy에서 시뮬레이션 동작을 하도록 구현하는 테스트용?? 으로 볼 수 있다.  

```java
class VirtualProxy implements Subject {
    //    private RealSubject realSubject;
    public VirtualProxy(){}
    public Object action() {
        return virtualAction();
    }
    private Object virtualAction(){ /* do something */ return null; }
}
```

## 5. 원격 Proxy
RealSubject가 다른 JVM이나 다른 서버에 있는 경우, 같은 공간에서 동작하는 것처럼 만든다.  
Client는 RemotProxy가 RealSubject인줄 안다.  
저수준 작업(네트워킹, 데이터 변환)을 주로 처리한다.  

```java
class RemoteProxy implements Subject {
    public RemoteProxy(){}
    public Object action() {
        return remoteAction();
    }
    private Object remoteAction() {
        connect();  // 다른 JVM이나 서버를 연결 함
        Object result = getResponse();  // RealSubject가 반환하는 값을 저장
        disconnect();   // 연결 종료

        return result;
    }

    /* 저수준 작업들 */
    private void connect() { /* 원격 연결 로직 */ }
    private Object getResponse() { /* 연결된 곳에 있는 RealSubject로부터 데이터 받는 로직 */ }
    private void disconnect() { /* 연결 해제 */ }
}
```

## 6. AOP Proxy
우리가 알고 있는 AOP를 Proxy 형태로 구현한 것 이다.  
즉, 모든 객체에 Proxy를 연결하여 필요한 동일 작업을 수행하게 한다. 

```java
class AOPProxy implements Subject {
    private RealSubject realSubject;
    public AOPProxy(){
        realSubject = new RealSubject();
    }
    public Object action() {
        preProcess();
        Object result = realSubject.action();
        postProcess();
        return result;
    }
    private void preProcess(){/* 선행 작업 */}
    private void postProcess(){/* 후행 작업 */}
}
```

이외에도 엄청나게 많은 Proxy Pattern들이 존재한다.  
결론은 Client 객체가 Subject 객체의 어떤 action을 호출하고자 할 때, 부가적인 작업이 필요한 경우 Proxy Pattern을 사용하면 좋다.  
Client는 Subject(Subject를 구현한 Proxy)의 action만을 호출하면 되고, Subject는 자신의 할일을 그대로 하면 된다.  
단지 Proxy에서만 Subject action()을 호출하기 위해 필요한 부가작업과 직접 Subject action()을 호출할 뿐이다.  