import React from 'react'
import {ContextHandler} from "../Context";

export function MenuItem({menuItemTitle, menuItemContext, activeContext}) {
    return <li className={activeContext === menuItemContext ? "menu-item menu-item-active" : "menu-item"}
               onClick={(event) => ContextHandler(menuItemContext)}>{menuItemTitle}
    </li>
}