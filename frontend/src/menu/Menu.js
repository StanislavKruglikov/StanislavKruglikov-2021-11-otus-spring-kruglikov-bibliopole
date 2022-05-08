import React from 'react'
import {MenuItem} from "./MenuItem";

export function Menu(activeContext, activeContextHandler) {
    window.ttt= activeContextHandler;
    return <div className="menu-bar">
        <ul>
            <MenuItem key="home" menuItemTitle="Домой" menuItemContext="home" activeContext={activeContext}
                      onClickHandler={activeContextHandler}/>
            <MenuItem key="book-list" menuItemTitle="Книги" menuItemContext="book-list" activeContext={activeContext}
                      onClickHandler={(context) => { activeContextHandler(context)}}/>
            <MenuItem key="author-list" menuItemTitle="Авторы" menuItemContext="author-list" activeContext={activeContext}
                      onClickHandler={(context) => { activeContextHandler(context)}} />
            <MenuItem key="genre-list" menuItemTitle="Жанры" menuItemContext="genre-list" activeContext={activeContext}
                      onClickHandler={(context) => { activeContextHandler(context)}}/>
        </ul>
    </div>
}