import React from "react";
import {EditBookAction} from "./BookActions";

export function Book({book}) {
    return <div className="list-block-item">
        <div className="list-block-item-content">
            <a className="link-content" href="" onClick={(event) =>
                EditBookAction({book: book}) }>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Название:</div>
                    <div className="list-block-item-line-value">{book.title}</div>
                </div>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Автор:</div>
                    <div>{book.authorName}</div>
                </div>
                <div className="list-block-item-line">
                    <div className="list-block-item-line-title">Жанр:</div>
                    <div className="list-block-item-line-value">{book.genreName}</div>
                </div>
            </a>
        </div>
        <div className="list-block-item-control-block">
            <a className="list-block-item-control-block-first link-action" href="">комментарии</a>
            <a className="list-block-item-control-second link-action" href="">удалить книгу</a>
        </div>
    </div>
}