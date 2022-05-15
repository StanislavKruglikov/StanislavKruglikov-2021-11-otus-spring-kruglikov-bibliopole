import React from "react";

export function AuthorLineItem({author}) {
    return <div>{author.id + ' - ' + author.firstName +' '+author.lastName +' '+author.patronymicName}</div>
}