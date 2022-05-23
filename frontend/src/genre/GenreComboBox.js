import React, { useEffect } from "react";
import {getGenres} from "../genre/GenreApi";

export function GenreComboBox({id, name, selectValue, onSelectHandler}) {
    const [genres,setGenres] = React.useState([]);
    useEffect(() => {
        getGenres().then((genres) => {
            setGenres(genres);
        });
    },[]);

    return <select id={id} name={name} value={selectValue} onChange={onSelectHandler}>
            { genres.map(genre => {
                return <option key={genre.id} className="genre" value={genre.id} data={JSON.stringify(genre)}>
                    {genre.name}
                </option>;
            })}
            <option className="author" value="">...</option>
        </select>
}