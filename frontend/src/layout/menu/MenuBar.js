import React from 'react'
import {MenuItem} from "./MenuItem";

export function MenuBar({menuData, activeContext, onClick}) {
    return <div className="menu-bar">
        <ul>{menuData.map((item) => {
                return <MenuItem key={item.context}
                    menuItem={item}
                    onClick = {  (contextValue) => {
                        menuData.forEach((item) => item.active = item.context === contextValue);
                        onClick(menuData,contextValue);
                    }}/>
            })}
        </ul>
    </div>;
}