import React, { useEffect } from "react";
import {GenreListItem} from "./GenreListItem";
import {getGenres} from "../genre/GenreApi";

export function GenreList() {
    const [genres,setGenres] = React.useState([]);
    useEffect(() => {
        getGenres().then((genres) => {
            setGenres(genres);
        });
    },[]);
    return <div className="list-block-item">
        <div id="genre-list">
            { genres.map(genre => <GenreListItem key={genre.id} genre={genre} />) }
        </div>
    </div>
}