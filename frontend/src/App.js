import React from 'react'
import {MenuBar} from "./layout/menu/MenuBar";
import {menuData} from "./layout/menu/MenuApi";
import {Footer} from "./layout/footer/Footer";
import {Body} from "./layout/Body"

export function App() {
    const [menuItems,setMenuItems] = React.useState(menuData);
    const [activeContext,setActiveContext] = React.useState(menuItems.filter(i=>i.active===true)[0].context);
    return <>
        <MenuBar menuData={menuData} activeContext={activeContext} onClick={
        (menuData,contextValue) => {
            setActiveContext(contextValue);
            setMenuItems(menuData);
        }}/>
        <Body activeContext={activeContext}/>
        <Footer/>
        </>
}