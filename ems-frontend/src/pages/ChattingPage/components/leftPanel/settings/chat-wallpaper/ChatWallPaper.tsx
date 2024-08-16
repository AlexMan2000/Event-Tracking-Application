import React, { CSSProperties } from 'react';
import "./ChatWallPaper.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface ChatWallPaperProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const ChatWallPaper: React.FC<ChatWallPaperProps> = ({style, className}: ChatWallPaperProps) => {
  return (
    <div className={classNamesArgs("chat-wall-paper-container", className)} style={{...style}}>
      ChatWallPaper Component
    </div>
  );
};

export default ChatWallPaper;