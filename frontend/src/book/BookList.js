import React, { useState, useEffect }  from "react";
import { BookListItem } from "./BookListItem";
import {getBooks, deleteBook} from "../book/BookApi";

export function BookList({activeContext, changeContextHandler}) {
    const [books,setBooks] = useState([]);
    useEffect(() => {
        getBooks().then((newBooks) => {
            setBooks(newBooks);
        });
    },[]);
    return <div id="book-list">
        <div className="list-block-item-control-block">
            <span className="link-action add-point" href="" onClick={()=> changeContextHandler({...activeContext,contextObject: undefined,action: 'book-edit'})}>Добавить книгу</span>
        </div>
        { books.map(book => <BookListItem
            key={book.id}
            book={book}
            onEditHandler={()=> changeContextHandler({...activeContext,contextObject: book,action: 'book-edit'})}
            onDeleteHandler={ (book) => {
                console.log("start delete book");
                deleteBook(book)
                    .then(() => {
                        getBooks()
                            .then((newBooks) => {
                                console.log("set new book list");
                                setBooks(newBooks);
                            });
                    })
            }}
            onCommentsHandler={(book)=> changeContextHandler({...activeContext,contextObject: {bookId: book.id},action: 'comments'})}
            />)
        }
    </div>
}