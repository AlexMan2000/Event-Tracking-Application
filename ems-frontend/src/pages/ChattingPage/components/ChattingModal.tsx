import React, { CSSProperties } from 'react';
import "./ComponentName.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface ChattingModalProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const ChattingModal: React.FC<ChattingModalProps> = ({style, className}: ChattingModalProps) => {
  return (
    <div className={classNamesArgs("chatting-app-container", className)} style={{...style}}>
      ChattingModal Component
    </div>
  );
};

export default ChattingModal;