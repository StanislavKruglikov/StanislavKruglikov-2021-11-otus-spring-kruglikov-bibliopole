import React from 'react'

export function MenuItem({menuItem, onClick}) {
    return <li
        className={ menuItem.active ? "menu-item menu-item-active" : "menu-item"}
        onClick={ (event) => {
            onClick(menuItem.context)
        }}>{menuItem.title}
    </li>
}