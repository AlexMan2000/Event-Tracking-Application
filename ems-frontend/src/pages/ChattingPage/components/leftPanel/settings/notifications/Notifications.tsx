import React, { CSSProperties } from 'react';
import "./Notifications.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import BackNavigation from '@/commons/components/navigation/BackNavigation';
import { SubPageBaseInterface } from '../../interfaces';
import { findFullPath } from '../../../ChattingModalContext';


interface NotificationsProps extends SubPageBaseInterface{
  // Define your props here

  style?: CSSProperties
  className?: string
}

const Notifications: React.FC<NotificationsProps> = ({onBackPage, onSubPage, style, className}: NotificationsProps) => {
  
  const findParentRouteFullPath = (currentRoute: string):string => {
    return currentRoute.split("/").slice(0, -1).join("/");
  }

  const currentFullPath = findFullPath("notifications").path;

  
  return (
    <div className={classNamesArgs("notifications-container", className)} style={{...style}}>
      <BackNavigation onClick={() => {
        const parentPathFullPath = findParentRouteFullPath(currentFullPath);
          if (onBackPage) {
            onBackPage(parentPathFullPath);
          }  
        }} 
        navName='Notifications' 
        navKey={"notifications"}/>
      <div className={"gaga"}>sdaaweadawdawdwddddddddddddddddddddd</div>
    </div>
  );
};

export default Notifications;