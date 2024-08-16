import React, { CSSProperties } from 'react';
import "./BackNavigation.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import BackArrowButton from '../buttons/BackArrowButton/BackArrowButton';
import { findFullPath, useChatBotModalContext } from '@/pages/ChattingPage/components/ChattingModalContext';


interface BackNavigationProps {
  // Define your props here
  onClick?: () => void;
  navKey: string;
  navName: string
  style?: CSSProperties
  className?: string
}

const BackNavigation: React.FC<BackNavigationProps> = ({ onClick, navName, navKey, style, className}: BackNavigationProps) => {

  const findParentRouteFullPath = (currentRoute: string):string => {
    return currentRoute.split("/").slice(0, -1).join("/");
  }
  

  const currentItemFullPath = findFullPath(navKey).path;
  const parentPath = findParentRouteFullPath(currentItemFullPath);
  return (
    <div className={classNamesArgs("back-navigation-container", className)} style={{...style}}>
      {parentPath !=="" ? <BackArrowButton onClick={()=> {
        
        if (onClick) {
          onClick();
        }
        }}/> : null}
      {navName}
    </div>
  );
};

export default BackNavigation;