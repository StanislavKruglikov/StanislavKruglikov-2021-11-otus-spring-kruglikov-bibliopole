import React from 'react'
import {Home} from "../home/Home";
import {BookList} from "../book/BookList";
import {BookEdit} from "../book/BookEdit";
import {AuthorList} from "../author/AuthorList";
import {GenreList} from "../genre/GenreList";
import {CommentList} from "../comment/CommentList";
import {CommentEdit} from "../comment/CommentEdit";

export function Body({activeContext, changeContextHandler}) {
    if(activeContext.contextValue === 'home') {
       return <div className="content-block"><Home/></div>
    } else if(activeContext.contextValue === 'books') {
        if(activeContext.action === 'comments') {
            return <div className="content-block">
                <CommentList activeContext={activeContext} changeContextHandler={changeContextHandler} />
            </div>
        } else if(activeContext.action === 'comment-edit') {
            return <div className="content-block">
                <CommentEdit activeContext={activeContext} changeContextHandler={changeContextHandler} />
            </div>
        } else if(activeContext.action === 'book-edit') {
            return <div className="content-block">
                <BookEdit activeContext={activeContext} changeContextHandler={changeContextHandler} />
            </div>
        } else {
            return <div className="content-block">
                <BookList activeContext={activeContext} changeContextHandler={changeContextHandler}/>
            </div>
        }
    } else if(activeContext.contextValue === 'authors') {
            return <div className="content-block"> <AuthorList/> </div>
    } else if(activeContext.contextValue === 'genres') {
            return <div className="content-block"> <GenreList/> </div>
    } else {
        return null;
    }
}