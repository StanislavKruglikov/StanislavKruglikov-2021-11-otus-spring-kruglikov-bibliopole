import React, { useState } from "react";
import {saveComment} from "./CommentApi";

export function CommentEdit({activeContext, changeContextHandler}) {
    const [comment,setComment] = useState(activeContext.contextObject || {});
    const [commentValidation,setCommentValidation] = useState(validation(activeContext.contextObject));
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
                 <span className={!commentValidation.text ? "edit-form-field-error-off" : "edit-form-field-error-on"}/>
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
                changeContextHandler({...activeContext,contextObject: {bookId: comment.bookId}, action: 'comments'})
            );
         }
     } else {
         changeContextHandler({...activeContext,contextObject: {bookId: comment.bookId}, action: 'comments' });
     }
}

function validation(editedComment) {
    const commentValidation = {
        text: !(editedComment?.text?.length > 0)
    };
    commentValidation.count = Object.values(commentValidation).filter(field => field).length;
    return commentValidation;
}