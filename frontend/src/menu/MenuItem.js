import React from 'react'

export function MenuItem({menuItemTitle, menuItemContext, activeContext, onClickHandler}) {
    return <li className={activeContext === menuItemContext ? "menu-item menu-item-active" : "menu-item"}
               onClick={(event) => onClickHandler(menuItemContext)}>{menuItemTitle}
    </li>
}