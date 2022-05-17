import React, { useEffect }  from "react";
import { CommentListItem } from "./CommentListItem";
import {getComments, deleteComment} from "../comment/CommentApi";

export function CommentList({activeContext, changeContextHandler}) {
    const bookId = activeContext.contextObject.bookId;
    const [comments,setComments] = React.useState([]);
    useEffect(() => {
        getComments(bookId).then((comments) => {
            setComments(comments);
        });
    },[]);
    return <div id="comment-list">
        <div className="list-block-item-control-block">
            <span className="link-action detail-point list-block-item-control-first" href=""
            onClick={()=> changeContextHandler({...activeContext,action: 'comment-edit',contextObject: {bookId: bookId}})}>
                Добавить комментарий
            </span>
            <span className="link-action step-back-point list-block-item-control-second" href="" onClick={()=>{
                    const changedActiveContext = {...activeContext,
                        contextValue: 'books', action: 'book-list',contextObject: undefined};
                    changeContextHandler(changedActiveContext);
            }}>Вернуться
            </span>
        </div>
        { comments.map(comment => <CommentListItem
            key={comment.id}
            comment={comment}
            onEditHandler={()=> changeContextHandler({...activeContext,action: 'comment-edit',contextObject: comment})}
            onDeleteHandler={() => {
                console.log("start delete comment");
                deleteComment(comment)
                    .then(() => {
                        getComments(bookId)
                        .then((listComments) => {
                            console.log("set new comment list");
                            setComments(listComments);
                        });
                    }
                    )
                }}
            />)
        }
    </div>
}