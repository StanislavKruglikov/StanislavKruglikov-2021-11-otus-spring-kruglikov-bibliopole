import React from 'react'
import {Home} from "../home/Home";
import {BookList} from "../book/BookList";
import {AuthorList} from "../author/AuthorList";
import {GenreList} from "../genre/GenreList";

export function Body({activeContext}) {
    if(activeContext === 'home') {
       return <div className="content-block">
            <Home/>
        </div>
    } else if(activeContext === 'books') {
        return <div className="content-block">
                    <BookList/>
        </div>
    } else if(activeContext === 'authors') {
            return <div className="content-block">
                        <AuthorList/>
            </div>
    } else if(activeContext === 'genres') {
                return <div className="content-block">
                            <GenreList/>
                </div>
    } else {
        return null;
    }
}