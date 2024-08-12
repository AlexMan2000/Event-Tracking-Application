import React, { CSSProperties } from 'react';
import "./ComponentName.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface MessageListProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const MessageList: React.FC<MessageListProps> = ({style, className}: MessageListProps) => {
  return (
    <div className={classNamesArgs("message-container", className)}>
      MessageList Component
    </div>
  );
};

export default MessageList;