import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

let stompClient = null;

export const connectStomp = (roomId, callback) => {
    const socket = new SockJS("http://localhost:8080/ws/chat");
    stompClient = new Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000,
        debug: (str) => console.log("STOMP Debug:", str),
        onConnect: () => {
            console.log(`STOMP 연결 완료! (방 번호: ${roomId})`);

            // 방 구독
            stompClient.subscribe(`/sub/chat/${roomId}`, (message) => {
                const receivedMessage = JSON.parse(message.body);
                console.log("받은 메시지:", receivedMessage);
                callback(receivedMessage);
            });
            console.log(`방 구독 완료: /sub/chat/${roomId}`);
        },
        onStompError: (frame) => {
            console.error("STOMP 오류:", frame);
        }
    });

    stompClient.activate();
};

// 여기서 MessageType 을 넘겨줘도 좋을거 같ㅇ,ㅁ.
export const sendMessage = (roomId, userId, message) => {
    if (!stompClient || !stompClient.connected) {
        setTimeout(() => sendMessage(roomId, userId, message), 1000);
        return;
    }

    const msgData = {
        roomId,
        userId,
        content: message,
        type: "CHAT"
    };


    stompClient.publish({
        destination: `/pub/chat/${roomId}`,
        body: JSON.stringify(msgData),
    });
};

// ✅ disconnectStomp를 명확하게 export 추가
export const disconnectStomp = () => {
    if (stompClient) {
        stompClient.deactivate();
        console.log("STOMP 연결 해제됨");
        stompClient = null;
    }
};
