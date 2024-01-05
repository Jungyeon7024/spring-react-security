import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import "만들어준 css 파일명.css";

const App = () => {
  const [messages, setMessages] = useState([
    { id: 1, writer: "1", message: "m1", write_date: "2024-01-03" },
    { id: 1, writer: "2", message: "m2", write_date: "2024-01-04" },
    { id: 1, writer: "3", message: "m3", write_date: "2024-01-05" },
  ]);

  const [newMessage, setNewMessage] = useState({
    id: "",
    writer: "",
    messages: "",
    write_date: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewMessage({ ...newMessage, [name]: value });
  };

  const handleAddMessage = () => {
    if (
      !newMessage.id ||
      !newMessage.writer ||
      !newMessage.messages ||
      !newMessage.write_date
    ) {
      alert("빈칸없이 채워주세요");
      return;
    }
    const isDuplicate = messages.some(
      (messages) => String(messages.id) === newMessage.id
    );
     if (isDuplicate) {
      alert("중복 Id 입니다");
      setNewMessage({ ...newMessage, ["id"]: "" });
      return;
    }
    setMessages([...messages, newMessage]);
    setNewMessage({ id: "", writer: "", message: "", write_date: "" });
  };

  const handleDeleteMessage = (id) => {
    const updatedMessages = messages.filter((messages) => messages.id !== id);
    setMessages(updatedMessages);
  };
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">List</Link>
            </li>
            <li>
              <Link to="/create">Message add</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route
            path="/"
            element={
              <Home message={messages} onDeleteMessage={handleDeleteMessage} />
            }
          />
          <Route
            path="/create"
            element={
              <CreateMessage
                newMessage={newMessage}
                onInputChange={handleInputChange}
                onAddMessage={handleAddMessage}
              />
            }
          />
        </Routes>
      </div>
    </Router>
  );
};

const Home = (props) => {
  const { messages, onDeleteMessage } = props;
  return (
    <div>
      <h2>Message List</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>writer</th>
            <th>Message</th>
            <th>Write Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {messages.map((message) => (
            <tr key={message.id}>
              <td>{message.id}</td>
              <td>{message.writer}</td>
              <td>{message.message}</td>
              <td>{message.write_date}</td>
              <td>
                <button onClick={() => onDeleteMessage(message.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const CreateMessage = (props) => {
  const { newMessage, onInputChange, onAddMessage } = prpos;
  return (
    <div>
      <h2>메세지 작성</h2>
      <form>
        <input
          type="number"
          name="id"
          value={newMessage.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="id"
          value={newMessage.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="message"
          value={newMessage.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="write_date"
          value={newMessage.id}
          onChange={onInputChange}
        />

        <br />
      </form>
      <button onClick={onAddMessage}>onAddMessage</button>
    </div>
  );
};
export default App;