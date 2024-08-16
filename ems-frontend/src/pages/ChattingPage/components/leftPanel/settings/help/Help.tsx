import React, { CSSProperties } from 'react';
import "./Help.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface HelpProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const Help: React.FC<HelpProps> = ({style, className}: HelpProps) => {
  return (
    <div className={classNamesArgs("help-container", className)} style={{...style}}>
      Help Component
    </div>
  );
};

export default Help;