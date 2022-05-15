import React from "react";

export function GenreListItem({genre}) {
    return <div class="">{genre.id + ' - ' + genre.name}</div>
}