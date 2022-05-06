import React from "react";
import { Book } from "./Book";

export function BookList({books}) {
    return <div id="book-list">
        <div className="list-block-item-control-block">
            <a className="link-action" href="">Добавить книгу</a>
        </div>
        { books.map(book => <Book key={book.id} book={book} />) }
    </div>
}