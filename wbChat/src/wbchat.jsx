import React, { useState, useEffect } from "react";
import { connectStomp, disconnectStomp, sendMessage } from "./stompClient.jsx";
import "./WbChat.css";

const WbChat = () => {
    const [inputRoomId, setInputRoomId] = useState(""); // 사용자가 입력할 방 ID
    const [roomId, setRoomId] = useState(null); // 실제 연결된 방 ID
    const [userId, setUserId] = useState(""); // 사용자 ID
    const [messages, setMessages] = useState([]); // 메시지 목록
    const [message, setMessage] = useState(""); // 입력 중인 채팅 메시지

    useEffect(() => {
        if (roomId) {
            disconnectStomp(); // 연결 해제
            connectStomp(roomId, (msg) => {
                setMessages((prev) => [...prev, msg]); // 메시지 화면에 추가
            });

            // ✅ URL 변경하여 주소창에 방 번호 표시
            window.history.pushState({}, "", `/chat/${roomId}`);
        }

        return () => disconnectStomp(); // 언마운트시
    }, [roomId]);

    // 방 입장시에 사용. <- 아마 코드 내일 수젇필요.
    const handleRoomChange = () => {
        if (inputRoomId.trim()) {
            setRoomId(inputRoomId.trim()); // 새로운 방 번호 설정
            setMessages([]); // 방이 바뀔 때 기존 메시지 삭제?
        }
    };

    // 메시지 보내는거
    const sendChatMessage = () => {
        if (message.trim() && userId.trim() && roomId) {
            sendMessage(roomId, userId, message);
            setMessage("");
        }
    };

    return (
        <div className="chat-container">
            <h1>웹소켓 채팅</h1>

            <div className="chat-header">
                <input
                    type="text"
                    value={inputRoomId}
                    onChange={(e) => setInputRoomId(e.target.value)}
                    placeholder="방 번호 입력"
                    disabled={roomId !== null}
                />
                <button onClick={handleRoomChange} disabled={!inputRoomId.trim() || !userId.trim()}>
                    방 입장
                </button>

                <input
                    type="text"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                    placeholder="ID 입력"
                    disabled={roomId !== null}  // 지금은 한번에 묶여있어서 이름이랑 같이 방번호가 지정되게 함.
                />
            </div>

            {/*<p>{roomId ? `연결된 방: ${roomId}` : "연결안됨"}</p>*/}

            <div className="chat-box">
                {messages.map((msg, index) => (
                    <div key={index} className="chat-message">
                        <strong>{msg.userId}:</strong> {msg.content}
                    </div>
                ))}
            </div>

            <div className="chat-input">
                <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="메시지를 입력하세요"
                />
                <button onClick={sendChatMessage} disabled={!roomId}>
                    전송
                </button>
            </div>
        </div>
    );
};

export default WbChat;
