import React from "react";

export function Author({author}) {
    return <div><a href="">{author.id + ' - ' + author.firstName +' '+author.lastName +' '+author.patronymicName}</a></div>
}