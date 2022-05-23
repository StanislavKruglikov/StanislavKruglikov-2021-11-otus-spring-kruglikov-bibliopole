import React, { useState } from "react";
import {saveComment} from "./CommentApi";

export function CommentEdit({activeContext, changeContextHandler}) {
    const [comment,setComment] = useState({text: undefined,...activeContext.contextObject});
    const [commentValidation,setCommentValidation] = useState(validation(comment));
    return <div className="edit-form">
        <form id="comment-edit-layout-id" onSubmit={(event)=> onSubmitHandler(event, activeContext, comment, commentValidation, changeContextHandler)}>
            <div className="edit-form-field-line">
                <textarea id="comment-edit-text" name="text" value={comment.text} rows={5} cols={50} placeholder="текст комментария"
                 onChange={(event)=> {
                    const editedComment = {...comment};
                    editedComment[event.target.name] = event.target.value;
                    setComment(editedComment);
                    setCommentValidation(validation(comment));
                 }}/>
                 <span className={comment.text ? "edit-form-field-error-off" : "edit-form-field-error-on"}/>
            </div>
            <button type="submit" name="actionSave" value="save">Сохранить</button>
            <button type="submit" name="actionCancel" value="cancel">Отмена</button>
        </form>
    </div>
}

function onSubmitHandler(event, activeContext, comment, commentValidation, changeContextHandler) {
     event.preventDefault();
     if(event.nativeEvent.submitter.value === "save") {
        if(commentValidation.count > 0) {
            alert("Заполните обязательные поля!");
        } else {
             saveComment(comment)
             .then((comment)=>
                changeContextHandler({...activeContext,contextObject: {book: comment.book}, action: 'comments'})
            );
         }
     } else {
         changeContextHandler({...activeContext,contextObject: {book: comment.book}, action: 'comments' });
     }
}

function validation(editedComment) {
    return {
        count: Object.values(editedComment).filter(field => !field).length
    };
}