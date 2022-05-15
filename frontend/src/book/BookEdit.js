import React, { useState, useEffect } from "react";
import {AuthorComboBox} from "../author/AuthorComboBox";
import {saveBook} from "./BookApi";
import {GenreComboBox} from "../genre/GenreComboBox";

export function BookEdit({activeContext, changeContextHandler}) {
    const [book,setBook] = useState(activeContext.contextObject || {});
    const [bookValidation,setBookValidation] = useState(validation(activeContext.contextObject));
    return <div className="edit-form">
        <form id="book-edit-layout-id" onSubmit={(event)=> onSubmitHandler(event, activeContext, book, bookValidation, changeContextHandler) }>
            <input name="id" type="hidden" value={book.id}/>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-title">Название:</label>
                <input id="book-edit-title" name="title" value={book.title} placeholder="название"
                 onChange={(event)=> {
                    book[event.target.name] = event.target.value;
                    setBook(book);
                    setBookValidation(validation(book));
                 }}/>
                 <span className={!bookValidation.title ? "edit-form-field-error-off" : "edit-form-field-error-on"}/>
            </div>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-authorId">Автор:</label>
                <AuthorComboBox key={'book-edit-authorId'} id={'book-edit-authorId'} selectValue={book.authorId}
                    name={'authorId'}
                    onSelectHandler={(event) => {
                        book[event.target.name] = event.target.value;
                        setBook(book);
                        setBookValidation(validation(book));
                    }}/>
                <span className={!bookValidation.authorId ? "edit-form-field-error-off" : "edit-form-field-error-on"}/>
            </div>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-genreId">Жанр:</label>
                <GenreComboBox key={'book-edit-genreId'} id={'book-edit-genreId'} selectValue={book.genreId}
                    name={'genreId'}
                    onSelectHandler={(event) => {
                        book[event.target.name] = event.target.value;
                        setBook(book);
                        setBookValidation(validation(book));
                    }}/>
                <span className={!bookValidation.genreId ? "edit-form-field-error-off" : "edit-form-field-error-on"}/>
            </div>
            <button type="submit" name="actionSave" value="save">Сохранить</button>
            <button type="submit" name="actionCancel" value="cancel">Отмена</button>
        </form>
    </div>
}

function onSubmitHandler(event, activeContext, book, bookValidation, changeContextHandler) {
     event.preventDefault();
     if(event.nativeEvent.submitter.value === "save") {
        if(bookValidation.count > 0) {
            alert("Заполните обязательные поля!");
        } else {
             saveBook(book)
             .then((book)=>{
                 const changedActiveContext = {...activeContext};
                 changedActiveContext.contextObject = undefined;
                 changedActiveContext.action = 'book-list';
                 changeContextHandler(changedActiveContext);
             });
         }
     } else {
         const changedActiveContext = {...activeContext};
         changedActiveContext.contextObject = undefined;
         changedActiveContext.action = 'book-list';
         changeContextHandler(changedActiveContext);
     }
}

function validation(editedBook) {
    const bookValidation = {
        title: !(editedBook?.title?.length > 0),
        authorId: !(editedBook?.authorId > 0),
        genreId: !(editedBook?.genreId > 0)
    };
    bookValidation.count = Object.values(bookValidation).filter(field => field).length;
    return bookValidation;
}