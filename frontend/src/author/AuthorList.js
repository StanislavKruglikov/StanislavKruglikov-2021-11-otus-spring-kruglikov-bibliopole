import React, { useEffect } from "react";
import {Author} from "./Author";
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
            { authors.map(author => <Author key={author.id} author={author} />) }
        </div>
    </div>
}