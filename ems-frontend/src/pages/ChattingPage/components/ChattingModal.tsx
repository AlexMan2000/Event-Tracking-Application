import React, { CSSProperties } from 'react';
import "./ChattingModal.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import SideBar from './sidebar/SideBar';
import ChattingList from './leftPanel/chattingList/ChattingList';
import ChattingMessage from './chattingDialog/ChattingMessage';
import LeftPanel from './leftPanel/LeftPanel';
import Settings from './leftPanel/settings/Settings';
import Notifications from "./leftPanel/settings/notifications/Notifications"


interface ChattingModalProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}



const ChattingModal: React.FC<ChattingModalProps> = ({style, className}: ChattingModalProps) => {
  
  
   
  return (
    <div className={classNamesArgs("chatting-app-container", className)} style={{...style}}>
      <SideBar/>
      {/* <ChattingList></ChattingList> */}
      <LeftPanel></LeftPanel>
      <ChattingMessage></ChattingMessage>
    </div>
  );
};

export default ChattingModal;