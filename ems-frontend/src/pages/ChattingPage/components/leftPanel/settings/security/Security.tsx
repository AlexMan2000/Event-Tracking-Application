import React, { CSSProperties } from 'react';
import "./Security.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface SecurityProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const Security: React.FC<SecurityProps> = ({style, className}: SecurityProps) => {
  return (
    <div className={classNamesArgs("security-container", className)} style={{...style}}>
      Security Component
    </div>
  );
};

export default Security;