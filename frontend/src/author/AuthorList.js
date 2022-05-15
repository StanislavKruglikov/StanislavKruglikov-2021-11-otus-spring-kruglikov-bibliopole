import React, { useEffect } from "react";
import {AuthorLineItem} from "./AuthorLineItem";
import {getAuthors} from "../author/AuthorApi";

export function AuthorList() {
    const [authors,setAuthors] = React.useState([]);
    useEffect(() => {
        getAuthors().then((authors) => {
            setAuthors(authors);
        });
    },[]);

    return <div className="list-block-item">
        <div id="author-list">
            { authors.map(author => <AuthorLineItem key={author.id} author={author} />) }
        </div>
    </div>
}