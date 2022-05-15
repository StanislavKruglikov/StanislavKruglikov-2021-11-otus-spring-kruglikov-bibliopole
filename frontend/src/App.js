import React, { useState } from 'react'
import {MenuBar} from "./layout/menu/MenuBar";
import {Footer} from "./layout/footer/Footer";
import {Body} from "./layout/Body"

export function App() {
    const [activeContext,setActiveContext] = useState({ contextValue: 'home' });
    return <>
        <MenuBar
            activeContext={activeContext}
            changeContextHandler={ (changedActiveContext) => setActiveContext(changedActiveContext) }
        />
        <React.StrictMode>
        <Body activeContext={activeContext}
            changeContextHandler={ (changedActiveContext) => setActiveContext(changedActiveContext) }
        />
        </React.StrictMode>
        <Footer/>
        </>
}