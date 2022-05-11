import React, { useEffect }  from "react";
import { Book } from "./Book";
import {getBooks} from "../book/BookApi";
import {deleteBook} from "../book/BookApi";



export function BookList() {
    const [books,setBooks] = React.useState([]);
    useEffect(() => {
        getBooks().then((books) => {
            setBooks(books);
        });
    },[]);
    return <div id="book-list">
        <div className="list-block-item-control-block">
            <span className="link-action" href="" onClick={()=>onAddHandler()}>Добавить книгу</span>
        </div>
        { books.map(book => <Book
            key={book.id}
            book={book}
            onEditHandler={(book)=> alert("edit book.title"+book.title)}
            onDeleteHandler={ (book) => {
                deleteBook(book).then( getBooks().then((books) => setBooks(books)))
            }}
            onCommentsHandler={(book)=>onCommentsHandler(book)}
            />)
        }
    </div>
}

function onCommentsHandler(book) {
    alert("COMMENT FOR BOOK: "+book.title);
}

function onAddHandler() {
    alert("ADD BOOK");
}