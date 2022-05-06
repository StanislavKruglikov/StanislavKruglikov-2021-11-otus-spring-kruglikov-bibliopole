import React from "react";

export function EditBook({book}) {
    return <div className="edit-form">
        <form id="book-edit-layout-id" action="" method="POST" th:action="@{/book-save}" th:object="${book}">
            <input name="id" type="hidden" value={book.id}/>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-title">Название:</label>
                <input id="book-edit-title" name="title" value={book.title} placeholder="название" />
                {/*<span th:each="err : ${#fields.errors('title')}" th:text="${err}" className="field-error"/>*/}
            </div>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-authorId">Автор:</label>
                <select id="book-edit-authorId" name="authorId">
                    <option className="author" value={book.author.id}>{book.author.lastName} {book.author.firstName} {book.author.patronymicName}</option>
                    <option className="author" value="0">...</option>
                </select>
                {/*<span th:each="err : ${#fields.errors('authorId')}" th:text="${err}" className="field-error"/>*/}
            </div>
            <div className="edit-form-field-line">
                <label htmlFor="book-edit-genreId">Жанр:</label>
                <select id="book-edit-genreId" name="genreId">
                    <option className="genre" value={book.genre.id}>{book.genre.name}</option>
                    <option className="genre" value="0">...</option>
                </select>
                {/*<span th:each="err : ${#fields.errors('genreId')}" th:text="${err}" className="field-error"/>*/}
            </div>
            <button type="submit" name="action" value="save">Сохранить</button>
            <button type="submit" name="action" value="cancel">Отмена</button>
        </form>
    </div>
}