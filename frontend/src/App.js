import React from 'react'
import {BookList} from "./book/BookList";
import {Menu} from "./menu/Menu";
import {Footer} from "./footer/Footer";

const books = [
    {id: 1, title:'dddd',authorName:'aaaa',genreName:'gggggg'},
    {id: 2, title:'dddd22',authorName:'aaaa222',genreName:'gggggg222'}
];

export function App() {
    const [activeContext,setActiveContext] = React.useState('home');
    return <>
        <Menu activeContext={activeContext} activeContextHandler={ (activeContext) => { return ()=>{ alert('ddddd'); } }}  />
        <div className="content-block">
            <BookList books={books}/>
        </div>
        <Footer/>
        </>
}