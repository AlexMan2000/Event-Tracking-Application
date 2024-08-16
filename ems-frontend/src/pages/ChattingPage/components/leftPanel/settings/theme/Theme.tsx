import React, { CSSProperties } from 'react';
import "./Theme.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface ThemeProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const Theme: React.FC<ThemeProps> = ({style, className}: ThemeProps) => {
  return (
    <div className={classNamesArgs("theme-container", className)} style={{...style}}>
      Theme Component
    </div>
  );
};

export default Theme;