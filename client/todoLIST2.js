import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";



const App = () => {
  const [books, setBooks] = useState([
    { id: 1, writer: "1", title: "m1", Release_date: "2024-01-03" },
    { id: 1, writer: "2", title: "m2", Release_date: "2024-01-04" },
    { id: 1, writer: "3", title: "m3", Release_date: "2024-01-05" },
  ]);

  const [newBook, setNewBook] = useState({
    id: "",
    writer: "",
    title: "",
    Release_date: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewBook({ ...newBook, [name]: value });
  };

  const handleAddBook = () => {
    if (
      !newBook.id ||
      !newBook.writer ||
      !newBook.title ||
      !newBook.Release_date
    ) {
      alert("빈칸없이 채워주세요");
      return;
    }
    const isDuplicate = books.some((books) => String(books.id) === newBook.id);
    if (isDuplicate) {
      alert("중복 Id 입니다");
      setNewBook({ ...newBook, ["id"]: "" });
      return;
    }
    setBooks([...books, newBook]);
    setNewBook({ id: "", writer: "", title: "", Release_date: "" });
  };

  const handleDeleteBook = (id) => {
    const updatedBooks = books.filter((books) => books.id !== id);
    setBooks(updatedBooks);
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
              <Link to="/create">book add</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route
            path="/"
            element={<Home book={books} onDeleteBook={handleDeleteBook} />}
          />
          <Route
            path="/create"
            element={
              <CreateBook
                newBook={newBook}
                onInputChange={handleInputChange}
                onAddBook={handleAddBook}
              />
            }
          />
        </Routes>
      </div>
    </Router>
  );
};

const Home = (props) => {
  const { books, onDeleteBook } = props;
  return (
    <div>
      <h2>Book List</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Writer</th>
            <th>Title</th>
            <th>Release_date </th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.writer}</td>
              <td>{book.title}</td>
              <td>{book.Release_date}</td>
              <td>
                <button onClick={() => onDeleteBook(book.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const CreateBook = (props) => {
  const { newBook, onInputChange, onAddBook } = props;
  return (
    <div>
      <h2>메세지 작성</h2>
      <form>
        <input
          type="number"
          name="id"
          value={newBook.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="id"
          value={newBook.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="title"
          value={newBook.id}
          onChange={onInputChange}
        />
        <input
          type="number"
          name="Release_date"
          value={newBook.id}
          onChange={onInputChange}
        />

        <br />
      </form>
      <button onClick={onAddBook}>onAddBook</button>
    </div>
  );
};
export default App;
