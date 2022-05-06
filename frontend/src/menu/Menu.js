import React from 'react'
import {MenuItem} from "./MenuItem";

export function Menu(activeContext,activeContextHandler) {
    return <div className="menu-bar">
        <ul>
            <MenuItem key="home" menuItemTitle="Домой" menuItemContext="home" activeContext={activeContext}
                      onClickHandler={(contextName) => activeContextHandler(contextName)}/>
            <MenuItem key="book-list" menuItemTitle="Книги" menuItemContext="book-list" activeContext={activeContext}
                      onClickHandler={(contextName) => activeContextHandler(contextName)}/>
            <MenuItem key="author-list" menuItemTitle="Авторы" menuItemContext="author-list" activeContext={activeContext}
                      onClickHandler={(contextName) => activeContextHandler(contextName)}/>
            <MenuItem key="genre-list" menuItemTitle="Жанры" menuItemContext="genre-list" activeContext={activeContext}
                      onClickHandler={(contextName) => activeContextHandler(contextName)}/>
        </ul>
    </div>
}