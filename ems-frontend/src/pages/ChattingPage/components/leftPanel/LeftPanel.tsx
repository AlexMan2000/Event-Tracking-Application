import React, { CSSProperties, useEffect, useState } from 'react';
import "./LeftPanel.css";
import { classNamesArgs } from "@/commons/utils/classNameHandler";
import { useChatBotModalContext } from '../ChattingModalContext';
import { findMenuNode } from '../ChattingModalContext';

interface PageState {
  component: React.ReactElement | null;
  key: string;
  transition?: string;
  transitionClass?: string;
  style?: CSSProperties;
}

const LeftPanel: React.FC = () => {


  

  const {currentRoute, setCurrentRoute } = useChatBotModalContext();


  const renderComponentFromPath = (currentRoute: string): React.ReactElement | null => {
    const findNode = findMenuNode(currentRoute);
    return findNode != null
      ? React.createElement(findNode.component, {
        onBackPage: handleBackClick,
        onSubPage: handleMenuClick
      } as React.ComponentProps<typeof findNode.component>)
      : null;
  };



  const handleMenuClick = (newFullPath: string) => {
    const newComponent = renderComponentFromPath(newFullPath);
    if (newComponent) {
      // Set up the new page stack
      setPages((prevPages) => [
        { ...prevPages[0] },
        { component: newComponent, key: newFullPath},
      ]);

      // Then render them
      setTimeout(() => {
        setPages((prevPages) => [
          { ...prevPages[0], transitionClass: "old", transition: "slide-out-left" },
          { ...prevPages[1], transitionClass: "new", transition: "slide-in-left" }
        ]);
        
      }, 10); // Small delay to ensure the DOM update

      setTimeout(()=>{
        setCurrentRoute(newFullPath);
      }, 200)
    }
  };
  

  const handleBackClick = (newFullPath: string) => {
    const newComponent = renderComponentFromPath(newFullPath);
    if (newComponent) {
        // First, set up the pages for the transition
      setPages((prevPages) => [
        { component: newComponent, key: newFullPath},
        { ...prevPages[0],  },
      ]);

      // Trigger the transitions with different speeds
      setTimeout(() => {
        setPages((prevPages) => [
          { ...prevPages[0], transitionClass: "new" },
          { ...prevPages[1], transitionClass: "old"},
        ]);
      }, 10); // Small delay to ensure the DOM update

      // After the transition, update the current route
      setTimeout(() => {
        setCurrentRoute(newFullPath);
      }, 200); // Match this delay to the slowest transition duration

    }
  };

  const [pages, setPages] = useState<PageState[]>([
    {
      component: renderComponentFromPath(currentRoute),
      key: currentRoute,
    }
  ]);
  
  useEffect(()=>{
    setPages([{
      key: currentRoute,
      component: renderComponentFromPath(currentRoute)
    }])
  }, [currentRoute])


  return (
    <div className={classNamesArgs("left-panel-container")}>
      {pages.map((page) => (
        <div key={page.key} className={classNamesArgs("left-panel-page", page.transitionClass, page.transition)}
          style={{...page.style}}
        >
          {page.component}
        </div>
      ))}
    </div>
  );
};

export default LeftPanel;
