import React, { useEffect } from "react";
import {getAuthors} from "../author/AuthorApi";

export function AuthorComboBox({id, name, selectValue, onSelectHandler}) {
    const [authors,setAuthors] = React.useState([]);
    useEffect(() => {
        getAuthors().then((authors) => {
            setAuthors(authors);
        });
    },[]);

    return <select id={id} name={name} value={selectValue} onChange={onSelectHandler}>
            { authors.map(author => {
                return <option key={author.id} className="author" value={author.id} data={JSON.stringify(author)}>
                    {author.firstName +' '+author.lastName +' '+author.patronymicName}
                </option>;
            })}
            <option className="author" value="">...</option>
        </select>
}