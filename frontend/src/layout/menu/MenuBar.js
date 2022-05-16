import React from 'react'
import {MenuItem} from "./MenuItem";
import {menuData} from "./MenuApi";

export function MenuBar({activeContext, changeContextHandler}) {
    return <div className="menu-bar">
        <ul>{menuData.map((item) => {
                item.active = item.context === activeContext.contextValue;
                return <MenuItem key={item.context}
                    menuItem={item}
                    onClick = {  (menuItem) => {
                        const changedActiveContext = {...activeContext};
                        changedActiveContext.contextValue = menuItem.context;
                        changeContextHandler(changedActiveContext);
                    }}/>
            })}
        </ul>
    </div>;
}