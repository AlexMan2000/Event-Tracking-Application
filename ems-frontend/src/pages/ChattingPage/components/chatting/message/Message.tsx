import React, { CSSProperties } from 'react';
import "./Message.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface MessageProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const Message: React.FC<MessageProps> = ({style, className}: MessageProps) => {
  return (
    <div className={classNamesArgs("message-wrapper", className)}>
      Message Component
    </div>
  );
};

export default Message;